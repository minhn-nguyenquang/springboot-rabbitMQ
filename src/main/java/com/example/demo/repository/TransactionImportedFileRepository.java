package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionImportedFile;

@Repository
public interface TransactionImportedFileRepository extends CrudRepository<TransactionImportedFile, Long> {

}
