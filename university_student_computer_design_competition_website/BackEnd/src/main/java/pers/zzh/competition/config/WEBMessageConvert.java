package pers.zzh.competition.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WEBMessageConvert {
    /**
     * 使用bean注入,才能使其有效果,验证的话就在Entity字段中使用fastjson的
     * 注解@JSONField(serialize = false),转换出来的信息不含该字段,则成功
     */
    @Bean
    public HttpMessageConverters customConverters() {


        //文件下载使用ByteArrayHttpMessageConverter处理
        ByteArrayHttpMessageConverter byteArrayHttpMessageConverter = new ByteArrayHttpMessageConverter();

        //加入转换器
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(byteArrayHttpMessageConverter);

        return new HttpMessageConverters(converters);
    }
}
