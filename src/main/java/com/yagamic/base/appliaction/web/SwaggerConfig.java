package com.yagamic.base.appliaction.web;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;


@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("seravee系统API",
                "#### HTTP请求应答状态码：\n"+
                "* 200:请求成功完成\n"+
                "- 404：表示相关实体不存在，很多情况下可能是APP传错ID了或这个实体已经被删除\n" +
                "- 4xx：表示逻辑出错，出错原因在body里返回\n" +
                "- 500：表示服务器异常，通常是服务器BUG造成的，请在APP中显示“网络错误”\n" +
                "### 自动生成的文档参数对象包含一些不需要的属性,请与界面结合使用，不需要的属性不必传\n",
                "3.0","","xiemeilong.ok@gmail.com","","");
    }

    private Predicate<String> paths() {
        return regex("/api/.*");
    }

}
