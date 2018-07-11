package com.pls.thridproject;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
//这里加上dao/mapper所在的包名  启动时自动扫描
@MapperScan(basePackages = "com.pls.thridproject.dao")
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
}
