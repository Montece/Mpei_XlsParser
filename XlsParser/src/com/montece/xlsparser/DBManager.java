package com.montece.xlsparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager
{
	private final String CLASS_NAME = "org.sqlite.JDBC";
    private final String CONNECTION_STRING = "jdbc:sqlite:";
    private String DATABASE_PATH;
    
    private Connection connection;

    /* ����������� ��� ������������� ������. ������������� ���������� � �� � ���������� ������� */
    public DBManager(String databasePath) throws Exception
    {
    	DATABASE_PATH = databasePath;
    	Class.forName(CLASS_NAME);
    	this.connection = DriverManager.getConnection(CONNECTION_STRING + DATABASE_PATH);
    }
    
    /* ��������� ����� ������� � ������� */
    public void addElement(String tableName, DBElement element) throws Exception
    {
    	PreparedStatement statement;
    	String command = String.format("INSERT INTO [%s]('id', 'company', 'fact_qliq_data1', 'fact_qliq_data2', 'fact_qoil_data1', 'fact_qoil_data2', 'forecast_qliq_data1', 'forecast_qliq_data2', 'forecast_qoil_data1', 'forecast_qoil_data2', 'date', 'total_qliq', 'total_qoil') VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", tableName);
        statement = this.connection.prepareStatement(command);
        statement.setObject(1, element.id);
        statement.setObject(2, element.company);
        statement.setObject(3, element.fact_qliq_data1);
        statement.setObject(4, element.fact_qliq_data2);
        statement.setObject(5, element.fact_qoil_data1);
        statement.setObject(6, element.fact_qoil_data2);
        statement.setObject(7, element.forecast_qliq_data1);
        statement.setObject(8, element.forecast_qliq_data2);
        statement.setObject(9, element.forecast_qoil_data1);
        statement.setObject(10, element.forecast_qoil_data2);
        statement.setObject(11, element.getDBDateString());
        statement.setObject(12, element.fact_qliq_data1 + element.fact_qliq_data2 + element.forecast_qliq_data1 + element.forecast_qliq_data2);
        statement.setObject(13, element.fact_qoil_data1 + element.fact_qoil_data2 + element.forecast_qoil_data1 + element.forecast_qoil_data2);
        statement.execute();
        statement.close();
    }
    
    /* ��������������� ������� � ������ */
	public String tableToString(String tableName) throws Exception
    {
		String str = "";
		
		PreparedStatement statement = this.connection.prepareStatement(String.format("SELECT * FROM [%s]", tableName));
    	ResultSet result = statement.executeQuery();
    	
    	while (result.next())
    	{
    		DBElement element = new DBElement();
    		element.id = result.getInt("id");
    		element.company = result.getString("company");
    		element.fact_qliq_data1 = result.getInt("fact_qliq_data1");
    		element.fact_qliq_data2 = result.getInt("fact_qliq_data2");
    		element.fact_qoil_data1 = result.getInt("fact_qoil_data1");
    		element.fact_qoil_data2 = result.getInt("fact_qoil_data2");
    		element.forecast_qliq_data1 = result.getInt("forecast_qliq_data1");
    		element.forecast_qliq_data2 = result.getInt("forecast_qliq_data2");
    		element.forecast_qoil_data1 = result.getInt("forecast_qoil_data1");
    		element.forecast_qoil_data2 = result.getInt("forecast_qoil_data2");
    		element.setDate(result.getInt("date"));
    		element.total_qliq = result.getInt("total_qliq");
    		element.total_qoil = result.getInt("total_qoil");
    		str += element + "\n\r";
    	}
    	
    	result.close();
    	statement.close();
		
		return str;
    }
    
	/* ������� ������� */
    public void addTable(String tableName) throws Exception
    {
    	PreparedStatement statement = this.connection.prepareStatement(String.format("CREATE TABLE [%s] (" + 
    			"[id] INTEGER DEFAULT '0' NOT NULL PRIMARY KEY AUTOINCREMENT," + 
    			"[company] VARCHAR(10)  NOT NULL," + 
    			"[fact_qliq_data1] INTEGER DEFAULT '0' NOT NULL," + 
    			"[fact_qliq_data2] INTEGER DEFAULT '0' NOT NULL," + 
    			"[fact_qoil_data1] INTEGER DEFAULT '0' NOT NULL," + 
    			"[fact_qoil_data2] INTEGER DEFAULT '0' NOT NULL," + 
    			"[forecast_qliq_data1] INTEGER DEFAULT '0' NOT NULL," + 
    			"[forecast_qliq_data2] INTEGER DEFAULT '0' NOT NULL," + 
    			"[forecast_qoil_data1] INTEGER DEFAULT '0' NOT NULL," + 
    			"[forecast_qoil_data2] INTEGER DEFAULT '0' NOT NULL," +
    			"[date] DATETIME DEFAULT '2000-01-01 00:00:00' NOT NULL," +
    			"[total_qliq] INTEGER DEFAULT '0' NOT NULL," +
    			"[total_qoil] INTEGER DEFAULT '0' NOT NULL)", tableName));
    	statement.execute();
    	statement.close();
    }
    
    /* ������� ������� */
    public void deleteTable(String tableName) throws Exception
    {
    	PreparedStatement statement = this.connection.prepareStatement(String.format("DROP TABLE [%s]", tableName));
    	statement.execute();
    	statement.close();
    }
    
    /* ��������� ������ � �� */
    public void Stop()
    {
    	try
    	{
    		if (!connection.isClosed())
        	{
        		connection.close();
        	}
    	}
    	catch (SQLException x)
    	{
    		
    	}
    }
}
