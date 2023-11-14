package com.shop.domain;

import lombok.Data;

@Data
public class Item {

    private Long id;
    private String itemName;
    private int price;
    private int quantity;

}
