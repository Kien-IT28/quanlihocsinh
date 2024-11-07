package com.thpt.quanlyhocsinh.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.thpt.quanlyhocsinh.dto.request.AuthenticationRequest;
import com.thpt.quanlyhocsinh.dto.request.IntrospectRequest;
import com.thpt.quanlyhocsinh.dto.response.AuthenticationResponse;
import com.thpt.quanlyhocsinh.dto.response.IntrospectResponse;
import com.thpt.quanlyhocsinh.entity.User;
import com.thpt.quanlyhocsinh.exception.AppException;
import com.thpt.quanlyhocsinh.exception.ErrorCode;
import com.thpt.quanlyhocsinh.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j  // Lombok sẽ tự động tạo log
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")

    String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();

        // Tạo verifier với khóa ký
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        // Phân tích token
        SignedJWT signedJWT = SignedJWT.parse(token);

        // Xác thực token
        boolean verified = signedJWT.verify(verifier);

        // Lấy thông tin thời gian hết hạn của token
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        // Xác nhận xem token có hết hạn không
        boolean isTokenValid = verified && expiryTime != null && expiryTime.after(new Date());

        // Log thông tin để debug
        log.info("Token: {}", token);
        log.info("Verified: {}", verified);
        log.info("Expiry Time: {}", expiryTime);

        // Trả về kết quả với token nếu cần thiết
        return IntrospectResponse.builder()
                .valid(isTokenValid)
                .authenticated(verified)  // Cập nhật để hiển thị trạng thái xác thực
                .token(token)  // Đảm bảo rằng token được trả về đúng cách
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            log.error(request.getUsername(), "Lỗi xác thực Token (Authentication failed)");
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(user);
        log.info("Successfully generated token for user: {}", request.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("devteria.com")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())) // Time Token 1h
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Failed to create token for user: {}", user, e);
            throw new RuntimeException(e);
        }
    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(stringJoiner :: add);
        }
        return stringJoiner.toString();
    }
}
