package com.vinit.stockmarket.stockmarket.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.vinit.stockmarket.stockmarket.model.Stock;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StockResourceControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void checkValid200ResponseFromPutapistock() throws JSONException {

		final String url = "/PATCH/api/stocks/3";

		Stock stock = new Stock();

		stock.setName("Oracle");
		stock.setCurrentprice(new BigDecimal(44.34));
		stock.setLastupdate(new Timestamp(System.currentTimeMillis()));
		restTemplate.put(url, stock);

		Stock updatedUser = restTemplate.getForObject("/GET/api/stocks/3", Stock.class);
		System.out.println("User updated details : ");

		
		JSONAssert.assertEquals("44.3400", String.valueOf(updatedUser.getCurrentprice()), false);
	}

	@Test
	public void checkValid200ResponseFromGETapistocks() throws JSONException {

		ResponseEntity<String> response = this.restTemplate.getForEntity("/GET/api/stocks", String.class);
		JSONAssert.assertEquals(String.valueOf(200), String.valueOf(response.getStatusCodeValue()), false);

	}

	@Test
	public void checkValid200ResponseFromGETapistockbyid() throws JSONException {

		ResponseEntity<String> response = this.restTemplate.getForEntity("/GET/api/stocks/1", String.class);
		JSONAssert.assertEquals(String.valueOf(200), String.valueOf(response.getStatusCodeValue()), false);

	}

	@Test
	public void checkValid200ResponseFromGETapistockbyidNotFound() throws JSONException {

		ResponseEntity<String> response = this.restTemplate.getForEntity("/GET/api/stocks/5", String.class);
		JSONAssert.assertEquals(String.valueOf(404), String.valueOf(response.getStatusCodeValue()), false);

	}

	@Test
	public void checkValid200ResponseFromGETapistockbyidNot() throws JSONException {

		ResponseEntity<String> response = this.restTemplate.getForEntity("/GET/api/stockss/1", String.class);
		JSONAssert.assertEquals(String.valueOf(404), String.valueOf(response.getStatusCodeValue()), false);

	}

	@Test
	public void checkValid200ResponseFromDeleteapistock() throws JSONException {

		this.restTemplate.delete("/DELETE/api/stocks/1", String.class);

		ResponseEntity<String> response = this.restTemplate.getForEntity("/GET/api/stockss/1", String.class);
		JSONAssert.assertEquals(String.valueOf(404), String.valueOf(response.getStatusCodeValue()), false);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void checkValid200ResponseFromPOSTapistock() throws JSONException {

		final String url = "/POST/api/stocks";

		Stock stock = new Stock();
		stock.setId(new Long(4));
		stock.setName("Tesla");
		stock.setCurrentprice(new BigDecimal(44.34));
		stock.setLastupdate(new Timestamp(System.currentTimeMillis()));

		ResponseEntity<Stock> response = restTemplate.postForEntity(url, stock, Stock.class);

		JSONAssert.assertEquals(String.valueOf(201), String.valueOf(response.getStatusCodeValue()), false);

	}

}