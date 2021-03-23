package com.inditex.inditex.services;

import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PriceService {
    @Autowired
    PriceRepository priceRepository;

    public List<Price> findPriceByProductId(Date date, String productId, Long brandId){

        List <Price> price = priceRepository.findPriceByProductId(date, productId, brandId);
        System.out.println("The size of the list price: " + price.size());
        //PRIORITY: Desambiguador de aplicación de precios.
        //Si dos tarifas coinciden en un rago de fechas se aplica la de mayor
        // prioridad (mayor valor numérico).

        List<Price> priceHigherPriority = new ArrayList<>();
        price.stream().filter(price1 ->
                price1.getPriority() > 0L)
                .forEach(priceHigherPriority::add);

        return priceHigherPriority;

    }
}
