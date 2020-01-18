package com.autoservis.projekat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService uds;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(uds);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests(authorize -> {
	    	authorize.antMatchers("/").permitAll();
	    	authorize.antMatchers("/admin/**").hasRole("ADMIN");
	    	authorize.antMatchers("/worker/**").hasAnyRole("ADMIN","WORKER");
	    });
        
        http.formLogin(login -> {
        	
        	login.loginPage("/pages/login.jsp");
        	login.loginProcessingUrl("/login");
        	login.defaultSuccessUrl("/getAll");
        	login.failureForwardUrl("/pages/failure.jsp");
        });
        
        http.logout(logout -> {
        	logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/pages/login.jsp");
        });
        
        http.exceptionHandling(exception -> {
        	exception.accessDeniedPage("/pages/access_denied.jsp");
        });
        
        http.rememberMe();
        http.csrf();
	}
	
}
