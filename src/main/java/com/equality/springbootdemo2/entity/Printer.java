package com.equality.springbootdemo2.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Printer implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1859979509666800745L;
	
	
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	private String printerState;

	/**
	 * @return the printerState
	 */
	public String getPrinterState() {
		return printerState;
	}

	/**
	 * @param printerState the printerState to set
	 */
	public void setPrinterState(String printerState) {
		this.printerState = printerState;
	}
	
	private List<String> printerStateReasons = new ArrayList<String>();

	/**
	 * @return the printerStateReasons
	 */
	public List<String> getPrinterStateReasons() {
		return printerStateReasons;
	}

	/**
	 * @param printerStateReasons the printerStateReasons to set
	 */
	public void setPrinterStateReasons(List<String> printerStateReasons) {
		this.printerStateReasons = printerStateReasons;
	}
	

}
