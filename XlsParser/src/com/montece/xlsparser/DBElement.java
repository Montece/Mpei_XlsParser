package com.montece.xlsparser;

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
    
    /* Пустой конструктор */
    public DBElement()
    {
    	
    }
    
    /* Конструктор с параметрами для полей класса */
    public DBElement(int id_, String company_, int fact_qliq_data1_, int fact_qliq_data2_, int fact_qoil_data1_, int fact_qoil_data2_,
    		int forecast_qliq_data1_, int forecast_qliq_data2_, int forecast_qoil_data1_, int forecast_qoil_data2_)
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
    }
    
    /* Перегрузка метода toString для упрощения вывода объекта */
    @Override
    public String toString()
    {
		return String.format("[%s] - %s - %s, %s, %s, %s, %s, %s, %s, %s", id, company,
				fact_qliq_data1, fact_qliq_data2, fact_qoil_data1, fact_qoil_data2, 
				forecast_qliq_data1, forecast_qliq_data2, forecast_qoil_data1, forecast_qoil_data2);
    }
}
