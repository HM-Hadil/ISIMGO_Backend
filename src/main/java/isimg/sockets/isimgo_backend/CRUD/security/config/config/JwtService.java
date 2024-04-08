package isimg.sockets.isimgo_backend.CRUD.security.config.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final String SECRET_KEY="Oj6D7wt0U3nAKFwPxc69GFDdbgwGhPmrHiXu7vP7xdCCpXVQdBWvqP3T01DaMPs+GECeOFvdWP1DvCMeZWp3BiE+dNSujFZTjU0i4k2usrVxv9tzE/iZ+7bN3Dh87d2zsVzXG/6edHYUZdBNAEW6kogRpaOs6bkObZsPXHZp/Ee+952Ucd1qBBPeSWbi16Gv0Do4l+oTNznuvfSu9b//ntg6mxaDqLqq+SqADwVdey2YSJR0rqyf7TkC6BRQ/y1el8z8SaQQ6HziESJZ4ytFyeS5bNeDhq2tJuOCVIDzVWT7SMFm0jZRqJd5V1WRQv82cQoVTXlBPQ6g2+sb/yukbC1xwTo8b25NwWcpFzF2p+8=\n";

    public String extractUserEmail(String token){
        return extractClaim(token,Claims::getSubject);
    }

    public <T>  T extractClaim(String token , Function<Claims,T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return  claimsTFunction.apply(claims);

    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);

    }

    public String generateToken(Map<String,Object> extractClamis, UserDetails userDetails ){

        return Jwts
                .builder()
                .setClaims(extractClamis)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+100*60*24))
                .signWith(getsigninKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username=extractUserEmail(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
     return    Jwts
                .parserBuilder()
                .setSigningKey(getsigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey getsigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
