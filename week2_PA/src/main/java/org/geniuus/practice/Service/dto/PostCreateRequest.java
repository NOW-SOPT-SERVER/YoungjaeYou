package org.geniuus.practice.Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record PostCreateRequest(
        @Size(max = 300, message = "포스트 제목이 최대 글자 수(300자)를 초과했습니다.") @NotBlank(message = "포스트 제목은 비어있거나, 공백으로만 이루어져있을 수 없습니다.") String title,
        @NotBlank(message = "포스트 내용은 비어있거나, 공백으로만 이루어져있을 수 없습니다.") String content,
        Boolean visibility
) {
}
