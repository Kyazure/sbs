package online.kyralo.common.security;

import online.kyralo.common.security.filter.JwtAuthorizationFilter;
import online.kyralo.common.security.filter.XssFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: guohezuzi
 * \* Date: 2018-11-10
 * \* Time: 下午3:37
 * \* Description:web安全配置
 * \
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().
                httpBasic().disable().
                formLogin().disable().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
                exceptionHandling().
                authenticationEntryPoint(new RestAuthenticationEntryPoint()).and().
                authorizeRequests().antMatchers(
                        "/api/v2/user/login",
                                    "/api/v2/user/register",
                                    "/api/v2/user/code/get",
                                    "/api/v2/user/code/verify",
                                    "/api/v2/user_gives/count").permitAll().and().
                authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().and().
                authorizeRequests().antMatchers("/", "/docs.html",
                        "/v2/api-docs",
                        "/swagger-resources",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security").permitAll().
                anyRequest().authenticated().and().
                addFilter(new JwtAuthorizationFilter(authenticationManager())).
                addFilterAfter(new XssFilter(), BasicAuthenticationFilter.class);

	//注销
	//http.logout();
    }

    @Override
    @Profile({"dev", "test"})
    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers("/webjars/**")
                .mvcMatchers("/upload/**");
    }
}
