/**
 ******************************************************************************
 * 							  REUSABLE FRAMEWORK
 *  							CONFIDENTIAL
 *							
 * *****************************************************************************
 */

package com.framework.ui.pageobjects;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.framework.setup.TestSetUp;
import com.framework.testutils.Constants;
import com.framework.testutils.DriverManager;

/**
 * @author maruthip
 *
 */
public abstract class BasePage<T> extends TestSetUp {

	private String handles;

	public T openPage(Class<T> clazz) {
		T page = null;
		AjaxElementLocatorFactory ajaxLocatorFactory = new AjaxElementLocatorFactory(DriverManager.getDriver(),
				Constants.DRIVER_TIME_OUT);
		page = PageFactory.initElements(DriverManager.getDriver(), clazz);

		PageFactory.initElements(ajaxLocatorFactory, page);
		return page;
	}

	public void initialize(String url) {
		DriverManager.getDriver().get(url);
	}

	public ExpectedCondition getPageLoadCondition(WebElement el) {
		return ExpectedConditions.visibilityOf(el);
	}

	@SuppressWarnings("rawtypes")
	public abstract ExpectedCondition getPageLoadCondition();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void waitForPageLoad(ExpectedCondition pageLoadCondition) {
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Constants.DRIVER_TIME_OUT);
		wait.until(pageLoadCondition);
	}

	public void waitForPageLoad(int timeout) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wd) {
				return "complete".equals(((JavascriptExecutor) wd).executeScript("return document.readyState"));
			}
		};
		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeout);
		wait.until(pageLoadCondition);
		DriverManager.getDriver().manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
	}

	public void waitForPageLoad() {

		Wait<WebDriver> wait = new WebDriverWait(DriverManager.getDriver(), 45);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println(
						String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.contentEquals("complete");
			}
		});
	}

	/**
	 * This method will return response code.
	 * 
	 * @param urlString
	 */
	public static int getResponseCode(String urlString) throws MalformedURLException {
		URL u;
		try {
			u = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			return connection.getResponseCode();

		} catch (IOException e) {
			testCaseLogger.get().fail("Couldn't get any response " + e.getLocalizedMessage());
		}
		return 0;
	}

	/**
	 * This method returns cookie value if cookie is present.
	 * 
	 * @param name
	 */
	public String getCookieValue(String name) {
		Cookie ck = DriverManager.getDriver().manage().getCookieNamed(name);
		if (ck == null) {
			testCaseLogger.get().log(Status.INFO, "getCookieValue: No cookie found with name='" + name + "'");
			return "";
		} else {
			return ck.getValue();
		}
	}

	/**
	 * This method returns number of openWindows.
	 * 
	 * @param name
	 */
	public int numberOfOpenWindows() {
		Set<String> set = DriverManager.getDriver().getWindowHandles();
		testCaseLogger.get().log(Status.INFO, "Number of open windows : " + set.size());
		return set.size();
	}

	/**
	 * Refresh the webpage in the browser
	 */
	public void refreshPage() {
		DriverManager.getDriver().navigate().refresh();
		testCaseLogger.get().log(Status.INFO, "refreshPage - reload webpage executed");
	}

	/**
	 * This method is for clicking on an element.
	 * 
	 * @param element
	 * @param elementName
	 */
	public void click(WebElement element, String elementName) {
		try {
			highlightElement(element);
			element.click();
			testCaseLogger.get().log(Status.INFO, "Clicked on " + elementName);
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().fail("Couldn't able to click " + e.getLocalizedMessage());
		}
	}

	/**
	 * This method is for clearing input on an element.
	 * 
	 * @param element
	 * @param elementName
	 */
	public void clear(WebElement element, String elementName) {
		try {
			highlightElement(element);
			element.clear();
			testCaseLogger.get().log(Status.INFO, "Clear on " + elementName);
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().fail("Couldn't able to clear " + e.getLocalizedMessage());
		}
	}

	public WebElement findElement(By by) {
		return DriverManager.getDriver().findElement(by);
	}

	/**
	 * This method is for fluentWait.
	 * 
	 * @param secs
	 */
	public void fluentWait(long secs) {
		try {
			ExpectedCondition<Boolean> pageLoadFinishedCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};

			WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), secs);
			wait.until(pageLoadFinishedCondition);
		} catch (Exception ex) {
			testCaseLogger.get().fail(ex);
		}
	}

	/**
	 * This method is for pageloadWait.
	 * 
	 * @param secs
	 */
	public void pageloadWait(long secs) {
		DriverManager.getDriver().manage().timeouts().pageLoadTimeout(secs, TimeUnit.SECONDS);
	}

	/**
	 * This method is for implicitWait.
	 * 
	 * @param secs
	 */
	public void explicitwait(long secs) {
		DriverManager.getDriver().manage().timeouts().implicitlyWait(secs, TimeUnit.SECONDS);
		WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), secs);
		webDriverWait.withTimeout(secs, TimeUnit.SECONDS);
	}

	/**
	 * This method is for implicitWaitSec.
	 * 
	 * @param secs
	 */
	public void implicitWaitSec(long secs) {
		try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is for switchHandle.
	 */
	public void switchHandle(String handle) {
		DriverManager.getDriver().switchTo().window(handle);
	}

	/**
	 * This method is for switchBackFromHandle.
	 */
	public void switchBackFromHandle() {
		DriverManager.getDriver().close();
		DriverManager.getDriver().switchTo().window(handles);
	}

	/**
	 * This method is for getElement.
	 * 
	 * @param by
	 * @param secs
	 * @return webElement
	 * 
	 */
	public WebElement getElement(By by, long secs) {
		WebElement webElement = null;
		try {
			webElement = (new WebDriverWait(DriverManager.getDriver(), secs))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception ex) {
			takeScreenshot();
			testCaseLogger.get().fail("Coudn't able to get the element " + ex.getLocalizedMessage());
		}

		return webElement;
	}

	/**
	 * This method is for getElementLoop.
	 * 
	 * @param by
	 * @param secs
	 * @param loop
	 * @return webElement
	 */
	public WebElement getElementLoop(By by, long secs, int loop) {
		WebElement webElement = null;
		for (int index = 0; index < loop; index++) {
			webElement = getElement(by, secs);
			if (webElement != null) {
				return webElement;
			}
		}
		return webElement;
	}

	/**
	 * This method is for getElementLoop.
	 * 
	 * @param by
	 * @param secs
	 * @return
	 */
	public WebElement getElementLoop(By by, long secs) {
		return getElementLoop(by, secs, 3);
	}

	/**
	 * This method is for getElementList.
	 * 
	 * @param by
	 * @param secs
	 * @return list
	 */
	public List<WebElement> getElementList(By by, long secs) {
		List<WebElement> list = null;
		try {
			list = (new WebDriverWait(DriverManager.getDriver(), secs))
					.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		} catch (Exception ex) {
			takeScreenshot();
			testCaseLogger.get().fail("getElementList EXCEPTION : " + ex.toString());
		}
		return list;
	}

	/** Generate random ID for testing. */
	/**
	 * This method is for getRandomNumber.
	 * 
	 * @param min
	 * @param max
	 * @return integer
	 */
	public int getRandomNumber(int min, int max) {
		return (min + (int) (Math.random() * ((max - min) + 1)));
	}

	/**
	 * This method is for clickElementFromList.
	 * 
	 * @param by
	 * @return WebElement
	 */
	protected void clickElementFromList(By by) {
		List<WebElement> list = getElementList(by, 5);
		testCaseLogger.get().info("CLICK ELEMENT LIST: " + list);
		// need to select randomly one option from list
		int random = getRandomNumber(0, list.size() - 1);
		WebElement ele = list.get(random);
		testCaseLogger.get()
				.info("RANDOM ELEMENT SELECTED: " + list.size() + " - " + ele.getClass() + "--" + ele.toString());
		click(ele, "element");
	}

	/**
	 * Checks if the element is in the DOM and enabled.
	 * 
	 * @param by - selector to find the element
	 * @return true or false
	 */
	public boolean isElementEnabledAndDisplay(WebElement element) {
		try {
			highlightElement(element);
			return element.isEnabled();

		} catch (NoSuchElementException e) {
			takeScreenshot();
			testCaseLogger.get().fail("isElementEnabledAndDisplay EXCEPTION : " + e.toString());
			return false;
		}
	}

	/**
	 * Checks if the element is in the DOM and displayed.
	 * 
	 * @param by - selector to find the element
	 * @return true or false
	 */
	public boolean isElementPresentAndDisplay(By by) {
		try {
			return DriverManager.getDriver().findElement(by).isDisplayed();

		} catch (NoSuchElementException e) {
			takeScreenshot();
			testCaseLogger.get().fail("isElementPresentAndDisplay EXCEPTION : " + e.toString());
			return false;
		}
	}

	/**
	 * Checks if the element is in the DOM and displayed.
	 * 
	 * @param by - selector to find the element
	 * @return true or false
	 */
	public boolean isElementPresentAndDisplay(WebElement element) {
		try {
			highlightElement(element);
			testCaseLogger.get().pass("Element is present and visible " + element.getText());
			return element.isDisplayed();
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().fail("isElementPresentAndDisplay EXCEPTION : " + e.toString());
			return false;
		}
	}

	/**
	 * This method is for quitDriver.
	 * 
	 */
	public void quitDriver() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
	}

	public String executeJavascript(String javascript) {
		return String.valueOf(((JavascriptExecutor) DriverManager.getDriver()).executeScript(javascript));
	}

	/**
	 * This method will perform Java Script click action on web element
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */
	public void jsClick(WebElement pageElement, String logMessage) {
		try {
			scrollIntoView(pageElement, logMessage);
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", pageElement);
			testCaseLogger.get().pass("Verify click action on: " + logMessage);
		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get().fail(exception.getLocalizedMessage());

		}
	}

	public  void jsSendKeys(WebElement element, String keysToSend) {
		try {
			JavascriptExecutor ex = (JavascriptExecutor) DriverManager.getDriver();
			ex.executeScript("arguments[0].value='" + keysToSend + "';", element);
			testCaseLogger.get().pass("Verify send Keys " + keysToSend);
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().fail("Verify send Keys didnt success " + e.getLocalizedMessage());
		}
	}

	/**
	 * This method will perform click action on web element
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */

	public void clickAction(WebElement pageElement, String logMessage) {
		try {
			highlightElement(pageElement);
			Actions action = new Actions(DriverManager.getDriver());
			action.click(pageElement);
			action.perform();
			testCaseLogger.get().pass("Verify click action on: " + logMessage);
		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get().fail("Not able to click using Action on Element" + exception.getLocalizedMessage());
		}
	}

	/**
	 * This method will enter text in to text field using Action
	 * 
	 * @param WebElement pageElement
	 * @param String     value
	 * @param String     logMessage
	 * @return void
	 */

	public void sendKeysAction(WebElement pageElement, String value, String logMessage) {
		try {
			highlightElement(pageElement);
			Actions action = new Actions(DriverManager.getDriver());
			action.click(pageElement);
			action.sendKeys(pageElement, value);
			action.perform();

			testCaseLogger.get().pass("Verify text is entered to " + logMessage);

		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get().fail(exception.getLocalizedMessage());

		}
	}

	/**
	 * This method will enter text in to text field
	 * 
	 * @param WebElement pageElement
	 * @param String     value
	 * @param String     logMessage
	 * @return void
	 */
	public void enterKeys(WebElement pageElement, Keys key, String logMessage) {
		try {
			highlightElement(pageElement);
			pageElement.sendKeys(key);
			testCaseLogger.get().pass("Verify key is entered to " + logMessage);

		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get().fail(
					"Couldn't enter key to " + logMessage + " due to exception >>> " + exception.getLocalizedMessage());

		}
	}

	/**
	 * This method will enter text in to text field
	 * 
	 * @param WebElement pageElement
	 * @param String     value
	 * @param String     logMessage
	 * @return void
	 */
	public void sendKeys(WebElement pageElement, String value, String logMessage) {
		try {
			highlightElement(pageElement);
			pageElement.sendKeys(value);
			testCaseLogger.get().pass("Verify text is entered to " + logMessage);

		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get().fail("Couldn't enter values to " + logMessage + " due to exception >>> "
					+ exception.getLocalizedMessage());

		}
	}

	public void scrollIntoView(WebElement element, String msg) {
		try {
			((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);",
					element);
			highlightElement(element);
			testCaseLogger.get().pass("Moved to the element " + msg);
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().warning("Couldn't find that element in DOM " + e.getLocalizedMessage());
		}
	}

	/**
	 * 
	 * 
	 * @param pageElement
	 * @param logMessage
	 */

	public void focus(WebElement pageElement, String logMessage) {
		Actions builder = new Actions(DriverManager.getDriver());
		builder.moveToElement(pageElement).build().perform();
	}

	/**
	 * This method will mouse hover the element
	 * 
	 * @author prabhudatta
	 * @param WebElement element
	 * @return void
	 */

	public void mouseHover(WebElement element) {
		try {
			Actions action = new Actions(DriverManager.getDriver());
			action.moveToElement(element).build().perform();
			testCaseLogger.get().pass("Mouse Hover on element");
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().fail(e.getLocalizedMessage());

		}
	}

	/**
	 * 
	 * 
	 * @param dropdownList
	 * @param timeOutPeriod
	 * @return
	 */

	public WebElement waitForOptionToBePopulatedInList(final WebElement dropdownList, int timeOutPeriod) {

		WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), Constants.DRIVER_TIME_OUT);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					List<WebElement> options = dropdownList.findElements(By.tagName("option"));
					if (options.size() > 1) {
						return dropdownList;
					} else
						return null;

				} catch (NoSuchElementException ex) {
					takeScreenshot();
					return null;
				} catch (StaleElementReferenceException ex) {
					takeScreenshot();
					return null;
				} catch (NullPointerException ex) {
					takeScreenshot();
					return null;
				}
			}

		});

	}

	@SuppressWarnings("unchecked")
	public void waitForElement(WebElement element, int duration) {
		Wait wait = new FluentWait(DriverManager.getDriver()).withTimeout(Duration.ofSeconds(duration))
				.pollingEvery(Duration.ofMillis(duration)).ignoring(Exception.class);
		wait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					if (element.isDisplayed()) {
						return element;
					} else
						return null;
				} catch (NoSuchElementException ex) {
					takeScreenshot();
					testCaseLogger.get().fail(ex.getLocalizedMessage());
					ex.printStackTrace();
					return null;
				} catch (StaleElementReferenceException ex) {
					takeScreenshot();
					testCaseLogger.get().fail(ex.getLocalizedMessage());
					ex.printStackTrace();
					return null;
				} catch (NullPointerException ex) {
					takeScreenshot();
					testCaseLogger.get().fail(ex.getLocalizedMessage());
					ex.printStackTrace();
					return null;
				}
			}

		});
	}

	public WebElement waitForElementToBeDisplayed(final WebElement element, int timeOutPeriod,
			final String logMessage) {

		// try{
		WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), timeOutPeriod);
		webDriverWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));

		return webDriverWait.until(new ExpectedCondition<WebElement>() {

			public WebElement apply(WebDriver driver) {
				try {
					if (element.isDisplayed()) {
						testCaseLogger.get().pass("Found the Element is displayed");
						return element;
					} else
						return null;
				} catch (NoSuchElementException ex) {
					takeScreenshot();
					testCaseLogger.get().fail(ex.getLocalizedMessage());
					Assert.assertTrue(false);
					return null;
				} catch (StaleElementReferenceException ex) {
					takeScreenshot();
					testCaseLogger.get().fail(ex.getLocalizedMessage());
					Assert.assertTrue(false);
					return null;
				} catch (NullPointerException ex) {
					takeScreenshot();
					testCaseLogger.get().fail(ex.getLocalizedMessage());
					Assert.assertTrue(false);
					return null;
				}
			}

		});

	}

	public void waitForElementToDisappear(final By by, int timeOutPeriod) {

		FluentWait<By> fluentWait = new FluentWait<By>(by);
		fluentWait.pollingEvery(Duration.ofMillis(Constants.DRIVER_TIME_OUT));
		fluentWait.withTimeout(Duration.ofSeconds(timeOutPeriod));
		fluentWait.until(new Function<By, Boolean>() {
			public Boolean apply(By by) {
				return !DriverManager.getDriver().findElement(by).isDisplayed();
			}
		});

	}

	/**
	 * 
	 * 
	 * @param element
	 * @param timeOutPeriod
	 */

	@SuppressWarnings("deprecation")
	public void waitForElementToDisappear(final WebElement element, int timeOutPeriod) {

		WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Constants.DRIVER_TIME_OUT);
		wait.pollingEvery(2, TimeUnit.SECONDS);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will perform click action on web element
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */
	public void selectListBox(WebElement pageElement, String value, String by) {

		try {
			highlightElement(pageElement);
			jsClick(pageElement, value);
			Select select = new Select(pageElement);
			if (by.equalsIgnoreCase("ByVisibleText")) {
				select.selectByVisibleText(value);
				testCaseLogger.get().pass("Selected the element by Visible Text" + value);
			}

			else if (by.equalsIgnoreCase("Index")) {
				select.selectByIndex(Integer.parseInt(value));
				testCaseLogger.get().pass("Selected the element by Index" + value);
			}

			else if (by.equalsIgnoreCase("value")) {
				select.selectByValue(value);
				testCaseLogger.get().pass("Selected the element by value" + value);
			}

		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get()
					.fail("Couldn't select the list box with " + by + " due to " + exception.getLocalizedMessage());

		}
	}

	/**
	 * This method will perform click action on web element
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */
	public void selectDropDown(WebElement pageElement, String value, String by) {

		try {
			Select select = new Select(pageElement);
			if (by.equalsIgnoreCase("ByVisibleText")) {
				select.selectByVisibleText(value);
				testCaseLogger.get().pass("Selected the element by Visible Text" + value);
			}

			else if (by.equalsIgnoreCase("Index")) {
				select.selectByIndex(Integer.parseInt(value));
				testCaseLogger.get().pass("Selected the element by Index" + value);
			}

			else if (by.equalsIgnoreCase("value")) {
				select.selectByValue(value);
				testCaseLogger.get().pass("Selected the element by value" + value);
			}

		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get()
					.fail("Couldn't select the list box with " + by + " due to " + exception.getLocalizedMessage());
		}
	}
	
	/**
	 * This method will perform click action on web element
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */
	public void selectInput(WebElement pageElement, String value, WebElement input) {

		try {
			highlightElement(pageElement);
			jsClick(pageElement, value);
			
			sendKeys(input, value, value);
			enterKeys(input, Keys.ENTER, value);
			testCaseLogger.get()
			.pass(" select the list box with " + value);
			
			
		} catch (Exception exception) {
			takeScreenshot();
			testCaseLogger.get()
					.fail("Couldn't select the list box with "  + " due to " + exception.getLocalizedMessage());
		}
	}


	public void killNode() {
		DriverManager.getDriver().navigate()
				.to("http://localhost:4444/lifecycle-manager/LifecycleServlet?action=shutdown");

	}

	/**
	 * This method will perform click action on web element
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */
	public void clickListBox(WebElement pageElement, String value) {
		try {
			highlightElement(pageElement);

			List<WebElement> taskGrpList = pageElement.findElements(By.tagName("li"));
			for (WebElement tsg : taskGrpList) {

				System.out.println(tsg.getText());
				if (tsg.getText().contains(value.trim())) {
					click(tsg, value);
					testCaseLogger.get().pass("Selected the element " + value);
					break;
				}
			}
		} catch (Exception e) {
			takeScreenshot();
			testCaseLogger.get().fail(e.getLocalizedMessage());
			
		}
	}

	/**
	 * This method will take screenshot for any step
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */

	public String captureScreenshot(WebDriver driver, String screenShotName) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + screenShotName
				+ ".png";
		File destination = new File(dest);
		try {
			FileUtils.copyFile(source, destination);
			testCaseLogger.get().addScreenCaptureFromPath(destination.getAbsolutePath());
		} catch (Exception e) {
		}
		return dest;
	}

	/**
	 * This method will take screenshot for any step
	 * 
	 * @param WebElement pageElement
	 * @param String     logMessage
	 * @return void
	 */

	public String captureFullPageScreenshot(WebDriver driver, String screenShotName) {

		String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + screenShotName
				+ ".png";
		Shutterbug.shootPage(driver, ScrollStrategy.WHOLE_PAGE, true).withName(screenShotName).save();

		try {
			testCaseLogger.get().addScreenCaptureFromPath(new File(dest).getAbsolutePath());
		} catch (Exception e) {
		}
		return dest;
	}

	public List<WebElement> findElements(By by) {
		return DriverManager.getDriver().findElements(by);
	}

	public static void highlightElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
		jse.executeScript("arguments[0].style.border='3px solid red'", element);
	}

	public void acceptAlert() {
		DriverManager.getDriver().switchTo().alert().accept();
	}

	public void rejectAlert() {
		DriverManager.getDriver().switchTo().alert().dismiss();
	}

	public boolean isCheckBoxChecked(WebElement el) {
		highlightElement(el);
		testCaseLogger.get().info("Checkbox is checked " + el.isSelected());
		return el.isSelected();
	}

	public boolean isAlphaNumeric(String s) {
		return s != null && s.matches("^[a-zA-Z0-9]*$");
	}

	public String[] cleanArray(String[] array) {
		return Arrays.stream(array).filter(x -> !StringUtils.isBlank(x)).toArray(String[]::new);
	}

	public boolean isElementMandatory(WebElement el, String msg) {
		boolean isMandatory = false;
		try {
			scrollIntoView(el, msg);
			if (el.getText().contains("*")) {
				testCaseLogger.get().info(msg + "Element is Mandatory");
				isMandatory = true;
			} else {
				testCaseLogger.get().info(msg + "Element is Not Mandatory");
				isMandatory = false;
			}
		} catch (Exception e) {
			testCaseLogger.get().warning(msg + "Element is Not Mandatory " + e.getLocalizedMessage());
			isMandatory = false;
		}

		return isMandatory;
	}

	public List<WebElement> getAllOptions(WebElement el) {
		try {
			Select select = new Select(el);
			return select.getOptions();
		} catch (Exception e) {
			testCaseLogger.get().fail("Couldn't get option for this Select element due to " + e.getLocalizedMessage());
			return null;
		}
	}

	public int getMonthNumber(String monthName) {
		try {
			Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthName);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int month = cal.get(Calendar.MONTH);
			testCaseLogger.get().pass("converted month to its correponding integer value ");

			return month + 1;
		} catch (Exception e) {
			testCaseLogger.get()
					.fail("Couldn't convert month to its correponding integer value " + e.getLocalizedMessage());
			return 0;
		}
	}

	public List<WebElement> printTableHeader(WebElement table) {

		WebElement tableHead = table.findElement(By.tagName("thead"));
		WebElement headerRow = tableHead.findElement(By.tagName("tr"));
		List<WebElement> tableHeaderCol = headerRow.findElements(By.tagName("th"));
		for (WebElement tableHeader : tableHeaderCol) {
			testCaseLogger.get().info("Header present as " + tableHeader);
		}
		return tableHeaderCol;
	}

	public boolean deleteCsvFile(String filePath) {
		boolean isDeleted = false;
		try {
			File file = new File(filePath);
			file.delete();
			isDeleted = true;
			testCaseLogger.get().pass("File has been Deleted");
		} catch (Exception e) {
			isDeleted = false;
			testCaseLogger.get().warning("Couldn't delete the file. " + e.getLocalizedMessage());
		}

		return isDeleted;
	}
	
	public void takeScreenshot()
	{
		String fileName = this.getClass().getName();
		
		String dest = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator;
		try {
		File file = new File(dest + fileName+ "fail.png");
		//To add it in the extent report 
		TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, file);
		testCaseLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromPath(file.getAbsolutePath()).build());
		}
		catch(Exception e) {
			
		}
		
		
	}
}
