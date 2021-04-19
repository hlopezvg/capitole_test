package com.inditex.inditex.persistence.jpa;

import com.inditex.inditex.persistence.entitites.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p, b FROM Price p JOIN  p.brand b WHERE b.Id= :brandId and p.productId= :productId " +
            " and p.endDate >= :dateQuery")
    List<Price> findPriceByProductId(@Param("dateQuery") Date dateQuery,
                                     @Param("productId") String productId,
                                     @Param("brandId") Long brandId);

}
