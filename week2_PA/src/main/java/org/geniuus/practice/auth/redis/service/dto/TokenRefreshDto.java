package org.geniuus.practice.auth.redis.service.dto;

public record TokenRefreshDto(
        Long memberId,
        String token
) {
    public static TokenRefreshDto of(Long memberId, String token) {
        return new TokenRefreshDto(memberId, token);
    }
}
