package br.com.cursojava.petshop.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class UrlsConfigs extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "pet-shop";

    @Override
    public void configure(ResourceServerSecurityConfigurer configurer){
        configurer.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/autenticar").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/api/v2/**").permitAll()
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/criar-usuario").permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}
