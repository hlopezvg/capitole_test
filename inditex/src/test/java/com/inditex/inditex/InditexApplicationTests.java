package com.inditex.inditex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.inditex.persistence.entitites.Brand;
import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;


import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = InditexApplication.class)
@AutoConfigureMockMvc
class InditexApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private PriceRepository priceRepository;


	@Test
	void contextLoads() {}

	@Test
	public void givenId_getSales_returnNotNull() {
		Price price = mock(Price.class);
		PriceRepository priceRepository = mock(PriceRepository.class);
		price.setId(1L);
		priceRepository.save(price);

		assertNotNull(priceRepository.findById(1l));

	}

	@Test
	public void givenValidPost_getPrice_returnValidEntityWithPriorityExpectedOnlyOne() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-14-00.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);


		this.mockMvc.perform(get("/api/prices/{dateQuery}/{productId}/{brandId}",
				"2020-12-31-23.59.59", 35455, 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].productId", is(price.getProductId())));

	}

	@Test
	public void givenValidPost_getPriceDay14Hour10_returnEmpty() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-14-10.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);


		this.mockMvc.perform(get("/api/prices/{dateQuery}/{productId}/{brandId}",
				"2020-06-14-10.00.00", 35455, 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void givenValidPost_getPriceDay14Hour16_returnEmptyy() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-14-16.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);


		this.mockMvc.perform(get("/api/prices/{dateQuery}/{productId}/{brandId}",
				"2020-06-14-16.00.00", 35455, 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));

	}

	@Test
	public void givenValidPost_getPriceDay14Hour21_returnEmptyy() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-14-21.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);


		this.mockMvc.perform(get("/api/prices/{dateQuery}/{productId}/{brandId}",
				"2020-06-14-21.00.00", 35455, 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));

	}


	@Test
	public void givenValidPost_getPriceDay16Hour21_returnEmpty() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-16-21.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);


		this.mockMvc.perform(get("/api/prices/{dateQuery}/{productId}/{brandId}",
				"2020-06-16-21.00.00", 35455, 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));

	}



}
