package com.example.demo.excel.reader;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Transaction;
import com.example.demo.repository.TransactionRepository;

@Component
@Qualifier("transactionExcelReader")
public class TransactionExcelReader extends AbsExcelReader {

	private static final int DATA_BEGIN_ROW = 6;
	private static final int DATA_BEGIN_CELL = 1;

	@Autowired
	private TransactionRepository transactionRepository;
	@Override
	public void readDataFromWorkbook(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0);
		saveTransactions(workbook, sheet);
	}

	private void saveTransactions(Workbook workbook, Sheet sheet) {
		int lastRow = sheet.getLastRowNum();

		List<Transaction> transactions = new ArrayList<>();
		for (int rowIndex = DATA_BEGIN_ROW; rowIndex <= lastRow; rowIndex++) {
			Transaction transaction = new Transaction();
			Row row = sheet.getRow(rowIndex);

			int cellIndex = DATA_BEGIN_CELL;

			// Set transaction date
			Cell cell = row.getCell(cellIndex);
			transaction.setTransactionDateTime(cell.getStringCellValue());

			// Set content
			cell = row.getCell(++cellIndex);
			transaction.setContent(cell.getStringCellValue());

			// Set amount
			cell = row.getCell(++cellIndex);
			transaction.setAmount(cell.getStringCellValue());

			// Set Type
			cell = row.getCell(++cellIndex);
			transaction.setType(cell.getStringCellValue());
			
			transactions.add(transaction);
		}
		
		transactionRepository.saveAll(transactions);
	}

}
