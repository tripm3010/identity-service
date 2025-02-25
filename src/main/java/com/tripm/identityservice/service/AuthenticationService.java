package com.tripm.identityservice.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tripm.identityservice.dto.request.AuthenticationRequest;
import com.tripm.identityservice.dto.request.IntrospectRequest;
import com.tripm.identityservice.dto.response.AuthenticationResponse;
import com.tripm.identityservice.dto.response.IntrospectResponse;
import com.tripm.identityservice.entity.User;
import com.tripm.identityservice.exception.AppException;
import com.tripm.identityservice.exception.ErrorCode;
import com.tripm.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AuthenticationService {

    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean matches = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!matches) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token  = generateToken(user);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("tripm.com")
                .issueTime(new Date())
                .expirationTime(new Date(new Date().getTime() + 1000 * 60 * 60))
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create JWS object", e);
            throw new RuntimeException(e);
        }
    }

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);
        return IntrospectResponse.builder()
                .valid(verified && expirationDate.after(new Date()))
                .build();
    }

    private String buildScope(User user) {
        StringJoiner scopes = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRole())) {
//            user.getRole().forEach(scopes::add);
        }
        return scopes.toString();
    }


}
