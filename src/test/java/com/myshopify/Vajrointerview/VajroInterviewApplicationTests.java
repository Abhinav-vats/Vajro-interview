package com.myshopify.Vajrointerview;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myshopify.Vajrointerview.controller.VajroInterviewController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VajroInterviewApplicationTests {

	private MockMvc mockMvc;

	@InjectMocks
	private VajroInterviewController vajroInterviewController;

	ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

	}

	@Test
	public void getAllDetailsTest() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(get("/product/all").header("Authorization", "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")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful()).andReturn();

		Assert.assertTrue(mvcResult.getResponse().getStatus() == 200);

	}

	@Test
	public void getAllDetailsParamsTest() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/product/all?page=3&isReverse=1&inStock=0&outOfStock=1&sortBy=title")
				.header("Authorization", "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")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andReturn();

		Assert.assertTrue(mvcResult.getResponse().getStatus() == 200);

	}

}
