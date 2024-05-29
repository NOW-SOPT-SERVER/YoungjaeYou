package org.geniuus.practice.Service.dto;

import org.geniuus.practice.domain.Part;

public record MemberCreateRequest(
        String name,
        String password,
        Part part,
        int age
) {
}
