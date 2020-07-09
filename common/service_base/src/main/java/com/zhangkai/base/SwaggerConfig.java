package com.zhangkai.base;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Detail 配置Swagger步骤
 *              1、添加依赖
 *              2、创建一个公共服务来配置Swagger
 *                  由于很多模块需要使用Swagger进行测试，使用把Swagger的配置在common工程中
 *              3、配置启动类扫描Swagger所在的包   @ComponentScan(basePackages = {"com.atguigu"})
 *              4、配置Controller注解，方便查看方法作用
 *                  1、@Api(description="讲师管理")  作用在Controller上
 *                  2、@ApiOperation(value = "所有讲师列表")  作用在方法上
 *                  3 、@ApiParam(name = "id", value = "讲师ID", required = true)  作用在参数上
 * @Author MyPC
 * @Date 2020/6/20
 * @Version 1.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")   //组名，可自定义
                .apiInfo(webApiInfo())
                .select()
                //.paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()   //接口文档信息配置
                .title("网站-课程中心API文档")
                .description("本文档描述了课程中心微服务接口定义")
                .version("1.0")
                .contact(new Contact("小林", "http://atguigu.com", "hahhah@qq.com"))
                .build();
    }
}
