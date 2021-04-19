package com.inditex.inditex.services;

import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.util.Util;
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
        System.out.println("The size of the list price before applying any filter: " + price.size());

        //Esta es una forma de validar las fechas a traves del stream, lo ideal es
        //Quizas dejarlo del lado del JPA.

        //Filter dates for the same date/Filtar las fechas que sean del mismo dia

        //PRIORITY: Desambiguador de aplicación de precios.
        //Si dos tarifas coinciden en un rago de fechas se aplica la de mayor
        // prioridad (mayor valor numérico).


        List<Price> filterDatesAndPriority = new ArrayList<>();
        price.stream()
                .filter(price1 -> Util.calculateDateSameDay(date, price1.getStartDate(), price1.getEndDate()))
                .filter(price2 ->
                        price2.getPriority() > 0L)
                .forEach(filterDatesAndPriority::add);

        System.out.println("The size of the list after applying any filter: " + filterDatesAndPriority.size());

        return filterDatesAndPriority;

    }
}
