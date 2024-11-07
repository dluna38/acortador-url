package co.acortador.security.jwt;

import co.acortador.exceptions.ValidationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private final long extraMillisExpirationToken = 1000L*60*60*24;

    private final Environment env;

    public static final String SECRET_KEY= "4034403339373640252637215478263821445036356d4e265378395278383637";

    public JwtService(Environment env) {
        this.env = env;
    }

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }
    protected String extractUsername(Claims claims) {
        return claims.getSubject();
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    protected Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSignInKey() {
        final String key = env.getProperty("JWT_KEY");
        if(key == null) throw new ValidationException("key jwt","jwt_key missing");
        byte[] keyBytes = Decoders.BASE64.decode(key);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + extraMillisExpirationToken))
                .claim("roles",userDetails.getAuthorities().toString())
                .claims(extraClaims)
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();

    }

    public String generateToken(UserDetails userDetails){
        return generateToken(Collections.emptyMap(),userDetails);
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        return !isTokenExpired(token) && checkUser(token,userDetails);
    }
    protected boolean isTokenValid(Claims claims,UserDetails userDetails){
        return !isTokenExpired(claims) && checkUser(claims,userDetails);
    }
    private boolean checkUser(String token,UserDetails userDetails){
        Claims claims = extractAllClaims(token);
        try {
            return claims.getSubject().equals(userDetails.getUsername()) && claims.get("roles").equals(userDetails.getAuthorities().toString());
        } catch (Exception e) {
            return false;
        }
    }
    private boolean checkUser(Claims claims,UserDetails userDetails){
        try {
            return claims.getSubject().equals(userDetails.getUsername()) && claims.get("roles").equals(userDetails.getAuthorities().toString());
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }


}

