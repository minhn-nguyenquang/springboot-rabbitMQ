package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "transaction")
public class Transaction extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "imported_file_id", nullable = false)
	private TransactionImportedFile importedFile;

	@Column(name = "content")
	private String content;

	@Column(name = "amount")
	private String amount;

	@Column(name = "type")
	private String type;

	@Column(name = "transaction_date_time")
	private String transactionDateTime;

}
