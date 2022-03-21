package com.montece.xlsparser;

import java.util.List;

public class XlsParserMain
{
	private static DBManager dbManager;
	private static XLSManager xlsManager;
	private static MainForm form;
	private static final boolean USE_CONSOLE_OUTPUT = false;
	private static final boolean USE_FORM_OUTPUT = true;
	
	/* ����� ��������� ��������� */
	public static void main(String[] args)
	{
		form = new MainForm("XlsParserMain - Window", 500, 750);
		
		printText("������ ���������");
		
	   	printText("������������� ���� ������...");
	   	try
	   	{
	   		dbManager = new DBManager("Main.s3db"); //args[1]
	    }
	    catch (Exception x)
	   	{
	   		printText("������ ������������� ���� ������!");
	   		printError(x);
	   		return;
	   	}
	   	printText("�������� ������������� ���� ������!");
	    
	    printText("������������� ��������� excel...");
	    try
	   	{
	   		xlsManager = new XLSManager("�������.xlsx"); //args[2]
	   	}
	   	catch (Exception x)
	    {
	    	printText("������ ������������� ��������� excel!");
	   		printError(x);
	   		return;
	   	}
		
	    printText("�������� ��� ������������ �������...");
		try
		{
			dbManager.deleteTable("MainTable");
		}
		catch (Exception x)
		{
			printText("������� �� ����������! �������� �����...");
		}
		
		printText("���������� ����� �������...");
		try
		{
			dbManager.addTable("MainTable");
		}
		catch (Exception x)
		{
			printText("������ ���������� �������!");
			printError(x);
			return;
		}
		
		List<DBElement> elements = null;
		
		printText("������� excel ���������...");
		try
		{
			elements = xlsManager.createElementsArray(0);
		}
		catch (Exception x)
		{
			printText("������ �������� �������!");
			printError(x);
			return;
		}
		
		printText("���������� ��������� � �������...");
		try
		{
			for	(DBElement element : elements)
			{
				dbManager.addElement("MainTable", element);
			}
		}
		catch (Exception x)
		{
			printText("������ ���������� ��������� � �������!");
			printError(x);
			return;
		}
		
		printText("������, ���������� �� ���� ������:");
		try
		{
			String str = dbManager.tableToString("MainTable");
			printText(str);
		}
		catch (Exception x)
		{
			printText("������ ������ ������� �� ���� ������!");
			printError(x);
			return;
		}
		
		dbManager.Stop();
	    xlsManager.Stop();
	    
	    printText("����� ���������");
	}
	
	/* ����� ������ */
	public static void printText(String str)
	{
		if (USE_FORM_OUTPUT) form.printText(str);
		if (USE_CONSOLE_OUTPUT) System.out.println(str);
	}

	/* ����� ������ */
	public static void printError(Exception x)
	{
		printText(x.getMessage() + "\n\r" + x.getStackTrace());
	}
}