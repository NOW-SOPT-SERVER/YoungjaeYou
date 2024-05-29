package org.geniuus.practice.auth.redis.controller;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.dto.SuccessMessage;
import org.geniuus.practice.Common.dto.SuccessStatusResponse;
import org.geniuus.practice.auth.redis.service.TokenService;
import org.geniuus.practice.auth.redis.service.dto.TokenRefreshDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/reissuance")
    public ResponseEntity<SuccessStatusResponse<TokenRefreshDto>> reissueTokenByRefreshToken(
            @RequestBody TokenRefreshDto tokenRefreshRequest
            ) {
        return ResponseEntity.ok().body(
                SuccessStatusResponse.of(
                        SuccessMessage.TOKEN_REFRESH_SUCCESS,
                        tokenService.reissueAccessTokenByRefreshToken(tokenRefreshRequest)));
    }
}
