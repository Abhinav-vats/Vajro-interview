package com.myshopify.Vajrointerview.serivice;


import com.myshopify.Vajrointerview.Entity.FinalResponseDTO;
import com.myshopify.Vajrointerview.dto.Filter;

public interface VajroInterviewProductService {

	FinalResponseDTO getAllDataPage(String sortBy, Integer page, String isReverse,
			Filter filter, final String authorization);
}
