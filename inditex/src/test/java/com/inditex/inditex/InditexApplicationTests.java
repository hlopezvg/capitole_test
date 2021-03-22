package com.inditex.inditex;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.inditex.exceptions.OrderExceptions;
import com.inditex.inditex.persistence.entitites.Brand;
import com.inditex.inditex.persistence.entitites.Price;
import com.inditex.inditex.persistence.jpa.PriceRepository;
import com.inditex.inditex.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;


import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
	void contextLoads() {

	}

	@Test
	public void givenSpecificFixedTime_getNow_thenGetFixedInstant() {
		String instantExpected = "2014-12-22T10:15:30Z";
		Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));

		Instant now = Instant.now(clock);

		assertEquals(instantExpected, now.toString());
	}

	@Test
	public void givenId_getSales_returnNotNull() {
		Price price = mock(Price.class);
		PriceRepository priceRepository = mock(PriceRepository.class);
		price.setId(1L);
		priceRepository.save(price);

		assertNotNull(priceRepository.findById(1l));


	}

	@Test
	public void givenValidPost_getSales_returnValidEntity() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-14-00.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);

//		PriceRepository orderRepository1 = mock(PriceRepository.class);
//		orderRepository1.save(price);
//
//		List<Price> allOrders = Arrays.asList(price);
//
//		given(orderRepository1.findAll()).willReturn(allOrders);

		this.mockMvc.perform(get("/api/orders")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].orderName", is(price.getProductId())));

	}

	@Test
	public void consumeHealthCheck_returnValidString() throws Exception {
		Brand brand = new Brand("ZARA");
		Price price = new Price(
				Util.convertStringtoDate("2020-06-14-00.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand);

		//'{"orderDate": "2012-04-23T18:25:43.511Z", "orderName": "REF-101", "total":"190.00"}'
		this.mockMvc.perform(post("/api/order", 42L)
				.contentType("application/json")
				.param("orderDate", "2012-04-23T18:25:43.511Z",
						"orderName", "REF-101", "total", "190.00")
				.content(objectMapper.writeValueAsString(price)))
				.andExpect(status().isOk());
	}

	@Test
	public void entityRepository_savingAndRespose_IsOk(){
		Brand brand = new Brand("ZARA");
		Price price = priceRepository.save(new Price(
				Util.convertStringtoDate("2020-06-14-00.00.00"),
				Util.convertStringtoDate("2020-12-31-23.59.59"),
				1L,"35455",0L,35.50, "EUR", brand));

		Price foundPrice = priceRepository.findById(
				price.getId())
				.orElseThrow(() -> new OrderExceptions(price.getId()));

		assertNotNull(foundPrice);
		assertThat(price.getStartDate().after(
				Util.convertFromLocalToDateAddingMonths(1)), is(Boolean.TRUE));


	}

}
