package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "transaction_imported_file")
public class TransactionImportedFile extends AuditEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "name")
    private String name;
	
	@Column(name = "status")
    private String status;
	

	@OneToMany(mappedBy="importedFile")
    private Set<Transaction> transactions;
}
