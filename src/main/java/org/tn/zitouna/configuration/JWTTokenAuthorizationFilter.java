package org.tn.zitouna.configuration;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTTokenAuthorizationFilter extends OncePerRequestFilter{

	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-with, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,Authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Request-Method, Access-Control-Allow-Origin,Access-Control-Allow-Credentials, Authorization");
	
		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
			response.setStatus(HttpStatus.OK.value());
		}else {
			
			String requestToken = request.getHeader("Authorization"); //"Bearer asdf3436sdfgdg2564356...."
			
			String userName = null;
			String jwtToken = null;
			System.out.println(requestToken);
			if(requestToken !=null && requestToken.startsWith("Bearer ")) {
				jwtToken = requestToken.substring(7);
				userName = this.jwtTokenUtil.getUserNameFromToken(jwtToken);
				
			}else{
				logger.warn("JWT token is null or does not begin with Bearer String for url "+ request.getRequestURI());
			}
			if( jwtTokenUtil.validatToken(jwtToken, jwtTokenUtil.getUserNameFromToken(jwtToken))) {
				
				List<GrantedAuthority> authorities = this.jwtTokenUtil.getAuthoritiesClaimFromToken(jwtToken);
				
				Authentication authentication = this.jwtTokenUtil.getAthentication(userName, authorities, request);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
		
			
		}
		
		filterChain.doFilter(request, response);
		
	}

	
}