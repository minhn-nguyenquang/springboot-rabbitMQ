package com.example.demo.excel.reader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public abstract class AbsExcelReader {

	protected abstract void readDataFromWorkbook(Workbook workbook);

    public void readExcelFile(MultipartFile multipartFile) throws IOException {
    	readExcelFile(multipartFile.getInputStream());
    }

    public void readExcelFile(byte[] file) {
    	if (file == null || file.length <= 0) {
    		return;
    	}
    	InputStream is = new ByteArrayInputStream(file);
    	readExcelFile(is);
    }
    
    public void readExcelFile(InputStream is) {
        try(Workbook workbook = new XSSFWorkbook(is)) {
            readDataFromWorkbook(workbook);
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    protected Object getCellValueByCellName(Workbook workbook, Sheet sheet, String cellName) {
        CellAddress cellAddress = new CellAddress(cellName);
        Row row = sheet.getRow(cellAddress.getRow());
        Cell cell = row.getCell(cellAddress.getColumn());
        return getCellValue(workbook, cell);
    }

    protected Object getCellValue(Workbook wb, Cell cell) {
        Object result = null;
        if(cell != null) {
            switch(cell.getCellType()) {
                case BOOLEAN:
                    result = cell.getBooleanCellValue();
                    break;
                case NUMERIC:
                    if(HSSFDateUtil.isCellDateFormatted(cell)) {
                        result = cell.getDateCellValue();
                    } else {
                        result = cell.getNumericCellValue();
                    }
                    break;
                case STRING:
                    result = cell.getStringCellValue();
                    break;
                case ERROR:
                    result = cell.getErrorCellValue();
                    break;

                // CELL_TYPE_FORMULA will never occur
				// Never coin cak
                case FORMULA:
					FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();
					switch(evaluator.evaluateFormulaCell(cell)) {
                        case BOOLEAN:
							result = cell.getBooleanCellValue();
                            break;
                        case NUMERIC:
							if(HSSFDateUtil.isCellDateFormatted(cell)) {
								result = cell.getDateCellValue();
							} else {
								result = cell.getNumericCellValue();
							}
                            break;
                        case STRING:
							result = cell.getStringCellValue();
                            break;
                    }
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}
