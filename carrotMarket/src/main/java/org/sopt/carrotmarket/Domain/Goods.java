package org.sopt.carrotmarket.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long sellerId;
    private String title;
    @Enumerated(EnumType.STRING)
    private SaleType saleType;

    private int price;
    private boolean suggestOption;
    private String description;
    private String location;
}
