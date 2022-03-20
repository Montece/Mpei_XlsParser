package com.montece.xlsparser;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
		
		//Отрисовка окна посередине экрана с информацией из базы данных, которая туда поступила из файла excel
		JFrame frame = new JFrame("XlsParserMain - Window");
		frame.setSize(500, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    JTextArea textArea = new JTextArea("Данные, полученные из базы данных: \n\r");	    
	    frame.getContentPane().add(textArea);
	    frame.setVisible(true);
	    
	    try
	    {
	    	dbManager = new DBManager("Main.s3db");
			xlsManager = new XLSManager("Задание.xlsx");
			String str = dbManager.tableToString("MainTable");
			textArea.setText(textArea.getText() + str);
			
			try
			{
				dbManager.deleteTable("MainTable");
			}
			catch (Exception x)
			{
				System.out.println("Таблицы не существует! Создание новой...");
			}
			
			dbManager.addTable("MainTable");
			
			List<DBElement> elements = xlsManager.createElementsArray(0);
			
			for	(DBElement element : elements)
			{
				dbManager.addElement("MainTable", element);
			}
			
			dbManager.printTable("MainTable");
	    }
	    catch (Exception x)
	    {
	    	String error = x.getMessage() + "\n" + x.getStackTrace();
	    	System.out.println(error);
	    	textArea.setText(textArea.getText() + error);
	    }
	    finally
	    {
	    	dbManager.Stop();
	    	xlsManager.Stop();
	    }
	    
		System.out.println("Program stopped.");
	}
}