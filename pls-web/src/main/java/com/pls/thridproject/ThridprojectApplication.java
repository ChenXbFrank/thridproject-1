package com.pls.thridproject;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
//这里加上dao/mapper所在的包名  启动时自动扫描
@MapperScan(basePackages = "com.pls.thridproject.dao")
//http://localhost:8084/swagger-ui.html
@EnableSwagger2
public class ThridprojectApplication extends WebMvcConfigurationSupport {

	public static void main(String[] args) {
		SpringApplication.run(ThridprojectApplication.class, args);
	}

	/**
	 * 这里是为了使用阿里的fastjson
	 *
	 */
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		super.configureMessageConverters(converters);
		FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
		FastJsonConfig fastConfig=new FastJsonConfig();
		fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastConfig);
		converters.add(fastConverter);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(
				"classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations(
				"classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations(
				"classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	/**
	 * 配置servlet处理
	 */
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.pls.thridproject.controller"))
				.paths(PathSelectors.any())
				.build();
	}

    /**
     * 这里配置swagger的title和描述等
     * @return
     */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("三期接口文件")
				.description("请按照接口说明输入输出参数")
				.termsOfServiceUrl("http://www.joffro.com/")
				.version("1.0")
				.build();
	}
}
