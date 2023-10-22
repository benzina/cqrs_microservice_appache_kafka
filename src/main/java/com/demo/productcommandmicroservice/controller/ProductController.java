package com.demo.productcommandmicroservice.controller;

import com.demo.productcommandmicroservice.dto.ProductEvent;
import com.demo.productcommandmicroservice.entity.Product;
import com.demo.productcommandmicroservice.service.ProductCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductCommandService productCommandService;

    @PostMapping
    public Product createProduct(@RequestBody ProductEvent productEvent){
        return productCommandService.createProduct(productEvent);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody ProductEvent productEvent, @PathVariable Long id)  {
        return productCommandService.updateProduct(productEvent,id);
    }
}
