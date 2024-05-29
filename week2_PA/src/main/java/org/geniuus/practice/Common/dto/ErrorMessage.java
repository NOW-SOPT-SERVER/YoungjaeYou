package org.geniuus.practice.Common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    MEMBER_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 사용자가 존재하지 않습니다."),
    BLOG_FORBIDDEN_BY_MEMBER_EXCEPTION(HttpStatus.FORBIDDEN.value(), "ID에 해당하는 사용자가 해당 블로그에 쓰기 권한이 없습니다."),
    POST_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "검색할 포스트가 존재하지 않습니다"),
    BLOG_NOT_FOUND_BY_ID_EXCEPTION(HttpStatus.NOT_FOUND.value(), "ID에 해당하는 블로그가 존재하지 않습니다"),
    JWT_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "사용자의 로그인 검증을 실패했습니다."),
    PASSWORD_NOT_MATCHED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "해당 유저의 비밀번호가 일치하지 않습니다."),
    ;

    private final int status;
    private final String message;
}