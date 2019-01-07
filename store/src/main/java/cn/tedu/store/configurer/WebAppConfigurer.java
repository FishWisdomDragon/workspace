package cn.tedu.store.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.tedu.store.interceptor.LoginInterceptor;
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer{
@Override
public void addInterceptors(InterceptorRegistry registry) {

	registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**").addPathPatterns("/web/**").excludePathPatterns("/user/reg.do")
	.excludePathPatterns("/user/login.do").excludePathPatterns("/web/login.html").excludePathPatterns("/web/register.html");
}
}

