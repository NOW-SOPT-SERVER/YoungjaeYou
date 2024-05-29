package org.geniuus.practice.Service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.UnauthorizedException;
import org.geniuus.practice.Common.dto.ErrorMessage;
import org.geniuus.practice.Common.NotFoundException;
import org.geniuus.practice.Common.jwt.JwtTokenProvider;
import org.geniuus.practice.Repository.MemberRepository;
import org.geniuus.practice.Service.dto.*;
import org.geniuus.practice.auth.UserAuthentication;
import org.geniuus.practice.auth.redis.service.TokenService;
import org.geniuus.practice.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenService tokenService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberResponseWithTokens createMember (MemberCreateRequest memberCreateRequest) {
        Member member = memberRepository.save(
                Member.create(
                        memberCreateRequest.name(),
                        passwordEncoder.encode(memberCreateRequest.password()),
                        memberCreateRequest.part(),
                        memberCreateRequest.age())
        );

        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(member.getId()));
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(member.getId()));

        tokenService.saveRefreshToken(member.getId(), refreshToken);
        return MemberResponseWithTokens.of(accessToken, refreshToken, member.getId());
    }

    public MemberResponseWithTokens login(MemberLoginRequest memberLoginRequest) {
        Member member = memberRepository.findById(memberLoginRequest.memberId()).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION)
        );
        registerMember(memberLoginRequest.password(), member);

        String accessToken = jwtTokenProvider.issueAccessToken(
                UserAuthentication.createUserAuthentication(memberLoginRequest.memberId()));
        String refreshToken = jwtTokenProvider.issueRefreshToken(
                UserAuthentication.createUserAuthentication(memberLoginRequest.memberId()));
        tokenService.saveRefreshToken(member.getId(), refreshToken);
        return MemberResponseWithTokens.of(accessToken, refreshToken, memberLoginRequest.memberId());
    }

    public MemberFindDto findMemberById(Long memberId) {
        return MemberFindDto.of(memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다")
        ));
    }

    public List<MembersDto> findMembers() {
        return MembersDto.of(memberRepository.findAll());
    }

    @Transactional
    public void deleteMemberById(
            Long memberId
    ) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 사용자가 존재하지 않습니다."));
        memberRepository.delete(member);
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND_BY_ID_EXCEPTION)
        );
    }

    public void registerMember (String requestPassword, Member member) {
        if (!passwordEncoder.matches(requestPassword, member.getPassword()))
            throw new UnauthorizedException(ErrorMessage.PASSWORD_NOT_MATCHED_EXCEPTION);
    }

}
