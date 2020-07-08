package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.bean.QueryBean;
import com.training.dao.ELearningDAO;
import com.training.dao.RealEstateDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;

public class QueryValidDeatilsDataProviders {

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<QueryBean> list = new RealEstateDAO().getQuerys();
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(QueryBean temp : list){
			Object[]  obj = new Object[4]; 
			obj[0] = temp.getName();  
			obj[1] = temp.getEmail(); 
			obj[1] = temp.getSubject();
			obj[1] = temp.getMessage(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelInoutData1(){
		String fileName =  "C:\\Users\\PreethiBalaji\\Pictures\\1008\\Screenshots\\SubmitQueryUsingValidDetails.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
	
	@DataProvider(name = "excel-inputsData")
	public Object[][] getExcelInoutData(){
		String fileName =  "C:\\Users\\PreethiBalaji\\Pictures\\1008\\Screenshots\\SubmitQueryUsingInvalidDetails.xlsx"; 
		return new ApachePOIExcelRead().getExcelContent(fileName); 
	}
/*
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:\\Users\\PreethiBalaji\\Pictures\\1008\\Screenshots\\SubmitQueryUsingValidDetails.xlsx", "Sheet1"); 
	}
	
	@DataProvider(name = "xls-inputsData")
	public Object[][] getXLSDataInput(){
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData("C:\\Users\\PreethiBalaji\\Pictures\\1008\\Screenshots\\SubmitQueryUsingValidDetails.xlsx", "Sheet2"); 
	}
	*/	
}
