package com.inditex.inditex.persistence.jpa;

import com.inditex.inditex.persistence.entitites.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.productId= :productId and p.brand= :brandId and p.startDate >= :dateQuery")
    Price findPriceByProductId(@Param("dateQuery") Date dateQuery,
            @Param("productId") String productId,
            @Param("brandId") Long brandId);
}
