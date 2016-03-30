package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import com.example.web.login.LoginService;
import com.example.web.login.SocialUserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private LoginService loginService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	.antMatchers("/","/signup").permitAll()
                .antMatchers("/web/**").hasRole("USER")
                .antMatchers("/backend/**").access("hasRole('USER') and hasRole('ADMIN')") 
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login.do")
                .failureUrl("/login.do?error")
                .loginProcessingUrl("/authLogin.do")
                .defaultSuccessUrl("/",true) 
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout.do")
                .logoutSuccessUrl("/login.do?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll().and()
            .apply(new SpringSocialConfigurer());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        http.exceptionHandling().accessDeniedPage("/login.do?error");
        http.sessionManagement().invalidSessionUrl("/login.do");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService);
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/sb-admin-2-1.0.8/**","/webjars/**","/kakao/userinfo");
	}
	
	@Bean
	public SocialUserDetailsService socialUserDetailsService() {
		return new SocialUserDetailsServiceImpl(loginService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}	
}