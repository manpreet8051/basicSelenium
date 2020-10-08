import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class TestClass {

	public static void main(String[] args) throws Exception
	{
		//table data print url 1. https://www.seleniumeasy.com/test/table-data-download-demo.html
		
	  WebDriver driver;
	  System.setProperty("webdriver.chrome.driver", "D:\\new sel\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("https://www.seleniumeasy.com/test/table-data-download-demo.html");

	  Thread.sleep(3000);
	  
	  //scroll using action class;
//	  WebElement nameHead = driver.findElement(By.xpath("//table//thead//tr//th[text()='Name']"));
//	  Actions actions =new Actions(driver);
//	  actions.moveToElement(nameHead).build().perform();
//	  
//
//	  
//	  WebElement table = driver.findElement(By.xpath("//table[@id='example']"));
//	  List<WebElement> rows = table.findElements(By.tagName("tr"));
//	  int rowsSize = rows.size();
//	  for(int row=1;row<rowsSize;row++) {
//		  List<WebElement> colm = rows.get(row).findElements(By.tagName("td"));
//		  for(int col=0;col<colm.size();col++) {
//			  System.out.print(colm.get(col).getText()+"|");
//		  }
//		  System.out.println("");
//	  }
	  
	  //Store values to excel
	  
	  String[][] tableValues;
	  final String FILEPATH = "D:\\new sel\\Store Data.xlsx";
	  WebElement table = driver.findElement(By.xpath("//table[@id='example']"));
	  List<WebElement> rows = table.findElements(By.tagName("tr"));
	  int totalRows = rows.size();
	  List<WebElement> columns = table.findElements(By.tagName("th"));
	  int totalColumns = columns.size();
	  tableValues = new String[totalRows-10][totalColumns];
	  
	  for(int row=1;row<=totalRows-10;row++) {
		  for(int col=1; col<=totalColumns;col++) {
			  if(row==1) {
				  tableValues[row-1][col-1]= driver.findElement(By.xpath("//table[@id='example']//tr["+row+"]//th["+col+"]")).getText();
				  System.out.print(driver.findElement(By.xpath("//table[@id='example']//tr["+row+"]//th["+col+"]")).getText()+" |");
			  }
			  else {
				  tableValues[row-1][col-1]= driver.findElement(By.xpath("//table[@id='example']//tr["+row+"]//td["+col+"]")).getText();
				  System.out.print(driver.findElement(By.xpath("//table[@id='example']//tr["+row+"]//td["+col+"]")).getText()+" |");
			  }
			  
		  }
		  System.out.println();
	  }
	  XSSFWorkbook workbook = new XSSFWorkbook();
	  XSSFSheet sheet = workbook.createSheet();
	  
	  //create excel
	  int rowNum=0;
	  System.out.println("Creating excel");
	  for(Object[] dataInTable:tableValues) {
		  Row row = sheet.createRow(rowNum++);
		  int colNum=0;
		  for(Object ob:dataInTable) {
			  Cell cell = row.createCell(colNum++);
			  if(ob instanceof String) {
				  cell.setCellValue((String)ob);
			  }
			  else if(ob instanceof Integer) {
				  cell.setCellValue((Integer)ob);
			  }
		  }
	  }
	  
	  try {
		  FileOutputStream output = new FileOutputStream(FILEPATH);
		  workbook.write(output);
	  }
	  catch(FileNotFoundException e) {
		  e.printStackTrace();
	  }
	  finally {
		  workbook.close();
		  System.out.println("Data written in excel");
	  }
	  
	  driver.quit();
	}

}
