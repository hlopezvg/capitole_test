package com.inditex.inditex.persistence.initialdata;

import com.inditex.inditex.persistence.entitites.Book;
import com.inditex.inditex.persistence.entitites.Brand;
import com.inditex.inditex.persistence.entitites.Page;
import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.BookRepository;
import com.inditex.inditex.persistence.jpa.BrandRepository;
import com.inditex.inditex.persistence.jpa.PageRepository;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


@Configuration
public class SetInitialData {

    private static final Logger log = LoggerFactory.getLogger(SetInitialData.class);

//    @Bean
//    CommandLineRunner populateDateBase(BookRepository bookRepository, PageRepository pageRepository){
//        return args ->{
//            // create a new book
//            Book book = new Book("Java 101", "John Doe", "123456");
//
//            // save the book
//            bookRepository.save(book);
//
//            // create and save new pages
//            pageRepository.save(new Page(12, "Introduction contents II", "Introduction", book));
//            pageRepository.save(new Page(655, "Java 8.1 contents", "Java 8", book));
//            pageRepository.save(new Page(950, "Concurrency contents II", "Concurrency", book));
//        };
//
//    }


    @Bean
    CommandLineRunner populateDateBase(PriceRepository priceRepository, BrandRepository brandRepository){
        return args -> {
            Brand brandEntity = new Brand("ZARA");
            log.info("Loading data: " + brandRepository.save(brandEntity));

            Price pricesEntity = new Price(
                    Util.convertStringtoDate("2020-06-14-00.00.00"),
                    Util.convertStringtoDate("2020-12-31-23.59.59"),
                    1L,"35455",0L,35.50, "EUR", brandEntity);

            log.info("Loading data: " + priceRepository.save(pricesEntity));

            Price pricesEntity2 = new Price(
                    Util.convertStringtoDate("2020-06-14-00.00.00"),
                    Util.convertStringtoDate("2020-12-31-23.59.59"),
                    1L,"35456",0L,35.50, "EUR", brandEntity);

            log.info("Loading data: " + priceRepository.save(pricesEntity2));
        };
    }
}
