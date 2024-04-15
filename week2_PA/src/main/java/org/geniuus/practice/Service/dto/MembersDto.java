package org.geniuus.practice.Service.dto;

import org.geniuus.practice.domain.Member;
import org.geniuus.practice.domain.Part;

import java.util.ArrayList;
import java.util.List;

public record MembersDto(
        String name,
        Part part,
        int age) {

    public static List<MembersDto> of(List<Member> members) {
        List<MembersDto> result = new ArrayList<>();
        for (Member m : members) {
            result.add(new MembersDto(m.getName(), m.getPart(), m.getAge()));
        }
        return result;
    }
}
