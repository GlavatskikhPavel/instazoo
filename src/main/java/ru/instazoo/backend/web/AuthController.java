package ru.instazoo.backend.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.instazoo.backend.payload.request.LoginRequest;
import ru.instazoo.backend.payload.request.SingUpRequest;
import ru.instazoo.backend.payload.response.Info;
import ru.instazoo.backend.payload.response.JWTTokenSuccessResponse;
import ru.instazoo.backend.security.JWTTokenProvider;
import ru.instazoo.backend.security.SecurityConstants;
import ru.instazoo.backend.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final UserService userService;


    @PostMapping("/singin")
    public ResponseEntity<Object> authenticationUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTTokenSuccessResponse(jwt, true));
    }

    @PostMapping("/singup")
    public ResponseEntity<Object> registerUser(@RequestBody SingUpRequest request) {
        userService.createUser(request);
        return ResponseEntity.ok(new Info("User registration successfully", true));
    }
}
