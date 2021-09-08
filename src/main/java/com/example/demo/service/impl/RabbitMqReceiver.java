package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.demo.entity.TransactionImportedFile;
import com.example.demo.excel.reader.AbsExcelReader;
import com.example.demo.exception.ApplicationException;
import com.example.demo.model.request.TransactionFileRequest;
import com.example.demo.repository.TransactionImportedFileRepository;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMqReceiver.class);
	
	@Autowired
	@Qualifier("transactionExcelReader")
	private AbsExcelReader excelReader;
	
	@Autowired
	private TransactionImportedFileRepository transactionImportedFileRepository;
	@Override
	public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
		
	}

	@RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(TransactionFileRequest fileRequest) {
        logger.info("File Details Received is.. "+ fileRequest.getContentType());
        excelReader.readExcelFile(fileRequest.getFileData());
        
        TransactionImportedFile transactionImportedFile = transactionImportedFileRepository
        		.getById(fileRequest.getFileId())
        		.orElseThrow(() -> new ApplicationException("File with id: "+ fileRequest.getFileId() + " not found"));
        
        transactionImportedFile.setStatus("completed");
        transactionImportedFile = transactionImportedFileRepository.save(transactionImportedFile);
    }
}
