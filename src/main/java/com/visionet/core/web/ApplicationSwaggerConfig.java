package com.visionet.core.web;

import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * projectName:dzcx_lease
 * author:liusy@visionet.com.cn
 * data:2016/11/3
 */
@EnableSwagger2
public class ApplicationSwaggerConfig {
    @Bean
    public Docket addUserDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfo apiInfo = new ApiInfo(
                "租赁平台 API",
                "API Document 管理",
                "v1.0",
                "http://www.letzgo.com.cn/",
                new Contact("liusy", "http://www.letzgo.com.cn/", "liusy@visionet.com.cn"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0"
        );
        docket.apiInfo(apiInfo);
        return docket;
    }
}
