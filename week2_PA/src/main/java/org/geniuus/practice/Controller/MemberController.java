package org.geniuus.practice.Controller;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Service.MemberService;
import org.geniuus.practice.Service.dto.MemberCreateDto;
import org.geniuus.practice.Service.dto.MemberFindDto;
import org.geniuus.practice.Service.dto.MembersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity createMember(
            @RequestBody MemberCreateDto memberCreateDto
    ) {
        return ResponseEntity.created(URI.create(memberService.createMember(memberCreateDto)))
                .build();
    }

    @GetMapping
    public ResponseEntity<List<MembersDto>> findMembers() {
        return ResponseEntity.ok(memberService.findMembers());
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberFindDto> findMemberById(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.findMemberById(memberId));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity deleteMember(
            @PathVariable Long memberId
    ) {
        memberService.deleteMemberById(memberId);
        return ResponseEntity.noContent().build();
    }

}
