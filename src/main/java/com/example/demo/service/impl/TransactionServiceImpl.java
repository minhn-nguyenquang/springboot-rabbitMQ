package com.example.demo.service.impl;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.TransactionImportedFile;
import com.example.demo.excel.reader.AbsExcelReader;
import com.example.demo.model.response.TransactionResponse;
import com.example.demo.repository.TransactionImportedFileRepository;
import com.example.demo.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	@Qualifier("transactionExcelReader")
	private AbsExcelReader excelReader;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Value("${javainuse.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${javainuse.rabbitmq.routingkey}")
	private String routingkey;
	
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
		//excelReader.readExcelFile(file);
		rabbitTemplate.convertAndSend(exchange, routingkey, "hello");

	}

}
