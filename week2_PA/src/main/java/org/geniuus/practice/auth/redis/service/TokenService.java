package org.geniuus.practice.auth.redis.service;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.UnauthorizedException;
import org.geniuus.practice.Common.dto.ErrorMessage;
import org.geniuus.practice.Common.jwt.JwtTokenProvider;
import org.geniuus.practice.auth.UserAuthentication;
import org.geniuus.practice.auth.redis.domain.Token;
import org.geniuus.practice.auth.redis.repository.RedisTokenRepository;
import org.geniuus.practice.auth.redis.service.dto.TokenRefreshDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final RedisTokenRepository redisTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void saveRefreshToken(Long memberId, String refreshToken) {
        redisTokenRepository.save(
                Token.of(memberId, refreshToken));
    }

    public TokenRefreshDto reissueAccessTokenByRefreshToken(TokenRefreshDto tokenRefreshRequest) {
        Long tokenId = redisTokenRepository.findByRefreshToken(tokenRefreshRequest.token()).orElseThrow(
                () -> new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION)
        ).getId();
        Long requestId = tokenRefreshRequest.memberId();
        validateRequestIdWithTokenId(requestId, tokenId);
        String accessToken = jwtTokenProvider.issueAccessToken(UserAuthentication.createUserAuthentication(requestId));
        return TokenRefreshDto.of(requestId, accessToken);
    }

    public void validateRequestIdWithTokenId(Long requestId, Long tokenId) {
        if (!requestId.equals(tokenId)) {
            throw new UnauthorizedException(ErrorMessage.JWT_UNAUTHORIZED_EXCEPTION);
        }
    }
}
