package dtalk.security.token;

import dtalk.dto.user.UserDetailDTO;
import dtalk.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final String secretKey = "DSCache";
    private final long tokenValidTime = 30 * 60 * 10000;
    private final UserService userService;

    public String createToken(String userId, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userId); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과 signature 에 들어갈 secret값 세팅
                .compact();

    }
    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetailDTO userDetails = new UserDetailDTO(userService.findByUserId(this.getUserPk(token)));
        System.out.println("토큰 인증 조회 =================");
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰의 유효성 + 만료일자 확인

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("jwt 서명 확인 x");
        } catch (MalformedJwtException e) {
            System.out.println("jwt 올바르게 구성 x");
        } catch (ExpiredJwtException e) {
            SecurityContextHolder.clearContext();
            System.out.println("jwt 기간 종료");
        } catch (UnsupportedJwtException e) {
            System.out.println("jwt 형식이 다름");
        } catch (IllegalArgumentException e) {
            System.out.println("jwt 내용이 비었음");
        } catch (NullPointerException e) {
            System.out.println("jwt null");
        }
        return false;
    }
}



