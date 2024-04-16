package org.sopt.carrotmarket.Service;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotmarket.DTO.GoodsSellDto;
import org.sopt.carrotmarket.Domain.Goods;
import org.sopt.carrotmarket.Repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoodsService {

    private final GoodsRepository goodsRepository;

    @Transactional
    public String createGoodsForSell(GoodsSellDto goodsSellDto) {
        Goods goods = Goods.builder()
                .sellerId(goodsSellDto.sellerId())
                .title(goodsSellDto.title())
                .suggestOption(goodsSellDto.suggestOption())
                .description(goodsSellDto.description() == null ? "" : goodsSellDto.description())
                .saleType(goodsSellDto.saleType())
                .location(goodsSellDto.location())
                .price(goodsSellDto.price())
                .build();

        return goodsRepository.save(goods).getId().toString();
    }
}
