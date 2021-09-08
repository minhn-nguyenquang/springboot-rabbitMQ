package com.example.demo.model.request;

import lombok.Data;

@Data
public class TransactionFileRequest {

	private byte[] fileData;
	private String contentType;
	private Long fileId;
}
