package com.szjz.sell.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author szjz
 * @date 2019/5/8 11:16
 * 配置swagger
 */

@Configuration
@EnableSwagger2
@ComponentScan({"com.szjz.sell.controller"})  //扫描对应的包名
public class SwaggerConfig {
    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

//    @Resource
//    private ConfigProperties configProperties;

    @Bean
    public Docket createRestApi() {
        logger.debug("swagger initializing");
        StopWatch watch = new StopWatch();
        watch.start();

        List paremeters = new ArrayList<>();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)//
                .select()//
//            .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))//
                .paths(PathSelectors.any())//
                .build()//
                .pathMapping("/")//
                .globalOperationParameters(paremeters)//
                .apiInfo(apiInfo());//
//                .host(configProperties.getApp().getApiDomain());

        watch.stop();
        logger.debug("Swagger initialized in {} ms", watch.getTotalTimeMillis());
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Swagger2")//
                .description("Restful Api")//
                .contact(new Contact("石中君子", "", ""))//
                .version("1.0")//
                .build();

    }

}
