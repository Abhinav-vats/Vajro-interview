package com.myshopify.Vajrointerview.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.myshopify.Vajrointerview.Entity.FinalResponseDTO;
import com.myshopify.Vajrointerview.dto.Filter;
import com.myshopify.Vajrointerview.serivice.VajroInterviewProductService;

@RestController
@RequestMapping("/product")
public class VajroInterviewController {

	private final static Logger logger = LogManager.getLogger(VajroInterviewController.class);

	@Autowired
	VajroInterviewProductService interviewProductService;

	@GetMapping("/all")
	ResponseEntity<?> findAllDataPage(
			@RequestParam(required = false, defaultValue = "id") final String sortBy,
			@RequestParam(required = false, defaultValue = "0") final String page,
			@RequestParam(required = false, defaultValue = "0") final String isReverse,
			@RequestParam(required = false, defaultValue = "0") final String inStock,
			@RequestParam(required = false, defaultValue = "0") final String outOfStock,
			@RequestParam(required = false, defaultValue = "10000000") 
			final String priceLessThan,
			@RequestParam(required = false, defaultValue = "0") final String priceGreaterThan,
			@RequestHeader(required = true) final String authorization) {

		logger.info("Entered: findAllDataPage");

		final Filter filter = new Filter();

		filter.setInStock(inStock);
		filter.setOutOfStock(outOfStock);
		filter.setPriceGreaterThan(priceGreaterThan);
		filter.setPriceLessThan(priceLessThan);
		ResponseEntity<?> responseEntity = null;

		try {
			responseEntity = new ResponseEntity<FinalResponseDTO>(interviewProductService.getAllDataPage(sortBy,
					Integer.parseInt(page), isReverse, filter, authorization), HttpStatus.OK);
		} catch (HttpClientErrorException e) {
			responseEntity = new ResponseEntity<Status>(new Status(e.getStatusCode(), e.getMessage(), new Date()),
					e.getStatusCode());

		}

		logger.info("Exiting: findAllDataPage");
		return responseEntity;

	}

}

class Status {

	private HttpStatus status;
	private String errorMessage;

	private Date timestamp;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Status(HttpStatus status, String errorMessage, Date timestamp) {
		super();
		this.status = status;
		this.errorMessage = errorMessage;
		this.timestamp = timestamp;
	}

}
