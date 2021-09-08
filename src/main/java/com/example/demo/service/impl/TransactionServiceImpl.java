package com.example.demo.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.TransactionImportedFile;
import com.example.demo.excel.reader.AbsExcelReader;
import com.example.demo.exception.ApplicationException;
import com.example.demo.model.request.TransactionFileRequest;
import com.example.demo.model.response.TransactionResponse;
import com.example.demo.repository.TransactionImportedFileRepository;
import com.example.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	
	@Autowired
	private RabbitMqSender rabbitMqSender;
	@Autowired
	private TransactionImportedFileRepository transactionImportedFileRepository;
	
	@Override
	public List<TransactionResponse> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void importTransactionFromExcelFile(MultipartFile file) {
		TransactionImportedFile transactionImportedFile = new TransactionImportedFile();
		transactionImportedFile.setName(file.getOriginalFilename());
		transactionImportedFile.setStatus("processing");
		
		transactionImportedFile = transactionImportedFileRepository.save(transactionImportedFile);
		sendFileToRabbitMq(file, transactionImportedFile);
	}
	
	private void sendFileToRabbitMq(MultipartFile file, TransactionImportedFile transactionImportedFile) {
		try {
			byte[] fileData = file.getBytes();
			String fileType = file.getContentType();
			TransactionFileRequest transactionFileRequest = new TransactionFileRequest();
			transactionFileRequest.setFileData(fileData);
			transactionFileRequest.setContentType(fileType);
			transactionFileRequest.setFileId(transactionImportedFile.getId());
			rabbitMqSender.sendFile(transactionFileRequest);
		} catch (IOException e) {
			throw new ApplicationException("Exception file: "+ e);
		}
		
	}

}
