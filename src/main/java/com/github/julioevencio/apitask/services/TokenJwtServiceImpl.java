package com.github.julioevencio.apitask.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.julioevencio.apitask.dto.security.TokenResponseDTO;

@Service
public class TokenJwtServiceImpl implements TokenJwtService {

	@Value("${security.jwt.token.secret-key}")
	private String secretKey;

	@Value("${security.jwt.token.expire-length}")
	private Long validityInMilliseconds;

	@Override
	public TokenResponseDTO createAccessToken(String username, List<String> roles) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + validityInMilliseconds);
		String issueURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		
		String accessToken = JWT.create()
				.withClaim("roles", roles)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.withSubject(username)
				.withIssuer(issueURL)
				.sign(Algorithm.HMAC256(secretKey.getBytes()))
				.strip();

		return new TokenResponseDTO(username, true, now, validity, accessToken);
	}
	
	@Override
	public boolean validateToken(String token) {
		try {
			DecodedJWT decodedJWT = this.decodedToken(token);

			if (decodedJWT.getExpiresAt().before(new Date())) {
				return false;
			}

			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public String getUsername(String token) {
		return this.decodedToken(token).getSubject();
	}
	
	private DecodedJWT decodedToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		
		return decodedJWT;
	}
	
}
