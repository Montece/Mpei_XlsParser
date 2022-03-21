package com.montece.xlsparser;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainForm
{	
	private static JFrame form;
	private static JTextArea text;
	
	/* ����������� ����� � �� ���������� � ��������� */
	public MainForm(String title, int width, int height)
	{
		//��������� ���� ���������� ������ � ����������� �� ���� ������, ������� ���� ��������� �� ����� excel
		form = new JFrame(title);
		form.setSize(width, height);
		form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���������� ������
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		form.setLocation(dim.width / 2 - form.getSize().width / 2, dim.height / 2 - form.getSize().height / 2);
		
		text = new JTextArea();	    
		text.setEditable(false);
		form.getContentPane().add(text);
		
		form.setVisible(true);
	}
	
	/* ����� ������ � ����� */
	public void printText(String str)
	{
		text.setText(text.getText() + "\n\r" + str);
	}
}
