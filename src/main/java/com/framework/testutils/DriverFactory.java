/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.testutils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.grid.selenium.GridLauncherV3;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.setup.TestSetUp;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	static String targetUrl = "";
	static DesiredCapabilities caps = null;

	private DriverFactory() {
	}

	/**
	 * This method is to create a driver instance for what is configured in
	 * configuration file.
	 * 
	 * @param browserName
	 * @throws MalformedURLException
	 * @throws InterruptedException
	 */
	public static WebDriver createDriverInstance(String browserName)
			throws MalformedURLException, InterruptedException {
		WebDriver driver = null;
		String portNo = "5555";
		String downloadFilepath = System.getProperty("user.dir") + File.separator + "Download";
		// setUpGridHub();
		if (TestSetUp.configProperty.getProperty("executionEnvironment").contains("Windows")) {
			if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				WebDriverManager.firefoxdriver().timeout(50);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setAssumeUntrustedCertificateIssuer(false);

				// Download setting
				profile.setPreference("browser.download.folderlist", 2);
				profile.setPreference("browser.helperapps.neverAsk.saveToDisk", "jpeg");
				profile.setPreference("browser.download.dir", downloadFilepath);

				FirefoxOptions options = new FirefoxOptions();
				options.setProfile(profile);

				caps = DesiredCapabilities.firefox();
				caps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
				caps.setCapability("idleTimeout", 150);

				portNo = "5568";
				if (TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("false")) {
					driver = new FirefoxDriver(caps);
				}

			} else if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
				chromePrefs.put("profile.password_manager_enabled", "false");
				chromePrefs.put("plugins.plugins_disabled", new String[] {"Chrome PDF Viewer"});
				chromePrefs.put("profile.default_content_settings.popups", 0);
				chromePrefs.put("download.default_directory", downloadFilepath);
				chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting",1);
				 chromePrefs.put("safebrowsing.enabled", "true");
				 chromePrefs.put("download.prompt_for_download", "false");
				 chromePrefs.put("plugins.always_open_pdf_externally", "false");
				 chromePrefs.put("download.default_directory", downloadFilepath);
				 
				 
				ChromeOptions options = new ChromeOptions();
				 options.addArguments("download.directory_upgrade", "true");
				 options.addArguments("download.prompt_for_download", "false");
				 options.addArguments("plugins.always_open_pdf_externally", "false");
				 options.addArguments("download.default_directory", downloadFilepath);
				options.setExperimentalOption("prefs", chromePrefs);
				options.addArguments("--incognito");

				caps = DesiredCapabilities.chrome();
				caps.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
				caps.setCapability("disable-popup-blocking", true);
				caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				caps.setCapability("chrome.prefs", chromePrefs);
				caps.setCapability("chrome.setProxyByServer", false);
				caps.setCapability("Connnection", "keep-alive");
				caps.setCapability(ChromeOptions.CAPABILITY, options);
				caps.setCapability("idleTimeout", 150);

				portNo = "5566";

				if (TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("false")) {
					driver = new ChromeDriver(caps);
				}
			} else if (browserName.equalsIgnoreCase("ie")) {
				WebDriverManager.iedriver().setup();
				caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability("idleTimeout", 150);

				portNo = "5577";
				if (TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("false")) {
					driver = new InternetExplorerDriver(caps);
				}

			}

			else if (browserName.equalsIgnoreCase("edge")) {
				portNo = "5588";
				WebDriverManager.edgedriver().setup();
				caps = DesiredCapabilities.edge();
				caps.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability("idleTimeout", 150);

				if (TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("false")) {
					driver = new EdgeDriver(caps);
				}
			}

			targetUrl = TestSetUp.configProperty.getProperty("Selenium_GRID_URL");

		} 

		if (TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("true")) {
			try {
			driver = new RemoteWebDriver(new URL(targetUrl), caps);
			System.out.println("Test will be running in zalenium node " + targetUrl);
			}
			catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		DriverManager.setDriver(driver);
		DriverManager.maximizeBrowser(driver);
		DriverManager.pageLoadTimeout(driver);
		DriverManager.setImplicitWait(driver);
		System.out.println("Driver created " + driver);

		/**
		 * Additional execution environments can be added here.
		 */
		return DriverManager.getDriver();
	}

	/**
	 * @param browserName
	 */

	private static void setUpGridHub() {
		try {
			GridLauncherV3.main(new String[] { "-role", "hub", "-port", "4444" });
			System.out.println("HUB Started successfully");
		} catch (Exception e) {
		}
	}

	private static void setUpGridNode(String browserName, String portNo) {

		try {
			GridLauncherV3.main(new String[] { "-role", "node", "-hub", "http://localhost:4444/grid/register", "-host",
					"localhost", "-browser", "\"" + "browserName=" + browserName + ",version=81" + "\"", "-port",
					"\"" + portNo + "\"" });
			// , "-servlets", "com.automation.remarks.remote.node.Video"
			System.out.println("NODE Started successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is to kill the driver.
	 */
	public static void destroyDriver() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}

	}
}
