/**
 * 
 */
package com.framework.customlisteners;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.setup.TestSetUp;
import com.framework.testutils.DriverFactory;
import com.framework.testutils.DriverManager;

public class TestsListeners extends TestSetUp implements ITestListener, ISuiteListener {

	public void onFinish(ISuite arg0) {
	}

	public void onStart(ISuite arg0) {
	}

	public void onFinish(ITestContext arg0) {
		
		extent.flush();
	}

	public void onStart(ITestContext arg0) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult arg0) {

		System.out.println("Test Case failed from listner >>> "+arg0.getMethod().getMethodName());
		String excepionMessage = StringUtils.isEmpty(arg0.getThrowable().getMessage())? ",":arg0.getThrowable().getMessage();
		String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
		String excepionTrace = StringUtils.isEmpty(Arrays.toString(arg0.getThrowable().getStackTrace()))? ",":Arrays.toString(arg0.getThrowable().getStackTrace());
		testCaseLogger.get()
				.fail("<details>" + "<summary style='cursor: pointer;'>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured: Click to see" + "</font>" + "</b >" + "</summary>"
						+ excepionMessage.replaceAll(",", "<br>") + "<br>" + excepionTrace.replaceAll(",", "<br>")
						+ "</details>" + " \n");

		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testCaseLogger.get().log(Status.FAIL, m);
		if (Status.FAIL == testCaseLogger.get().getStatus()) {
			try {
				File file = new File(dest + arg0.getName() + "fail.png");
				//To add it in the extent report 
				TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, file);
				testCaseLogger.get().log(Status.FAIL, file.getAbsolutePath());
				testCaseLogger.get().addScreenCaptureFromPath(file.getAbsolutePath());
				testCaseLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromPath(file.getAbsolutePath()).build());
			} catch (Exception e) {

			}
		}
		
		DriverFactory.destroyDriver();
	}

	public void onTestSkipped(ITestResult arg0) {
		
		System.out.println("Test Case skipped from listner >>> "+arg0.getMethod().getMethodName());
		String methodName = arg0.getMethod().getMethodName();
		String logText = "<b>" + "<font color=blue>" + "Test Case:- " + methodName + " Skipped." + "</font></b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		//if(arg0.getThrowable().getMessage().contains("RunMode is set to No in test data")) {
		testCaseLogger.get().skip(m);//}
		// extent.flush();
		DriverFactory.destroyDriver();

	}

	public void onTestStart(ITestResult arg0) {
		System.out.println("Test Case Started from listner >>> "+arg0.getMethod().getMethodName());
		
		ExtentTest child = classLevelExtentTest.get().createNode(arg0.getMethod().getMethodName());
		testCaseLogger.set(child);
		testCaseLogger.get().log(Status.INFO,
				"Execution of Test case- <b>" + arg0.getMethod().getMethodName() + "</b> started.");
	}

	public void onTestSuccess(ITestResult arg0) {

		System.out.println("Test Case completed from listner >>> "+arg0.getMethod().getMethodName());
		String methodName = arg0.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Passed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testCaseLogger.get().pass(m);
	}

}