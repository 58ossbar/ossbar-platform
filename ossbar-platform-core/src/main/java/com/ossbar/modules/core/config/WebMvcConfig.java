package com.ossbar.modules.core.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import com.ossbar.modules.core.common.cbsecurity.repeatsubmit.TokenInterceptor;

@Configuration
@AutoConfigureBefore(SecurityConfig.class)
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${com.ossbar.file-upload-path:/mnt/ossbar/uploads}")
	private String filePath;

	@Value("${com.ossbar.is-cors:false}")
	private boolean isCors;

	@Value("${com.ossbar.cors-url:*}")
	private String corsUrl;

	@Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		if(isCors) {
	    	registry.addMapping("/**")
	        .allowedOrigins(corsUrl)
	        .allowedMethods("*")
	        .allowedHeaders("*")
	        .allowCredentials(true)
	        .maxAge(3600);
		}
	}

	@Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("forward:/index");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("uploads/**")
		.addResourceLocations("file:d:/uploads/");
    	registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    /**
	 * 添加多个拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 国际化支持
		registry.addInterceptor(initializingLocaleChangeInterceptor());
		// 防止页面重复提交
		registry.addInterceptor(initializingTokenInterceptor()).addPathPatterns("/**");
	}
	@Bean
	public TokenInterceptor initializingTokenInterceptor() {
		logger.info("防止页面重复提交，确保数据安全！");
		return new TokenInterceptor();
	}
    /**
	 * 字符串消息转换器
	 */
	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter() {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return stringHttpMessageConverter;
	}

	/**
	 * JSON 数据处理
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setDefaultCharset(Charset.forName("UTF-8"));
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		return mappingJackson2HttpMessageConverter;
	}
	
	// 解决中文乱码问题
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(stringHttpMessageConverter());
		converters.add(mappingJackson2HttpMessageConverter());
	}
	/**
	 * 国际化支持 @Title: localeResolver @Description: @param @return 参数 @return
	 * LocaleResolver 返回类型 @throws
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		// 默认语言
		slr.setDefaultLocale(Locale.CHINESE);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor initializingLocaleChangeInterceptor() {
		logger.info("国际化支持！");
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		// 参数名
		lci.setParamName("lang");
		return lci;
	}
	@Bean 
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new CbMappingJackson2HttpMessageConverter());
		return restTemplate;
	}
}