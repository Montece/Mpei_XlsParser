package com.montece.xlsparser;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsParserMain
{
	private static DBManager dbManager;
	private static XLSManager xlsManager;
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("Program started.");
		
		dbManager = new DBManager("<ÏÓÒÜ_ÄÎ_ÁÀÇÛ_ÄÀÍÍÛÕ>");
		xlsManager = new XLSManager("<ÏÓÒÜ_ÄÎ_ÄÎÊÓÌÅÍÒÀ>");
	
		dbManager.deleteTable("MainTable");
		dbManager.addTable("MainTable");
		
		List<DBElement> elements = xlsManager.createElementsArray(0);
		
		for	(DBElement element : elements)
		{
			dbManager.addElement("MainTable", element);
		}        
		
		dbManager.printTable("MainTable");
		
		System.out.println("Program stopped.");
	}
}