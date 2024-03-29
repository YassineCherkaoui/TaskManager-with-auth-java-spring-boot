package com.github.julioevencio.apitask.configs;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.github.julioevencio.apitask.services.TokenJwtService;
import com.github.julioevencio.apitask.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthFilter extends OncePerRequestFilter {

	private final TokenJwtService tokenJwtService;
	private final UserDetailsService userDetailsService;

	public JwtAuthFilter(TokenJwtService tokenJwtService, UserDetailsServiceImpl userDetailsServiceImpl) {
		this.tokenJwtService = tokenJwtService;
		this.userDetailsService = userDetailsServiceImpl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorization = request.getHeader("Authorization");
		
		if (authorization != null && authorization.startsWith("Bearer ")) {
			String token = authorization.substring("Bearer ".length());
			
			if (tokenJwtService.validateToken(token)) {
				String username = tokenJwtService.getUsername(token);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
