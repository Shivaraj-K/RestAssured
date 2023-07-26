package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlData {

	public FileInputStream f;
	String path;
	XSSFWorkbook x;
	XSSFSheet s;
	XSSFRow row;
	XSSFCell cel;
	FileOutputStream fo;
	XSSFCellStyle style;
	
	public XlData(String path)
	{
		this.path=path;
	}
	
	public int getRows(String name) throws IOException
	{
		//".\\xlFile\\UserData.xlsx"
		f=new FileInputStream(path);
		x=new XSSFWorkbook(f);
		
		s=x.getSheet(name);
		int r=s.getLastRowNum();
		return r;
	}
	
	public int getCol(String name,int rowNum) throws IOException
	{
		f=new FileInputStream(path);
	    x=new XSSFWorkbook(f);
		
		s=x.getSheet(name);
		  row=s.getRow(rowNum);
		int c=row.getLastCellNum();
		return c;
	}
	
	public String getData(String name,int rowNum,int colNum) throws IOException
	{
		f=new FileInputStream(path);
	    x=new XSSFWorkbook(f);
		
		s=x.getSheet(name);
		  row=s.getRow(rowNum);
		  cel=row.getCell(colNum);
		  
		  String data;
		  
		  DataFormatter d=new DataFormatter();
		  try {
		 data= d.formatCellValue(cel);
		  }
		  catch(Exception e)
		  {
			  data="";
		  }
		  
		  return data;
	}
	
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())
		{
			x=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			x.write(fo);
		}
		f=new FileInputStream(path);
		x=new XSSFWorkbook(f);
		
		if(x.getSheetIndex(sheetName)==-1)
			x.createSheet(sheetName);
		s=x.getSheet(sheetName);
		
		if(s.getRow(rownum)==null)
			s.createRow(rownum);
		row=s.getRow(rownum);
		
		cel=row.createCell(colnum);
		cel.setCellValue(data);
		fo=new FileOutputStream(path);
		x.write(fo);
		x.close();
		f.close();
		fo.close();
		
	}
	
	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException
	{

	f=new FileInputStream(path);
	x=new XSSFWorkbook (f);
	s = x.getSheet (sheetName);
	
	row=s.getRow(rownum);
	cel=row.getCell(colnum);

	style=x.createCellStyle();

	style.setFillForegroundColor(IndexedColors.GREEN.getIndex()); 
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	cel.setCellStyle(style);
	x.write(fo);
    x.close();
    f.close();
    fo.close();
	}
	
	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException
	{

	f=new FileInputStream(path);

	x=new XSSFWorkbook (f);

	s = x.getSheet (sheetName);

	row=s.getRow(rownum);

	cel=row.getCell(colnum);

	style=x.createCellStyle();

	style.setFillForegroundColor(IndexedColors.RED.getIndex()); 
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	cel.setCellStyle(style);
	x.write(fo);
    x.close();
    f.close();
    fo.close();
	}
}
	

