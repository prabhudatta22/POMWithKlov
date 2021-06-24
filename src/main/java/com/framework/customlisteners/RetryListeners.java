/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.customlisteners;

import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListeners implements IRetryAnalyzer {

	private int count = 0;
	private int maxCount = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			Throwable ex = result.getThrowable();
			if (ex != null && ex.getClass() == UnreachableBrowserException.class) {
				return false;
			}
			if (count < maxCount) {
		    	result.getTestContext().getFailedTests().removeResult(result);
		    	result.getTestContext().getSkippedTests().removeResult(result);
		        count++;
		        return true;
			}

		}
		return false;
	}
	
	public String getResultStatusName(int status) {
    	String resultName = null;
    	if(status==1)
    		resultName = "SUCCESS";
    	if(status==2)
    		resultName = "FAILURE";
    	if(status==3)
    		resultName = "SKIP";
		return resultName;
    }
	
}