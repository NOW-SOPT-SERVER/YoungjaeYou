package org.geniuus.practice.Service.dto;

public record MemberResponseWithTokens(
        String accessToken,
        String refrshToken,
        Long memberId
) {

    public static MemberResponseWithTokens of(
            String accessToken,
            String refrshToken,
            Long memberId
    ) {
        return new MemberResponseWithTokens(accessToken, refrshToken, memberId);
    }
}


