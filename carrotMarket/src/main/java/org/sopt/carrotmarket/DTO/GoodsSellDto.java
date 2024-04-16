package org.sopt.carrotmarket.DTO;

import org.sopt.carrotmarket.Domain.SaleType;

import java.io.Serializable;

public record GoodsSellDto(
        Long sellerId,
        String title,
        SaleType saleType,
        int price,
        boolean suggestOption,
        String description,
        String location) {
}