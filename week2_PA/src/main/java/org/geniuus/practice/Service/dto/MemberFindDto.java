package org.geniuus.practice.Service.dto;

import org.geniuus.practice.domain.Member;
import org.geniuus.practice.domain.Part;

public record MemberFindDto(
        String name,
        Part part,
        int age
) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getName(), member.getPart(), member.getAge());
    }
}
