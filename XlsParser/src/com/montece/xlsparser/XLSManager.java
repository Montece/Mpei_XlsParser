package com.montece.xlsparser;

import java.io.*;
import java.sql.PreparedStatement;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSManager
{
	private File FILE_PATH;
	
	private FileInputStream stream;
	private Workbook workbook;
	
	public XLSManager(String filePath) throws Exception
	{
		FILE_PATH = new File(filePath);
		stream = new FileInputStream(FILE_PATH);
		workbook = new XSSFWorkbook(stream);
	}
	
	public void printSheet(int id) throws Exception
    {
		Sheet sheet = workbook.getSheetAt(id);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
		
		for (Row row : sheet)
		{
			for (Cell cell : row)
			{
				switch (formulaEvaluator.evaluateInCell(cell).getCellType())
				{
					case STRING:
						System.out.print(cell.getStringCellValue() + "\t");
						break;
					case NUMERIC:
						System.out.print(cell.getNumericCellValue() + "\t");
						break;
					case BLANK:
						System.out.print("<пусто>" + "\t");
						break;
					default:
						break;
				}
			}
			
			System.out.println();
		}
    }

	public List<DBElement> createElementsArray(int id)
	{
		List<DBElement> elements = new ArrayList<DBElement>();
		
		Sheet sheet = workbook.getSheetAt(id);
		FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

		for (int rowIndex = 3; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++)
		{
			Row row = sheet.getRow(rowIndex);
			DBElement element = new DBElement();
			
			for (int cellIndex = 0; cellIndex < row.getPhysicalNumberOfCells(); cellIndex++)
			{
				Cell cell = row.getCell(cellIndex);
				
				switch (cellIndex)
				{
					case 0:
						element.id = (int)cell.getNumericCellValue();
						break;
					case 1:
						element.company = cell.getStringCellValue();
						break;
					case 2:
						element.fact_qliq_data1 = (int)cell.getNumericCellValue();
						break;
					case 3:
						element.fact_qliq_data2 = (int)cell.getNumericCellValue();
						break;
					case 4:
						element.fact_qoil_data1 = (int)cell.getNumericCellValue();
						break;
					case 5:
						element.fact_qoil_data2 = (int)cell.getNumericCellValue();
						break;
					case 6:
						element.forecast_qliq_data1 = (int)cell.getNumericCellValue();
						break;
					case 7:
						element.forecast_qliq_data2 = (int)cell.getNumericCellValue();
						break;
					case 8:
						element.forecast_qoil_data1 = (int)cell.getNumericCellValue();
						break;
					case 9:
						element.forecast_qoil_data2 = (int)cell.getNumericCellValue();
						break;
					default:
						break;
				}
			}
			
			elements.add(element);
		}
		
		return elements;
	}
}
