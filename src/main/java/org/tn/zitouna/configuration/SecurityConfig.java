package org.tn.zitouna.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	

	private JWTTokenAuthorizationFilter jwtTokenAuthorizationFilter;
	@Autowired
	public SecurityConfig (JWTTokenAuthorizationFilter jwtTokenAuthorizationFilter) {
		
		this.jwtTokenAuthorizationFilter = jwtTokenAuthorizationFilter;
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.csrf().disable().cors().and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated().and().addFilterBefore(this.jwtTokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	
}
