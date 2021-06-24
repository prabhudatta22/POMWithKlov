/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

public interface Constants {

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- FRAMEWORK
	 * CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String ROOT_DIR = "user.dir";
	public String SCREENSHOTS_FOLDER = "\\screenshots\\";
	public String AUTHOR1 = "Prabhudatta";
	public String REGRESSION_CATEGORY = "Regression";
	public String SMOKE_CATEGORY = "Smoke";
	public String SANITY_CATEGORY = "Sanity";

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- SONARQUBE
	 * CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String SELENIUM_GRID = "Selenium_GRID_URL";
	public String EXECUTION_ENV = "executionEnvironment";
	public String BROWSER = "browser";
	public String PLATFORM = "platform";
	public String VERSION = "version";
	public String OS_VERSION = "os_version";
	public String BROWSERSTACK_LOCAL = "browserstack.local";
	public String BROWSERSTACK_LOCAL_VALUE_FALSE = "false";
	public String BROWSERSTACK_LOCAL_VALUE_TRUE = "true";
	public String FIREFOX = "FIREFOX";
	public String CHROME = "CHROME";
	public String IE = "IE";
	public String GRID_URL = "http://localhost:4444/wd/hub"; // 192.168.2.92

	/*
	 * -------------------------------------------------------------------------
	 * -------------------------------------------------------------- REPORTING
	 * CONSTANTS
	 * -------------------------------------------------------------------------
	 * --------------------------------------------------------------
	 */
	public String OR_ACTUAL_TITLE = "Actual Title: ";
	public String OR_START_FONT_TAG = "<font color=";
	public String OR_END_FONT_TAG = "</font>";
	public int DRIVER_TIME_OUT = 180;

	
}
