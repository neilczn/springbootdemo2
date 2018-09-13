package com.equality.springbootdemo2;

import static org.junit.Assert.*;

import java.awt.print.PrinterJob;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Provider.Service;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterState;
import javax.print.attribute.standard.PrinterStateReason;
import javax.print.attribute.standard.PrinterStateReasons;

import org.junit.Before;
import org.junit.Test;






public class Print {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void test4(){
		String path = System.getProperty("user.dir")+"\\src\\main\\java";
		System.out.println(path);
		File rootFiles = new File(path+"\\com\\equality2\\springbootdemo2");
		
		if (!rootFiles.exists()) {
			System.out.println("创建情况："+rootFiles.mkdirs());
		}else{
			System.out.println("存在目录："+rootFiles.getAbsolutePath());
		}
		
	}
	
	@Test
	public void test3() {
		
		//取得根目录路径
		String rootPath = getClass().getResource("/").getFile().toString();
		System.out.println(rootPath);

		//当前目录路径
		String currentPath1 = getClass().getResource(".").getFile().toString();
		System.out.println(currentPath1);
		
		//当前目录的上级目录路径
		String parentPath = getClass().getResource("../").getFile().toString();
		System.out.println(parentPath);
		
		//Javaweb项目获取src目录
		String path1 = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(path1);
		
		//Java项目获取src目录
		String path2 = Service.class.getClass().getResource("/").getPath();
		System.out.println(path2);
		
		String path = System.getProperty("user.dir")+"\\src";
		System.out.println(path);
		
		String classPath = this.getClass().getClassLoader().getResource("/").getPath();
		System.out.println(classPath);
	}

	@Test
	public void test() {
		
		PrintService[] services = PrinterJob.lookupPrintServices();
		
		PrinterJob printer = PrinterJob.getPrinterJob();
		System.out.println("默认打印机为:"+ printer.getPrintService().getName());
		
			
		
		for (PrintService printService : services) {
			
			System.out.println("==================");
			System.out.println("打印机名称:" + printService.getName());
			//System.out.println("State " + printService.isAttributeCategorySupported(PrinterState.class));
			/*Set<Attribute> attributes = getAttributes(printService);
		    for(Attribute attr : attributes){
		    	System.out.println(attr.getName());
		    }*/
			
		    PrinterState prnState = (PrinterState)printService.getAttribute(
                    PrinterState.class);
		    
		    System.out.println("prnState:" + prnState);
		    
		    PrinterStateReasons prnStateReasons =
	                (PrinterStateReasons)printService.getAttribute(
	                                             PrinterStateReasons.class);
		    
		    if ((prnStateReasons != null) )// (prnStateReasons.containsKey(PrinterStateReason.SHUTDOWN))
	         {
		    	
		    	for (PrinterStateReason temp1 : prnStateReasons.keySet()) {
					System.out.println(temp1.getName()+","+temp1.getValue());
				}
	          }
		    
		}
		
	}
	
	
	public Set<Attribute> getAttributes(PrintService printer){
		Set<Attribute> set = new LinkedHashSet<Attribute>();
		
		//get the supported docflavors, categories and attributes
		Class<? extends Attribute>[] categories = (Class<? extends Attribute>[]) printer.getSupportedAttributeCategories();
		DocFlavor[] flavors = printer.getSupportedDocFlavors();
	    AttributeSet attributes = printer.getAttributes();
		
	    
	  //get all the avaliable attributes
	    for (Class<? extends Attribute> category : categories) {
	    
	    	for (DocFlavor flavor : flavors) {
				
	    		//get the value
	            Object value = printer.getSupportedAttributeValues(category, flavor, attributes);

	          //check if it's something
	            if (value != null) {
	            	
	            	if (value instanceof Attribute) {
	            		set.add((Attribute) value); //it's a SINGLE attribute...
					}else if (value instanceof Attribute[]){
						set.addAll(Arrays.asList((Attribute[]) value));
					}		
	            	
	            }
	    		
			}
	    	
	    }
		
		return set;
	}
	
	
	
	
	
	@Test
	public void test1() throws  Exception{
		
		System.out.println(System.getProperty("java.version"));//输出当前jdk版本号
        System.out.println(System.getProperty("sun.arch.data.model"));//输出当前jdk所用平台
        String dir = "C:\\Users\\E-Quality\\Documents\\visual studio 2015\\Projects\\WindowsFormsApplication1\\ConsoleApplication1\\bin\\Debug\\PrinterStatusQueryConsoleApp.exe \"Foxit Reader PDF Printer\"";
        Process p = Runtime.getRuntime().exec(dir);
        
        InputStream child_in = p.getInputStream(); 
		/*int c; 
		while ((c = child_in.read()) != -1) { 
			//   System.out.println("kkk"); 
			System.out.print((char)c); 
		} */
		
		byte[] rec = new byte[1024*1024];
		int j = child_in.read(rec);
		byte[] rec2 = new byte[j];
		System.arraycopy(rec, 0, rec2, 0, j);
		
		//String temp = new String(rec2,"GB2312");//注意：这里转换的编码需要与C#控制台输出字符串的编码一致，C#控制台默认输出编码为GB2312
		String temp = new String(rec2,"UTF-8");
		System.out.println(temp);
		
		child_in.close();
		
		/*p.waitFor();
        InputStream input = p.getInputStream();
        int i = input.available();
        byte[] rec = new byte[i];
        int j = input.read(rec);
        String temp = new String(rec);
        System.out.println(temp);*/
        p.destroy();
        /*if () {
			
		}*/		
		
	}
	
	

}
