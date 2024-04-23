package org.geniuus.practice.Service.dto;

import org.geniuus.practice.domain.Part;

public record MemberCreateDto(
        String name,
        Part part,
        int age
) {
}
