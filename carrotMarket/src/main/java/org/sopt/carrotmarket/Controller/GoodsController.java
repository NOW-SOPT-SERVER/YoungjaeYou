package org.sopt.carrotmarket.Controller;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotmarket.DTO.GoodsSellDto;
import org.sopt.carrotmarket.Service.GoodsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @PostMapping
    public ResponseEntity createGoodsForSell(@RequestBody GoodsSellDto goodsSellDto) {
        return ResponseEntity.created(URI.create(goodsService.createGoodsForSell(goodsSellDto))).build();
    }

}
