package org.geniuus.practice.Controller;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.geniuus.practice.Repository.MemberRepository;
import org.geniuus.practice.Service.MemberService;
import org.geniuus.practice.Service.dto.MemberCreateDto;
import org.geniuus.practice.Settings.ApiTest;
import org.geniuus.practice.domain.Part;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MemberControllerTest extends ApiTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Nested
    @DisplayName("멤버 생성 테스트")
    public class createMember {

        @Test
        @DisplayName("요청 성공 테스트")
        public void createMemberSuccess() throws Exception {
            // given
            final var request = new MemberCreateDto(
                    "geniuus",
                    Part.SERVER,
                    26
            );

            // when
            final var response = RestAssured
                    .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(request)
                    .when()
                    .post("/api/v1/member")
                    .then().log().all().extract();

            Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        }
    }
}
