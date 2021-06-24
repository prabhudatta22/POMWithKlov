/**
 * The ExtentManager class is used to create the report file
 *
 * @author Prabhudatta.c
 * @version 1.0
 * 
 */

package com.framework.testutils;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.framework.setup.TestSetUp;

public class ExtentXManager {

	private ExtentXManager() {
	}

	private static ExtentSparkReporter extentx;
	private static ExtentKlovReporter klovreport;
	
	private static String filePath = "./extentreport.html";

	public static ExtentSparkReporter getExtent() {
		if (extentx != null) {
			return extentx;
		} else {
			extentx = new ExtentSparkReporter(filePath);
			extentx.config().setDocumentTitle("Payroll");
			extentx.config().setReportName("Payroll Regression");
			extentx.viewConfigurer()
		    .viewOrder()
		    .as(new ViewName[] { 
			   ViewName.DASHBOARD, 
			   ViewName.CATEGORY,
			   ViewName.TEST, 
			   ViewName.AUTHOR, 
			   ViewName.EXCEPTION, 
			   ViewName.LOG 
			})
		  .apply();
			try {
				extentx.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extentConfig/ReportsConfig.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return extentx;
		}
		
	}
	
	public static ExtentKlovReporter getKlovReport()
	{
		// intialize with a project name, build name
		ExtentKlovReporter klov = new ExtentKlovReporter();
		klov.setProjectName("Payroll");
	    klov.setReportName("Payroll Regression");
		// address, host/port of MongoDB
		klov.initMongoDbConnection(TestSetUp.configProperty.getProperty("extentServer"), 27017);
		// Klov server address
		klov.initKlovServerConnection("http://"+ TestSetUp.configProperty.getProperty("extentServer"));
	    

		return klov;
	}
	

}
