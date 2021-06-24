/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.framework.setup.TestSetUp;

public class ExtentManager {

	private ExtentManager() {
	}

	private static ExtentReports extent;
	private static String filePath = "./extentreport.html";

	public static ExtentReports getExtent() {
		if (extent != null) {
			return extent;
		} else {
			extent = new ExtentReports();
			//extent.attachReporter(getHtmlReporter());
			extent.setSystemInfo("Host Name", "Prabhudatta Choudhury");
			extent.setSystemInfo("Version", TestSetUp.configProperty.getProperty("version"));
			extent.setSystemInfo("Test Type", "Regression");
			extent.setSystemInfo("Application Type", "Web Application");
			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
			return extent;
		}
	}

//	public static ExtentHtmlReporter getHtmlReporter() {
//		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filePath);
//		htmlReporter.setAppendExisting(false);
//		htmlReporter.loadXMLConfig(
//				System.getProperty(Constants.ROOT_DIR) + "/src/test/resources/extentConfig/ReportsConfig.xml");
//		return htmlReporter;
//	}

}
