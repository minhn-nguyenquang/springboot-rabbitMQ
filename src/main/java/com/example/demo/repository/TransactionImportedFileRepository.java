package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionImportedFile;

@Repository
public interface TransactionImportedFileRepository extends CrudRepository<TransactionImportedFile, Long> {

	@Query("SELECT e "
			+ " FROM #{#entityName} e "
			+ "	WHERE e.id = ?1")
	Optional<TransactionImportedFile> getById(Long fileId);
}
