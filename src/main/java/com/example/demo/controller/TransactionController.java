package com.example.demo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.constant.ApiVersion;
import com.example.demo.model.response.TransactionImportedFileResponse;
import com.example.demo.service.TransactionService;

@RestController
@RequestMapping(value = ApiVersion.API_V1 + "/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/imported-files")
	public ResponseEntity<List<TransactionImportedFileResponse>> getAllFiles() {
	    return ResponseEntity.ok(transactionService.getTransactionImportedFiles());
	}
	
	@GetMapping("/imported-files/{id}")
	public ResponseEntity<TransactionImportedFileResponse> getFileById(@PathVariable Long id) {
	    return ResponseEntity.ok(transactionService.getTransactionImportedFileById(id));
	}
	
	@RequestMapping(value = "/import-excel"
			, method = RequestMethod.POST
			, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importExcelFile(@RequestPart("file") MultipartFile file) throws IOException {
		transactionService.importTransactionFromExcelFile(file);
        return ResponseEntity.ok("The file is uploaded successfully");
    }
}
