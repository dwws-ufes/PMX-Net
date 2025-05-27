package br.ifes.pmxnet.seguranca;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtFilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtAuthFilter());

        // ⚠️ Altere para o prefixo que você deseja proteger (ex: /api/privado/*)
        registrationBean.addUrlPatterns("/api/tipos/*");

        return registrationBean;
    }
}
