package org.geniuus.practice.Common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum SuccessMessage {

    BLOG_CREATE_SUCCESS(HttpStatus.CREATED.value(), "블로그 생성이 완료되었습니다."),
    POST_CREATE_SUCCESS(HttpStatus.CREATED.value(), "포스팅이 완료되었습니다."),
    POST_FIND_SUCCESS(HttpStatus.OK.value(), "포스팅 검색이 완료되었습니다."),
    TOKEN_REFRESH_SUCCESS(HttpStatus.OK.value(), "토큰 재발급이 완료되었습니다."),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK.value(), "로그인이 완료되었습니다."),
    ;

    private final int status;
    private final String message;
}
