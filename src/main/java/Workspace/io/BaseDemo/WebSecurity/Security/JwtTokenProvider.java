package Workspace.io.BaseDemo.WebSecurity.Security;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	@Value(	"${questapp.app.secret}")
	private String AppKey;
	@Value(	"${questapp.expires.in}")
	private long Time;
	
	public String generateJwtToken(org.springframework.security.core.Authentication auth)
	{
		JwtUserDetails userDetails=(JwtUserDetails) auth.getPrincipal();
		
		return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setIssuer("BaseDemo")
				.setExpiration(new Date(System.currentTimeMillis()+Time))
				.signWith(SignatureAlgorithm.HS512,AppKey)
				.compact();
	}
	
	Long getUserIdFromJwt(String token)
	{
		Claims claims =  Jwts.parser().setSigningKey(AppKey).parseClaimsJws(token).getBody() ;	
		
		
	return Long.parseLong(claims. getSubject())       ;
	}

	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(AppKey).parseClaimsJws(token);
			return !isTokenExpired(token);
		} catch (SignatureException e) {
			return false;
		}catch (MalformedJwtException e) {
			return false;
		}catch (ExpiredJwtException e) {
			return false;
		}catch (UnsupportedJwtException e) {
			return false;
		}catch (IllegalArgumentException e) {
			return false;
		}
	
	
	
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(AppKey).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date()) ;
	}
	

}
