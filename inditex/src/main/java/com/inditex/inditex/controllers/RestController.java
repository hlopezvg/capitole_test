package com.inditex.inditex.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.inditex.inditex.exceptions.OrderExceptions;
import com.inditex.inditex.persistence.entitites.Brand;
import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.services.PriceService;
import com.inditex.inditex.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RequestMapping("/api")
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private PriceService priceService;

    private final PriceRepository priceRepository;

    public RestController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @GetMapping("/orders")
    List<Price> getAll(){
        return priceRepository.findAll();
    }


    @GetMapping("/order/{id}")
    Price getOne(@PathVariable Long id){
        return priceRepository.findById(id)
                .orElseThrow(() -> new OrderExceptions(id));
    }


    @GetMapping(value = "/prices/{dateQuery}/{productId}/{brandId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    Price getPrice(@PathVariable String dateQuery,
            @PathVariable String productId,
            @PathVariable Long brandId){
        Price price = priceRepository.findPriceByProductId(Util.convertStringtoDate(dateQuery), productId, brandId);
        return price;
    }

    @PostMapping("/order")
    Price saveOrderEntity(@RequestBody Price price){
        return priceRepository.save(price);
    }

    @GetMapping("/healthcheck")
    String getOne(){
        return Util.healtCheck();
    }


}
