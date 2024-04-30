package org.geniuus.practice.Service.dto;

public record BlogCreateRequest(
        String title,
        String description
) {
}