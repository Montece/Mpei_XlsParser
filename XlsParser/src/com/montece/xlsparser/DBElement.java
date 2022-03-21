package com.montece.xlsparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBElement
{
	//Список всех полей
	
    public int id;
    public String company;
    public int fact_qliq_data1;
    public int fact_qliq_data2;
    public int fact_qoil_data1;
    public int fact_qoil_data2;
    public int forecast_qliq_data1;
    public int forecast_qliq_data2;
    public int forecast_qoil_data1;
    public int forecast_qoil_data2;
    private Date date;
    public int total_qliq;
    public int total_qoil;
    
    private static final SimpleDateFormat XLS_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private static final SimpleDateFormat DB_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    
    /* Пустой конструктор */
    public DBElement()
    {
    	
    }
    
    /* Конструктор с параметрами для полей класса */
    public DBElement(int id_, String company_, int fact_qliq_data1_, int fact_qliq_data2_, int fact_qoil_data1_, int fact_qoil_data2_,
    		int forecast_qliq_data1_, int forecast_qliq_data2_, int forecast_qoil_data1_, int forecast_qoil_data2_, Date date_)
    {
    	this.id = id_;
    	this.company = company_;
    	this.fact_qliq_data1 = fact_qliq_data1_;
    	this.fact_qliq_data2 = fact_qliq_data2_;
    	this.fact_qoil_data1 = fact_qoil_data1_;
    	this.fact_qoil_data2 = fact_qoil_data2_;
    	this.forecast_qliq_data1 = forecast_qliq_data1_;
    	this.forecast_qliq_data2 = forecast_qliq_data2_;
    	this.forecast_qliq_data2 = forecast_qliq_data2_;
    	this.forecast_qoil_data1 = forecast_qoil_data1_;
    	this.forecast_qoil_data2 = forecast_qoil_data2_;
    	this.date = date_;
    	this.total_qliq = 0;
    	this.total_qoil = 0;
    }
    
    public String getXLSDateString()
    {
    	return XLS_DATE_FORMAT.format(date);
    }
    
    public String getDBDateString()
    {
    	return DB_DATE_FORMAT.format(date);
    }
    
    public void setDate(Integer number) throws ParseException
    {
    	date = DB_DATE_FORMAT.parse(number.toString());
    }
    
    public void setDate(Date date_)
    {
    	date = date_;
    }
    
    /* Перегрузка метода toString для упрощения вывода объекта */
    @Override
    public String toString()
    {
		return String.format("[%s] - %s - %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s", id, company,
				fact_qliq_data1, fact_qliq_data2, fact_qoil_data1, fact_qoil_data2, 
				forecast_qliq_data1, forecast_qliq_data2, forecast_qoil_data1, forecast_qoil_data2,
				getXLSDateString(), total_qliq, total_qoil);
    }
}
