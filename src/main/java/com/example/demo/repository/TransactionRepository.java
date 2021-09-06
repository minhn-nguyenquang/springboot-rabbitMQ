package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
