package com.montece.xlsparser;

import java.util.List;

public class XlsParserMain
{
	private static DBManager dbManager;
	private static XLSManager xlsManager;
	private static MainForm form;
	private static final boolean USE_CONSOLE_OUTPUT = false;
	private static final boolean USE_FORM_OUTPUT = true;
	
	/* Точка вхождения программы */
	public static void main(String[] args)
	{
		form = new MainForm("XlsParserMain - Window", 500, 750);
		
		printText("Запуск программы");
		
	   	printText("Инициализация базы данных...");
	   	try
	   	{
	   		dbManager = new DBManager("Main.s3db"); //args[1]
	    }
	    catch (Exception x)
	   	{
	   		printText("Ошибка инициализации базы данных!");
	   		printError(x);
	   		return;
	   	}
	   	printText("Успешная инициализации базы данных!");
	    
	    printText("Инициализация документа excel...");
	    try
	   	{
	   		xlsManager = new XLSManager("Задание.xlsx"); //args[2]
	   	}
	   	catch (Exception x)
	    {
	    	printText("Ошибка инициализации документа excel!");
	   		printError(x);
	   		return;
	   	}
		
	    printText("Удаление уже существующей таблицы...");
		try
		{
			dbManager.deleteTable("MainTable");
		}
		catch (Exception x)
		{
			printText("Таблицы не существует! Создание новой...");
		}
		
		printText("Добавление новой таблицы...");
		try
		{
			dbManager.addTable("MainTable");
		}
		catch (Exception x)
		{
			printText("Ошибка добавления таблицы!");
			printError(x);
			return;
		}
		
		List<DBElement> elements = null;
		
		printText("Парсинг excel документа...");
		try
		{
			elements = xlsManager.createElementsArray(0);
		}
		catch (Exception x)
		{
			printText("Ошибка парсинга таблицы!");
			printError(x);
			return;
		}
		
		printText("Добавление элементов в таблицу...");
		try
		{
			for	(DBElement element : elements)
			{
				dbManager.addElement("MainTable", element);
			}
		}
		catch (Exception x)
		{
			printText("Ошибка добавления элементов в таблицу!");
			printError(x);
			return;
		}
		
		printText("Данные, полученные из базы данных:");
		try
		{
			String str = dbManager.tableToString("MainTable");
			printText(str);
		}
		catch (Exception x)
		{
			printText("Ошибка вывода таблицы из базы данных!");
			printError(x);
			return;
		}
		
		dbManager.Stop();
	    xlsManager.Stop();
	    
	    printText("Конец программы");
	}
	
	/* Вывод текста */
	public static void printText(String str)
	{
		if (USE_FORM_OUTPUT) form.printText(str);
		if (USE_CONSOLE_OUTPUT) System.out.println(str);
	}

	/* Вывод ошибки */
	public static void printError(Exception x)
	{
		printText(x.getMessage() + "\n\r" + x.getStackTrace());
	}
}