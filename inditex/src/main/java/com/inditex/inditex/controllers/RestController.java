package com.inditex.inditex.controllers;

import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.services.PriceService;
import com.inditex.inditex.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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


    @GetMapping(value = {"/prices/{dateQuery}",
            "/prices/{dateQuery}/{productId}", "/prices/{dateQuery}/{productId}/{brandId}"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<Price> getPrice(@PathVariable("dateQuery") String dateQuery,
                         @PathVariable("productId") String productId,
                         @PathVariable("brandId") Long brandId) {
        List<Price> price = priceService.findPriceByProductId(Util.convertStringtoDate(dateQuery), productId, brandId);
        return price;
    }


    @GetMapping("/healthcheck")
    String getOne() {
        return Util.healtCheck();
    }


}
