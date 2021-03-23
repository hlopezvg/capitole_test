package com.inditex.inditex.persistence.initialdata;

import com.inditex.inditex.persistence.entitites.Brand;
import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.BrandRepository;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SetInitialData {

    private static final Logger log = LoggerFactory.getLogger(SetInitialData.class);

    @Bean
    CommandLineRunner populateDateBase(PriceRepository priceRepository,
                                       BrandRepository brandRepository){
        return args -> {
            Brand brandEntity = new Brand("ZARA");
            log.info("Loading data: " + brandRepository.save(brandEntity));

            Price pricesEntity = new Price(
                    Util.convertStringtoDate("2020-06-14-00.00.00"),
                    Util.convertStringtoDate("2020-12-31-23.59.59"),
                    1L,"35455",0L,35.50, "EUR", brandEntity);

            log.info("Loading data: " + priceRepository.save(pricesEntity));

            Price pricesEntity2 = new Price(
                    Util.convertStringtoDate("2020-06-14-15.00.00"),
                    Util.convertStringtoDate("2020-06-14-18.30.00"),
                    2L,"35455",1L,25.45, "EUR", brandEntity);

            log.info("Loading data: " + priceRepository.save(pricesEntity2));



            Price pricesEntity3 = new Price(
                    Util.convertStringtoDate("2020-06-15-00.00.00"),
                    Util.convertStringtoDate("2020-06-15-11.00.00"),
                    3L,"35455",1L,30.50, "EUR", brandEntity);

            log.info("Loading data: " + priceRepository.save(pricesEntity3));

            Price pricesEntity4 = new Price(
                    Util.convertStringtoDate("2020-06-15-16.00.00"),
                    Util.convertStringtoDate("2020-12-31-23.59.59"),
                    4L,"35455",1L,38.95, "EUR", brandEntity);

            log.info("Loading data: " + priceRepository.save(pricesEntity4));

        };
    }
}
