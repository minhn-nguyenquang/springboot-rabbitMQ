package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.response.TransactionImportedFileResponse;

public interface TransactionService {

	List<TransactionImportedFileResponse> getTransactionImportedFiles();
	
	TransactionImportedFileResponse getTransactionImportedFileById(Long id);
	
	void importTransactionFromExcelFile(MultipartFile file);
}
