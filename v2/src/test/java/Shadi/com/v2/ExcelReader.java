package Shadi.com.v2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelReader {
	public FileInputStream fis = null ;
	private XSSFWorkbook workbook =null;
	private XSSFSheet sheet = null;
	private XSSFRow row   =null;
	private XSSFCell cell = null;
	
	public ExcelReader(String path) {
		
		//String currentdir = System.getProperty("user.dir");
		 try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			}
		  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		
	}
	public int gettotalrow(String sheetname) {
		int rowcount = 1;
		int index =workbook.getSheetIndex(sheetname);
		if(index == -1) {
			System.out.println("No sheet found");
		}else {
			
			sheet = workbook.getSheetAt(index);
			rowcount = sheet.getLastRowNum()+1;
			
		}
		return rowcount;
	  }	
	
	public String getdataofrow(String sheetname, int colnum,int rownum) {
		int index =workbook.getSheetIndex(sheetname);
		if(index == -1) {
			String nosheet ="No sheet found";
				return nosheet;
		}else {
			
			sheet = workbook.getSheetAt(index);
		}
		row =sheet.getRow(rownum-1);
		if (row==null) {
			
			return "";	
		}
		cell = row.getCell(colnum);
		if (cell==null) {
			return"";
			
		}
		if(cell.getCellType()==CellType.STRING) {
			
			return cell.getStringCellValue();
		}else if (cell.getCellType()==CellType.NUMERIC){
			String cellText  = String.valueOf(cell.getNumericCellValue());
			return cellText;
		}else
			return "";
		
		
	} 
	
	}
