package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderData {

	//@Test
	@DataProvider(name="data")
	public String[][] getData() throws IOException
	{
		String path=".\\xlFile\\UserData.xlsx";
		XlData x=new XlData(path);
		int r=x.getRows("Sheet1");
		int c=x.getCol("Sheet1", 1);
		
		System.out.println(r+"  "+c);
		String[][] s=new String[r-1][c];
		
		for(int i=1;i<r;i++)
		{
			for(int j=0;j<c;j++)
			{
				s[i-1][j]=x.getData("Sheet1", i, j);
			}
		}
		
		return s;
//		for(int i=0;i<r-1;i++)
//		{
//			for(int j=0;j<c;j++)
//			{
//				System.out.println(s[i][j]);
//			}
//		}
	}
	
	@DataProvider(name="username")
	public String[] getUserName() throws IOException
	{
		String path=".\\xlFile\\UserData.xlsx";
		XlData x=new XlData(path);
		int r=x.getRows("Sheet1");
		
		String[] s=new String[r-1];
		
		for(int i=1;i<r;i++)
		{
			s[i-1]=x.getData("Sheet1", i, 1);
		}
		
		return s;
	}
}
