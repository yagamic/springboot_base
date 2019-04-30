package com.yagamic.base.appliaction.web;

import com.yagamic.base.appliaction.web.spring.ImagePreviewSelfCacheResolver;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;


@Import({ SwaggerConfig.class})  //InfrastructureConfig.class, DomainConfig.class,
@Configuration
@EnableSwagger2
public class WebConfiguration extends WebMvcConfigurerAdapter {
    //@Value("${consul:file.location}")
    String LOCATION = "/opt/web/file";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //上传的文件
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + LOCATION + "/")
                .setCachePeriod(31556926)
                .resourceChain(false)
                .addResolver(new ImagePreviewSelfCacheResolver( new ConcurrentMapCache("ResourceCache")));
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //multipart支持，要结合MultipartConfigElement
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    private static final Charset UTF8 = Charset.forName("UTF-8");


    //应答消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //能够返回String
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
        stringConverter.setWriteAcceptCharset(false);
        converters.add(stringConverter);

        //能够返回图片
        BufferedImageHttpMessageConverter imageConverter = new BufferedImageHttpMessageConverter();
        imageConverter.setDefaultContentType(MediaType.IMAGE_PNG);
        converters.add(imageConverter); //这个必须在MappingJackson2HttpMessageConverter前面
        converters.add(new ResourceHttpMessageConverter());

        //能够返回JSON
        converters.add(new MappingJackson2HttpMessageConverter());

    }
}
