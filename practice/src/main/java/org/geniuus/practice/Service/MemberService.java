package org.geniuus.practice.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Repository.MemberRepository;
import org.geniuus.practice.Service.dto.MemberCreateDto;
import org.geniuus.practice.Service.dto.MemberFindDto;
import org.geniuus.practice.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public String createMember(
            MemberCreateDto memberCreateDto
    ) {
        Member member = Member.create(memberCreateDto.name(), memberCreateDto.part(), memberCreateDto.age());
        memberRepository.save(member);
        return member.getId().toString();
    }

    public MemberFindDto findMemberById(Long memberId) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다")
        ));
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }
}
