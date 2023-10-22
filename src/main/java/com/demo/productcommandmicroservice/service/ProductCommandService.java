package com.demo.productcommandmicroservice.service;

import com.demo.productcommandmicroservice.dto.ProductEvent;
import com.demo.productcommandmicroservice.entity.Product;
import com.demo.productcommandmicroservice.enums.ExceptionMessage;
import com.demo.productcommandmicroservice.enums.TypeEvent;
import com.demo.productcommandmicroservice.exception.ProductBusinessException;
import com.demo.productcommandmicroservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductCommandService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ProductCommandService.class);
    private static final String TOPIC = "product-event-topic";

    public Product createProduct(ProductEvent productEvent){
        Product product= productRepository.save(productEvent.getProduct());
        ProductEvent event = new ProductEvent(TypeEvent.createdProduct.getValeur(), product);
        logger.info(String.format("#### -> Producing message on create product -> %s", product));
        kafkaTemplate.send(TOPIC,event);
        return product;
    }

    public Product updateProduct(ProductEvent productEvent, Long id)  {
        Product existingProduct =productRepository.findById(id).orElseThrow(()-> new ProductBusinessException(ExceptionMessage.ProductNoutFound.getValeur(),id));
        Product product = productEvent.getProduct();
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        Product persistedProduct= productRepository.save(existingProduct);
        ProductEvent event = new ProductEvent(TypeEvent.updatedProduct.getValeur(), persistedProduct);
        logger.info(String.format("#### -> Producing message on update persistedProduct -> %s", persistedProduct));
        kafkaTemplate.send(TOPIC,event);
        return persistedProduct;
    }
}
