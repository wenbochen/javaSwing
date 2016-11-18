package com.wenbo.swing;

import java.applet.Applet;

import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.Panel;

public class Calculator extends Applet {

	/**
	 * Create the applet.
	 */
	public Calculator() {
		
		TextField textField = new TextField();
		add(textField);
		
		TextField textField_1 = new TextField();
		add(textField_1);
		
		TextField textField_2 = new TextField();
		add(textField_2);

	}

}
