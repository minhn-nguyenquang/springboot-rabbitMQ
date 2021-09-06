package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.response.TransactionResponse;

public interface TransactionService {

	List<TransactionResponse> getAll();
	
	void importTransactionFromExcelFile(MultipartFile file);
}
