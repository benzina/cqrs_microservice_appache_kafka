package com.demo.productcommandmicroservice.dto;

import com.demo.productcommandmicroservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEvent {
    private String typeEvent;
    private Product product;
}
