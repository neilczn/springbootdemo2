package com.equality.springbootdemo2.controller;

import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.print.PrintService;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.equality.springbootdemo2.entity.Printer;

@RestController
@RequestMapping("/print")
public class PrintController {
	
	@RequestMapping("/list")
	public List<Printer> list(){
		
		List<Printer> list = new ArrayList<>();
		
		PrintService[] services = PrinterJob.lookupPrintServices();
		
		for (PrintService printService : services) {
			Printer temp = new Printer();
			temp.setName(printService.getName());
			
			
			PrinterState prnState = (PrinterState)printService.getAttribute(
                    PrinterState.class);
			
			if (prnState != null) {
				temp.setPrinterState("printerState:"+prnState.getName()+","+prnState.getValue());
			}
			
			PrinterStateReasons prnStateReasons =
	                (PrinterStateReasons)printService.getAttribute(
	                                             PrinterStateReasons.class);
			
			if ((prnStateReasons != null) ){		    	
		    	for (PrinterStateReason temp1 : prnStateReasons.keySet()) {
					//System.out.println(temp1.getName()+","+temp1.getValue());
		    		temp.getPrinterStateReasons().add("prnStateReason:"+temp1.getName()+","+temp1.getValue());
				}
	        }
			
			list.add(temp);
		}
		
		return list;
	}

}
