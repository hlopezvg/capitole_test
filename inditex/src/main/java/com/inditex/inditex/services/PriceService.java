package com.inditex.inditex.services;

import com.inditex.inditex.persistence.entitites.Brand;
import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.BrandRepository;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    BrandRepository brandRepository;

    public Price findPriceByBrandId(String productId, Long brandId, Date date){


        Price price = priceRepository.findPriceByProductId(date, productId, brandId);

        return price;

    }
}
