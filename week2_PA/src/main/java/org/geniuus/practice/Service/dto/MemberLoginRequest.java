package org.geniuus.practice.Service.dto;

public record MemberLoginRequest(
        Long memberId,
        String password
) {
}
