package com.example.demo.model.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TransactionResponse {

	private String content;

	private Double amount;

	private String type;

	private LocalDateTime transactionDateTime;
}
