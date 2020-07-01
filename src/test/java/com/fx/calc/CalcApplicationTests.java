package com.fx.calc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fx.calc.config.GeneralConfig;
import com.fx.calc.models.dto.CalculateInvoiceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.math.BigDecimal;
import java.time.LocalDate;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(GeneralConfig.class)
public class CalcApplicationTests {
	@Autowired
	private MockMvc mvc;
	
	@Test
	public void validUsdAmountAndDate_ShouldSuceed() throws Exception {
		// We need this to ensure that LocalDate is serialized by jackson to the yyyy-MM-DD format
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		CalculateInvoiceDto payload = new CalculateInvoiceDto();
		payload.setUsdAmount(BigDecimal.valueOf(5));
		payload.setDate(LocalDate.now().minusDays(30).withDayOfMonth(1));

		mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculator/invoice")
			.content(mapper.writeValueAsString(payload))
			.accept("application/json")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.data.invoiceInCad", is(6.791475)));
	}

	@Test
	public void validUsdAmountAndDateOlder90Days_ShouldFail() throws Exception {
		// We need this to ensure that LocalDate is serialized by jackson to the yyyy-MM-DD format
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		CalculateInvoiceDto payload = new CalculateInvoiceDto();
		payload.setUsdAmount(BigDecimal.valueOf(5));
		payload.setDate(LocalDate.now().minusDays(1000).withDayOfMonth(1));

		mvc.perform(MockMvcRequestBuilders.post("/api/v1/calculator/invoice")
				.content(mapper.writeValueAsString(payload))
				.accept("application/json")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}
}
