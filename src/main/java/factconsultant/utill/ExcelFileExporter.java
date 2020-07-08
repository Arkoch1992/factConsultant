package factconsultant.utill;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import factconsultant.model.Customer;



public class ExcelFileExporter {
	private static String columnHead[] = { "SlNo", "Customer Name", "PhoneNo","EmailId", "Message" };
	public static ByteArrayInputStream contactListToExcelFile(List<Customer> customers) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Customers");
			
			Row row = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        // Creating header
	  
	        Cell cell =null;
	        for (int i = 0; i < columnHead.length; i++) {
				  cell = row.createCell(i);
				  cell.setCellValue(columnHead[i]);
				  cell.setCellStyle(headerCellStyle);
				}
	        
	        int rowNum=1;
	        int rowColumn=1;
	        // Creating data rows for each customer
	        for(Customer customer: customers) {
	        	Row dataRow = sheet.createRow(rowNum ++);

	        	dataRow.createCell(0).setCellValue(rowColumn++);
	        	dataRow.createCell(1).setCellValue(customer.getName());
	        	dataRow.createCell(2).setCellValue(customer.getPhoneNo());
	        	dataRow.createCell(3).setCellValue(customer.getEmailId());
	        	dataRow.createCell(4).setCellValue(customer.getMessage());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
