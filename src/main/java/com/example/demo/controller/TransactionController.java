package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.ApiVersion;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping(value = ApiVersion.API_V1 + "/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping
	public ResponseEntity<String> index() {
		String body = "Hello";
	    return ResponseEntity.ok(body);
	}
	
	@RequestMapping(value = "/import-excel", method = RequestMethod.POST)
    public ResponseEntity<String> importExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
		transactionService.importTransactionFromExcelFile(file);
        return ResponseEntity.ok("The file is uploaded successfully");
    }
}
