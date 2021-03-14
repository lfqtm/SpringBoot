package com.stud.web.config;

import com.stud.web.convert.GuiGuMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.condition.MediaTypeExpression;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfiguration {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter methodFilter = new HiddenHttpMethodFilter();
        methodFilter.setMethodParam("_m");
        return methodFilter;
    }


    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            //自定义协商策略
            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                //添加支持解析的媒体类型
                Map<String, MediaType> mediaTypeMap = new HashMap<>();
                mediaTypeMap.put("json", MediaType.APPLICATION_JSON);
                mediaTypeMap.put("xml", MediaType.APPLICATION_XML);
                mediaTypeMap.put("gg", MediaType.parseMediaType("application/x-guigu"));
                //参数适配
                ParameterContentNegotiationStrategy negotiationStrategy = new ParameterContentNegotiationStrategy(mediaTypeMap);
                //请求头适配
                HeaderContentNegotiationStrategy headerContentNegotiationStrategy = new HeaderContentNegotiationStrategy();
                configurer.strategies(Arrays.asList(negotiationStrategy, headerContentNegotiationStrategy));
            }

            //开启矩阵变量
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                UrlPathHelper urlPathHelper = new UrlPathHelper();
                urlPathHelper.setRemoveSemicolonContent(false);
                configurer.setUrlPathHelper(urlPathHelper);
            }

            @Override
            public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
                converters.add(new GuiGuMessageConverter());
            }

        };
    }
}
