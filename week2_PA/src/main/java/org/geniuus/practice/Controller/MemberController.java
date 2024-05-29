package org.geniuus.practice.Controller;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.dto.SuccessMessage;
import org.geniuus.practice.Common.dto.SuccessStatusResponse;
import org.geniuus.practice.Service.MemberService;
import org.geniuus.practice.Service.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<List<MembersDto>> findMembers() {
        return ResponseEntity.ok(memberService.findMembers());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberFindDto> findMemberById (@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity deleteMember (@PathVariable Long memberId) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/member")
    public ResponseEntity<MemberResponseWithTokens> createMember(
            @RequestBody MemberCreateRequest memberCreateRequest
    ) {
        MemberResponseWithTokens memberResponseWithTokens = memberService.createMember(memberCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", memberResponseWithTokens.memberId().toString())
                .body(memberResponseWithTokens);
    }

    @PostMapping("/member/login")
    public ResponseEntity<SuccessStatusResponse<MemberResponseWithTokens>> login(
            @RequestBody MemberLoginRequest memberLoginRequest
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.MEMBER_LOGIN_SUCCESS, memberService.login(memberLoginRequest)));
    }



}
