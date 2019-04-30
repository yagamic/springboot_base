package com.yagamic.base.infrastructure.utils;

import com.yagamic.base.infrastructure.serialization.KryoSerializer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;

@Aspect
@Component
@PropertySource("classpath:/application.properties")
public class AspectExpressionsBean extends KryoSerializer {
    @Value("${cache.timeout}")
    private String timeoutval;

    //@Autowired
    JedisPool jedisPool;

    @Around("execution(* *(..)) && @annotation(cache)")
    public Object calculateExecutionTime(ProceedingJoinPoint pjp,
                                         Cache cache) throws Throwable {
        StringBuilder key = new StringBuilder(cache.key());
        int paramsCount = cache.paramsCount();
        for (int i = 0; i < paramsCount; i++) {
            key.append((pjp.getArgs()[i]));
            if (StringUtils.hasText(cache.separator()) && i != (paramsCount - 1)) {
                key.append(cache.separator());
            }
        }
        String keyVal = key.toString();
        try (Jedis jedis = jedisPool.getResource()) {
            //超时时间
            long timeout = cache.timeout();
            if (timeout == 0) {
                timeout = Long.valueOf(timeoutval);
            }
            timeout = timeout * 60 * 1000l;
            long now = (new Date()).getTime();
            String timeoutKey = "_starttime_" + keyVal;
            if (!jedis.exists(timeoutKey)) {
                jedis.set(timeoutKey, String.valueOf(now));
            }
            long start = Long.valueOf(jedis.get(timeoutKey));
            long interval = now - start;

            Object retVal = null;
            //如果超过缓存时间,就去取实际的值
            boolean isExist = jedis.exists(keyVal);
            if (interval >= timeout || interval == 0 || !isExist) {
                try {
                    retVal = pjp.proceed();
                } catch (EntityNotFoundException t) {
                    return getResult(cache, jedis.get(keyVal), isExist);
                }
                //重置起始时间
                jedis.set(timeoutKey, String.valueOf(now));
                if (cache.isList()) {
                    if (((List) retVal).size() != 0) {
                        jedis.set(keyVal, serializationList((List) retVal, cache.cacheObject()));
                    } else {
                        if (isExist) {
                            jedis.del(keyVal);
                        }
                    }
                    return retVal;
                } else {
                    if (retVal != null) {
                        jedis.set(keyVal, serializationObject(retVal, cache.cacheObject()));
                        return retVal;
                    }
                }
            }

            return getResult(cache, jedis.get(keyVal), true);
        } catch (Exception t) {
            return getResult(cache, null, false);
        }
    }

    private Object getResult(Cache cache, String cacheVal, boolean isExist) {
        if (isExist) {
            if (cache.isList()) {
                return deserializationList(cacheVal, cache.cacheObject());
            } else {
                return deserializationObject(cacheVal, cache.cacheObject());
            }
        } else {
            if (cache.isList()) {
                return new ArrayList<>();
            } else {
                return null;
            }
        }
    }

}
