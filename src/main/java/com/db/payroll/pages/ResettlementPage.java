package com.darwinbox.payroll.pages;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;

import com.framework.testutils.CSVFileReader;
import com.framework.testutils.DriverManager;
import com.framework.testutils.PDFReader;
import com.framework.ui.pageobjects.BasePage;

public class ResettlementPage extends BasePage {

	@FindBy(xpath = ".//*[@href='/payroll/setup/dashboard']")
	private List<WebElement> payRollTile;

	@FindBy(xpath = "//*[@href='/payroll/setup/fandf/resettlement/1']")
	private WebElement resttlementHeaderSubmenu;

	@FindBy(id = "month_dropdown")
	private WebElement resettlementMonthAndYearDrpDwn;

	@FindBy(id = "user_list_dropdown")
	private WebElement resettlementGropupCompany;

	@FindBy(xpath = "//*[@id='example']//tbody//tr//*[@type='checkbox']")
	private List<WebElement> resettlementEmployeeCheckBoxList;

	@FindBy(xpath = "//*[@id='fandfprocess']//*[@class='btn btn-primary btn-sm text-uppercase first_step_run']")
	private WebElement resettlementRun;

	@FindBy(xpath = "//*[@class='btn btn-default btn-sm text-uppercase procees_arrearsandlop_page']")
	private WebElement nextButtonResettlementInputs;

	@FindBy(xpath = "//*[@class='inner-heading site-color']")
	private WebElement resettlementPageName;

	@FindBy(xpath = "//*[@class='btn btn-default btn-sm ml-4 text-uppercase procees_salarygenerated_page']")
	private WebElement nextButtonResettlementPreview;

	@FindBy(xpath = "//*[@class='btn btn-primary procees_salarygenerated_page']")
	private WebElement resettlementGenerateButton;

	@FindBy(xpath = "//*[@class='col-md-12 table-responsive']")
	private WebElement resettlementInputsTable;

	@FindBy(xpath = "//*[@class='ui  db-dropdown ']")
	private WebElement resettlementMonthDropdown;

	@FindBy(id = "admin_panel")
	private WebElement resettlementPreviewTable;

	@FindBy(xpath = "//*[@class='alert alert-success']")
	private WebElement generateResettlementPayslipSuccess;

	@FindBy(xpath = "//*[contains(@class, 'alert alert-')]")
	private WebElement generatePayslipAlert;

	@FindBy(xpath = "//*[@class='alert alert-danger']")
	private WebElement generateResettlementPayslipFailure;

	@FindBy(xpath = "//*[@class='btn btn-primary procees_salarygenerated_page']")
	private WebElement generateResettlementPayslip;

	@FindBy(id = "example")
	private WebElement employeeResettlementDataTable;

	@FindBy(xpath = "//*[@id= 'month_dropdown']//*[@selected='selected']")
	private WebElement resettlementMonthAndYearDrpDwnSelected;

	@FindBy(xpath = "//*[@id='example']//tbody//tr[1]//td//*[@type='checkbox']")
	private WebElement firstEmployeeCheckBox;

	@FindBy(xpath = "//*[@id='example']//*[@id='bulk_check']")
	private WebElement allEmployeeSelectionCheckBox;

	@FindBy(xpath = "//*[@id='info_message']//*[@class='info_message_text message_area']")
	private WebElement infoMessage;

	@FindBy(xpath = "//*[contains(@class,'dbProfile')]")
	private WebElement profileSelection;

	@FindBy(xpath = "//*[contains(@class,'dbPersonalDetails')]")
	private WebElement personalDetails;

	@FindBy(id = "field_date_of_joining")
	private WebElement dojFromProfile;

	@FindBy(id = "field_employee_id")
	private WebElement empIDFromProfile;

	@FindBy(xpath = "//*[@class='user-profile-info']//h3")
	private WebElement nameFromProfile;

	@FindBy(xpath = "//*[@class='user-profile-info']//p")
	private List<WebElement> designationFromProfile;

	@FindBy(xpath = "//*[@class= 'single-detail-field mb-24']//h6")
	private List<WebElement> headingsFromEmployeeProfile;

	@FindBy(xpath = "//*[@class= 'single-detail-field mb-24']//p")
	private List<WebElement> valuesFromEmployeeProfile;

	@FindBy(xpath = "//*[@class='ui  db-dropdown ']")
	private WebElement resettlementMonthDrpDwn;

	// ===================================

	@FindBy(xpath = "//*[@type='text' and contains(@id,'lop')]")
	private WebElement lopInput;

	@FindBy(xpath = "//*[@type='text' and contains(@id,'arrears')]")
	private WebElement arrearInput;

	@FindBy(xpath = "//*[@type='text' and contains(@id,'leave_encashment')]")
	private WebElement leaveEncashmentInput;

	@FindBy(xpath = "//*[@type='text' and contains(@id,'recovery_days')]")
	private WebElement recoveryInput;

	@FindBy(xpath = "//*[@class='db-table-one no-row-border']//tbody/tr//td[3]")
	private List<WebElement> ctcProrationMonthlyAmmount;

	@FindBy(xpath = "//*[@href='#ctc_proration']")
	private WebElement ctcProration;

	@FindBy(id = "leave_settings_create_btn")
	private WebElement extraSettingsSaveButton;

	@FindBy(id = "TenantProfile_cal_resettlement_on_basic")
	private WebElement calculateResettlementOnBasic;

	@FindBy(id = "TenantProfile_cal_resettlement_on_basic")
	private List<WebElement> calculateResettlementOnBasicList;

	@FindBy(id = "TenantProfile_leave_encashment_days_denom")
	private WebElement calLeOnNumOfDays;

	@FindBy(xpath = "//*[@class=\"main-logo bg-primary-white pull-left\"]/a/img")
	private WebElement companyProfleImage;

	@FindBy(xpath = ".//*[@href='/settings/payroll']")
	private WebElement settingsPayRollSubMenu;

	@FindBy(xpath = "//*[@class='ht_master handsontable']//*[@class='htCore'] ")
	private WebElement taxsheetTable;

	@FindBy(xpath = "//*[contains(@href,'/payroll/runPayroll/BankList')]")
	private WebElement bankListButton;

	@FindBy(xpath = "//*[contains(@class,'stepwizard-row')]//div")
	private List<WebElement> progressBarList;

	@FindBy(id = "TenantProfile_recovery_denominator")
	private WebElement recoveryDenominator;

	@FindBy(xpath = "//*[contains(@href,'payroll/import')]")
	private WebElement importButton;

	@FindBy(xpath = "//*[contains(@href,'/payroll/setup/GetExportData')]")
	private WebElement exportButton;

	@FindBy(id = "TenantProfile_calculate_gst_on_recovery_days")
	private WebElement extraSettingsGSTRecovery;

	@FindBy(id = "TenantProfile_cal_recovery_gross")
	private WebElement extraSettingsRecoveryGross;

	@FindBy(id = "TenantProfile_effective_date")
	private WebElement extraSettingsEffectiveDate;

	@FindBy(xpath = "//*[@type='search']")
	private WebElement previewAndGenerateSearch;

	@FindBy(xpath = "//*[@class='add_additionalPay circle-btn']")
	private WebElement additionalPayementsButton;

	@FindBy(id = "TransactionFormModel_transaction_name")
	private List<WebElement> transactionName;

	@FindBy(id = "TransactionFormModel_transaction_amount")
	private List<WebElement> transactionAmount;

	@FindBy(id = "add_more_fields_modal_new")
	private WebElement addAnotherTransaction;

	@FindBy(id = "TransactionFormModel_reimbursement")
	private List<WebElement> nonTaxable;

	@FindBy(xpath = "//*[@class='db-Delete']")
	private List<WebElement> transactionDelete;

	@FindBy(id = "add_addPay_btn")
	private WebElement addPaymentsUpdateBtn;
	
	@FindBy(xpath = "//*[@class='payslip_non-bulk-action db-btn btn-secondary btn-sm' and @title]")
	private WebElement form16;

	@FindBy(name = "payslips_year")
	private WebElement payslipsFYDrpDown;

	@FindBy(xpath = ".//*[@aria-controls='pay_slips']")
	private WebElement paySlipsHeaderSubMenu;
	
	@FindBy(name="year_dropdown")
	private WebElement payPackageFYDrpDown;
	
	@FindBy(xpath="//*[@href='#form12b']")
	private WebElement form12B;
	
	@FindBy(xpath="//*[@href='#form12bmidexit']")
	private WebElement form12BMidExit;
	
	@FindBy(id = "TenantProfile_alias_for_resettlement_payslip")
	private WebElement resettlementAlias;
	
	
	

	public ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return null;
	}

	public ResettlementPage getResettlementPage() {

		return (ResettlementPage) openPage(ResettlementPage.class);
	}

	public void navigateToResettlement(String submenu) {
		waitForElementToBeDisplayed(payRollTile.get(1), 30, "PayRoll Tile");
		scrollIntoView(payRollTile.get(1), "PayRoll Tile");
		waitForPageLoad(90);
		click(payRollTile.get(1), "Payroll module");
		waitForPageLoad(45);

		switch (submenu.toUpperCase()) {

		case "RESETTLEMENT":
			jsClick(resttlementHeaderSubmenu, "Resettlement Header Submenu");
			testCaseLogger.get().pass("Successfully navigated to Resettlement sub menu");
			waitForPageLoad(45);
			break;

		default:
			break;
		}

	}

	public void selectResettlementMonthAndYearDropdown(String yearAndMonth) {
		selectListBox(resettlementMonthAndYearDrpDwn, yearAndMonth, "value");

	}

	public void selectResettlementGroupCompanyDropdown(String GroupCompany) {
		selectListBox(resettlementGropupCompany, GroupCompany, "ByVisibleText");

	}

	public void selectResettlementEmployees(int numberEmployeesSelection) {
		int count = 0;
		if (resettlementEmployeeCheckBoxList.size() >= numberEmployeesSelection) {
			for (WebElement webElement : resettlementEmployeeCheckBoxList) {
				click(webElement, "Employee Selection");
				implicitWaitSec(1000);
				count++;
				if (count == numberEmployeesSelection) {
					break;
				}

			}

		} else {
			testCaseLogger.get().fail("Number of entries are greater than employees present in the list");
		}
	}

	public void clickOnResettlement() {
		click(resettlementRun, "Run Resettlemt");
		waitForPageLoad();

	}

	public void clickOnNextButtonInResettlementInputsPage() {
		implicitWaitSec(1000);
		click(nextButtonResettlementInputs, "Next button in Resettlement inputs page");
		implicitWaitSec(5000);
	}

	public void verifyClicksOnNextSalaryRegisterIsOpened() {
		implicitWaitSec(3000);
		if (nextButtonResettlementPreview.isDisplayed()) {
			testCaseLogger.get().pass("Clicks on Next Salary Register is opening");
		} else {
			testCaseLogger.get().fail("Clicks on Next Salary Register is not opening");
		}
	}

	public void clickOnNextButtonInResettlementPreviewPage() {
		implicitWaitSec(1000);
		click(nextButtonResettlementPreview, "Next button in Resettlement Preview page");
		implicitWaitSec(5000);
	}

	public void verifyresettlementGenerateButtonIsEnable() {
		if (resettlementGenerateButton.isEnabled()) {
			testCaseLogger.get().pass("Generate Resettlement Slip is Enabled");
		} else {
			testCaseLogger.get().fail("Generate Resettlement Slip is not Enabled");
		}
	}

	public void verifyAdminIsAbleToInputAllTheValuesInResettlement() {
		boolean result = false;
		List<WebElement> tableHead = resettlementInputsTable.findElements(By.tagName("th"));
		int i = 1;
		for (WebElement tHead : tableHead) {
			if (tHead.getText().equalsIgnoreCase("Total LOP (Days)")) {
				String value = findElement(By.xpath("//*[@class='col-md-12 table-responsive']//td[" + i + "]/input"))
						.getAttribute("type");
				if (value.equalsIgnoreCase("text")) {
					result = true;
				} else {
					result = true;
					break;
				}
			} else if (tHead.getText().equalsIgnoreCase("Arrears (Days)")) {
				String value = findElement(By.xpath("//*[@class='col-md-12 table-responsive']//td[" + i + "]/input"))
						.getAttribute("type");
				if (value.equalsIgnoreCase("text")) {
					result = true;
				} else {
					result = true;
					break;
				}
			} else if (tHead.getText().equalsIgnoreCase("Leave Encashment (Days)")) {
				String value = findElement(By.xpath("//*[@class='col-md-12 table-responsive']//td[" + i + "]/input"))
						.getAttribute("type");
				if (value.equalsIgnoreCase("text")) {
					result = true;
				} else {
					result = true;
					break;
				}
			} else if (tHead.getText().equalsIgnoreCase("Recovery (Days)")) {
				String value = findElement(By.xpath("//*[@class='col-md-12 table-responsive']//td[" + i + "]/input"))
						.getAttribute("type");
				if (value.equalsIgnoreCase("text")) {
					result = true;
				} else {
					result = true;
					break;
				}
			}
			i++;
		}
		if (result) {
			testCaseLogger.get().pass("Admin is able to input all the values ");
		} else {
			testCaseLogger.get().fail("Admin is not able to input all the values");
		}

	}

	public void verifyResettlementMonthIsAbleToSelect() {
		int j = 0;
		List<WebElement> options = new Select(resettlementMonthDropdown).getOptions();
		for (int i = 0; i < options.size(); i++) {
			Select dropdownItems = new Select(resettlementMonthDropdown);
			dropdownItems.selectByIndex(i);
			j++;
		}
		if (j == options.size()) {
			testCaseLogger.get().pass("Admin is able to select payout month");
		}
	}

	public void verifyAdminIsAbleToResettlementOnlyForFAndFEmployees() {
		List<WebElement> tableHead = resettlementPreviewTable.findElements(By.tagName("th"));
		for (WebElement tHead : tableHead) {
			if (tHead.getText().equalsIgnoreCase("F&F month")) {
				testCaseLogger.get().pass("Admin is able to Resettlement only for F and F Employees");
			}
		}

	}

	public void clickOnGenerateResettlementPayslip() {
		waitForPageLoad();
		click(generateResettlementPayslip, "Generate Resettlement payslip");
	}

	public void verifyResettlementPayslipGeneration() {
		waitForPageLoad();
		if (generatePayslipAlert.getText().contains("successfully generated")) {
			testCaseLogger.get().pass(generatePayslipAlert.getText());
		} else if (generatePayslipAlert.getText().contains("Error")) {
			testCaseLogger.get().pass(generatePayslipAlert.getText());
		}

	}

	public void verifyResettlementPayslip() {
		String filePath = System.getProperty("user.dir") + "/Download/ResettlementPayslip.pdf";
		String text = new PDFReader().readPDFFile(filePath);
		String paysliparr[] = text.split("\n");
		String paySlipHead = paysliparr[4];

		if (paySlipHead.equalsIgnoreCase("RESETTLEMENT PAYSLIP")) {
			testCaseLogger.get().pass("The Payslip header is showing as expected. ie..  " + paySlipHead);
		} else {
			testCaseLogger.get().fail(
					"The Payslip header is  showing as " + paySlipHead + " but the expected is RESETTLEMENT PAYSLIP");
		}
		int count = paysliparr.length;
		for (int i = 17; i < paysliparr.length; i++) {

			if (paysliparr[i].contains("Leave Encashment Days")) {
				testCaseLogger.get().pass("Under earnings leave enchshment is displaying");
			}
			count--;
			if (count == 17) {
				testCaseLogger.get().fail("Under earnings leave enchshment is not displaying");
				testCaseLogger.get().fail("Under earnings componenets which are processed are not showing");
			}
		}

		for (int i = 17; i < paysliparr.length; i++) {

			if (paysliparr[i].contains("LOP Monthwise")) {
				testCaseLogger.get().fail("LOP Monthwise is displaying");

			}
			count--;
			if (count == 17) {
				testCaseLogger.get().pass("LOP Monthwise is not displaying");

			}
		}
		String arr[] = paysliparr[19].split("  ");
		if (arr[arr.length - 1].equalsIgnoreCase("0")) {
			testCaseLogger.get().pass("TDS is not aplicable. The TDS Value is " + arr[arr.length - 1]);
		}

	}

	public void navigateResettlementEmp() {
		DriverManager.getDriver().get("https://payroll.qa.darwinbox.io/employeeprofile/view/id/256652");
	}

	public HashMap<String, String> selectResettlementEmployee() {
		HashMap<String, String> resettlementEmployeeDetails = new HashMap<String, String>();
		Select yearAndMonth = new Select(resettlementMonthAndYearDrpDwn);
		outerloop: for (int i = 0; i < yearAndMonth.getOptions().size(); i++) {
			waitForPageLoad(90);
			yearAndMonth.selectByIndex(i);
			waitForPageLoad(90);
			List<WebElement> tableRows = employeeResettlementDataTable.findElement(By.tagName("tbody"))
					.findElements(By.tagName("tr"));
			if (tableRows.size() > 0) {
				for (WebElement row : tableRows) {
					if (!(row.getText().contains("No Resettlement Employees"))) {
						implicitWaitSec(1000);
						resettlementEmployeeDetails.put("SelectedYearAndMonth",
								resettlementMonthAndYearDrpDwnSelected.getText());
						String empID = findElement(By.xpath("//*[@id='example']//tbody//tr[1]//td[2]")).getText();
						resettlementEmployeeDetails.put("empID", empID);
						click(findElement(By.xpath("//*[@id='example']//tbody//tr[1]//td//*[@type='checkbox']")),
								"employee selection");
						break outerloop;
					}

				}
			}
		}
		return resettlementEmployeeDetails;
	}

	public void verifyResttlementYearAndCompanyDropdowns() {
		Select yearAndMonth = new Select(resettlementMonthAndYearDrpDwn);
		int count = 0;
		for (int i = 0; i < yearAndMonth.getOptions().size(); i++) {
			waitForPageLoad(90);
			yearAndMonth.selectByIndex(i);
			count++;
		}
		if (count == yearAndMonth.getOptions().size()) {
			testCaseLogger.get().pass("Admin is able to select All the years from the dropdown");
		} else {
			testCaseLogger.get().fail("Admin is not able to select All the years from the dropdown");
		}
		selectResettlementGroupCompanyDropdown("ALL");
		testCaseLogger.get().pass("Admin is able to select All group companies from the list of companies");
	}

	public void verifyEmployeeGroupPageTable() {
		String empID = null;
		String name = null;
		String designation = null;
		String department = null;
		String doj = null;
		String dor = null;
		String dol = null;
		implicitWaitSec(5000);
		NavigateToCBPage();
		switchToNewWindow();
		implicitWaitSec(2000);
		verifyCBPageNavigation();
		click(profileSelection, "Profile Selection");
		click(personalDetails, "Personal Details");
		implicitWaitSec(1000);

		HashMap<String, String> profileValues = new HashMap<>();

		for (int i = 1; i < headingsFromEmployeeProfile.size(); i++) {
			if (headingsFromEmployeeProfile.get(i).getText().equalsIgnoreCase("Department")) {
				profileValues.put("Department", valuesFromEmployeeProfile.get(i).getText());
			}
			if (headingsFromEmployeeProfile.get(i).getText().equalsIgnoreCase("Date of resignation")) {
				profileValues.put("DOR", valuesFromEmployeeProfile.get(i).getText().replaceAll("-", " "));
			}
			if (headingsFromEmployeeProfile.get(i).getText().equalsIgnoreCase("Date of separation")) {
				profileValues.put("DOL", valuesFromEmployeeProfile.get(i).getText().replaceAll("-", " "));
			}
		}
		empID = empIDFromProfile.getText();
		designation = designationFromProfile.get(0).getText();
		department = profileValues.get("Department");
		dor = profileValues.get("DOR");
		dol = profileValues.get("DOL");
		doj = dojFromProfile.getText().replaceAll("-", " ");
		name = nameFromProfile.getText();
		// System.out.println(department);
		// System.out.println(designation);
		switchToDefault();
		implicitWaitSec(2000);
		List<WebElement> tableHeads = employeeResettlementDataTable.findElements(By.tagName("th"));
		List<WebElement> tableColumns = employeeResettlementDataTable.findElement(By.tagName("tbody"))
				.findElement(By.tagName("tr")).findElements(By.tagName("td"));
		if (tableHeads.get(1).getText().trim().equalsIgnoreCase("EMPLOYEE ID")) {
			if (tableColumns.get(1).getText().equalsIgnoreCase(empID)) {
				testCaseLogger.get().pass("Employee ID for the resettlement employee is showing");

			} else {
				testCaseLogger.get().fail("Employee ID for the resettlement employee is not showing");
			}
		}
		if (tableHeads.get(2).getText().trim().equalsIgnoreCase("NAME")) {
			if (tableColumns.get(2).getText().equalsIgnoreCase(name)) {
				testCaseLogger.get().pass("Employee NAME for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("Employee NAME for the resettlement employee is not showing");
			}
		}
		if (tableHeads.get(3).getText().trim().equalsIgnoreCase("DESIGNATION")) {
			if (tableColumns.get(3).getText().contains(designation)) {
				testCaseLogger.get().pass("DESIGNATION for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("DESIGNATION for the resettlement employee is not showing");
			}
		}
		if (tableHeads.get(4).getText().trim().equalsIgnoreCase("DEPARTMENT")) {
			if (tableColumns.get(4).getText().contains(department)) {
				testCaseLogger.get().pass("DEPARTMENT for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("DEPARTMENT for the resettlement employee is not showing");
			}
		}
		if (tableHeads.get(5).getText().trim().equalsIgnoreCase("DOJ")) {
			if (tableColumns.get(5).getText().equalsIgnoreCase(doj)) {
				testCaseLogger.get().pass("DOJ for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("DOJ for the resettlement employee is not showing");
			}
		}
		if (tableHeads.get(6).getText().equalsIgnoreCase("DOR")) {
			if (tableColumns.get(6).getText().equalsIgnoreCase(dor)) {
				testCaseLogger.get().pass("DOJ for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("DOR for the resettlement employee is not showing");
			}
		}

		if (tableHeads.get(7).getText().equalsIgnoreCase("DOL")) {
			if (tableColumns.get(7).getText().equalsIgnoreCase(dol)) {
				testCaseLogger.get().pass("DOL for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("DOL for the resettlement employee is not showing");
			}
		}
		if (tableHeads.get(8).getText().equalsIgnoreCase("F&F PAY MONTH")) {
			if (tableColumns.get(8).getText() != null) {
				testCaseLogger.get().pass("F&F PAY MONTH for the resettlement employee is showing");
				testCaseLogger.get().pass("Already F and F processed employee is displaying");
			} else {
				testCaseLogger.get().fail("F&F PAY MONTH for the resettlement employee is not showing");
				testCaseLogger.get().fail("F and F not processed employee is displaying");
			}
		}
		if (tableHeads.get(9).getText().equalsIgnoreCase("ACTIONS")) {
			testCaseLogger.get().pass("C&B Page is showing");
			if (tableColumns.get(9).getText() != null) {
				testCaseLogger.get().pass("C&B Page option for the resettlement employee is showing");
			} else {
				testCaseLogger.get().fail("C&B Page for the resettlement employee is not showing");
			}
		}

	}

	public void findResettlementEmployee() {
		Select yearAndMonth = new Select(resettlementMonthAndYearDrpDwn);
		outerloop: for (int i = 0; i < yearAndMonth.getOptions().size(); i++) {
			waitForPageLoad(90);
			yearAndMonth.selectByIndex(i);
			waitForPageLoad(90);
			if (resettlementMonthAndYearDrpDwnSelected.getText().equalsIgnoreCase("2021-04")) { // 2020-04 employee is
				// not loading
				continue;
			}
			List<WebElement> tableRows = employeeResettlementDataTable.findElement(By.tagName("tbody"))
					.findElements(By.tagName("tr"));
			if (tableRows.size() > 0) {
				for (WebElement row : tableRows) {
					if (!(row.getText().contains("No Resettlement Employees"))) {
						testCaseLogger.get().info("Employee Data found");
						break outerloop;
					}
				}
			}
		}
	}

	public void NavigateToCBPage() {
		List<WebElement> tableHeads = employeeResettlementDataTable.findElements(By.tagName("th"));

		for (int i = 1; i <= tableHeads.size(); i++) {
			if (tableHeads.get(i).getText().equalsIgnoreCase("ACTIONS")) {
				click(findElement(By.xpath("//*[@id='example']//tr[1]//td[" + (i + 1) + "]//a")), "CB");
				testCaseLogger.get().pass("Able to click on C & B Page");
				break;
			}

		}
	}

	public void verifyCBPageNavigation() {
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		if (currentUrl.contains("https://payroll.qa.darwinbox.io/payroll/employees/view/id")) {
			testCaseLogger.get().pass("Clicked on cb page is redirecting to the compensation page  ");
		} else {
			testCaseLogger.get().fail("Clicked on cb page is not redirecting to the compensation page ");
		}
	}

	public void switchToNewWindow() {
		ArrayList<String> tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		DriverManager.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
	}

	public void switchToDefault() {
		ArrayList<String> tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
		DriverManager.getDriver().switchTo().window(tabs.get(tabs.size() - 2));
	}

	public void verifyResettlementProgressBar() {
		click(firstEmployeeCheckBox, "Employee Selection");
		click(resettlementRun, "Resettlement Run");
		List<WebElement> tableRows = resettlementInputsTable.findElement(By.tagName("tbody"))
				.findElements(By.tagName("tr"));
		if (tableRows.size() == 1) {
			testCaseLogger.get()
					.pass("Employee count is flowing as expcted From Select Employee Group page to Resettlement page ");
		} else {
			testCaseLogger.get()
					.fail("Employee count is flowing as expcted From Select Employee Group page to Resettlement page");
		}
		implicitWaitSec(2000);
		if (progressBarList.get(0).getText().contains("Select Employee Group")
				&& progressBarList.get(1).getText().contains("Resettlement Inputs")
				&& progressBarList.get(2).getText().contains("Preview and Generate Payslips")) {
			testCaseLogger.get().pass("3 step progress bar is displaying with respect to the name");
		} else {
			testCaseLogger.get().fail("3 step progress bar is not displaying with respect to the name");
		}
		System.out.println(" class : :" + progressBarList.get(1).getAttribute("class"));

		if (progressBarList.get(1).getAttribute("class").contains("active")) {
			testCaseLogger.get()
					.pass("Progress is getting moved from Step1 to Step2 And with the Name Resettlement Inputs");
		} else {
			testCaseLogger.get().fail("Progress is not getting moved rom Step1 to Step2");
		}
	}

	public void verifyResettlementEmployeeCheckBoxeSelectionAndProgressBar() {

		if (!(firstEmployeeCheckBox.isSelected())) {
			click(firstEmployeeCheckBox, "employee selection");
		}
		if (firstEmployeeCheckBox.isSelected()) {
			testCaseLogger.get().pass("CheckBoxes are available and able to click");
		} else {
			testCaseLogger.get().fail("Not able to click on checkboxes");
		}

		if (!(allEmployeeSelectionCheckBox.isSelected())) {
			click(allEmployeeSelectionCheckBox, "All employee selection");
		}
		if (allEmployeeSelectionCheckBox.isSelected()) {
			testCaseLogger.get().pass("Able to click on bulk employee selection");
			click(allEmployeeSelectionCheckBox, "All employee selection unselected");
		} else {
			testCaseLogger.get().fail("Not Able to click on bulk employee selection");
		}

		click(resettlementRun, "Resettlement Button");
		waitForElement(infoMessage, 30);
		if (infoMessage.getText().equalsIgnoreCase("Please select employees")) {
			testCaseLogger.get().pass("Error messsage is showing when no employees selected");
		} else {
			testCaseLogger.get().fail("Error messsage is not showing when no employees selected");
		}
		verifyResettlementProgressBar();

	}

	public HashMap<String, String> getGetailsFromSelectEmployeeGroup() {
		HashMap<String, String> employeeGroupValues = new HashMap<String, String>();
		List<WebElement> tableHeads = employeeResettlementDataTable.findElements(By.tagName("th"));
		List<WebElement> tableColumns = employeeResettlementDataTable.findElement(By.tagName("tbody"))
				.findElement(By.tagName("tr")).findElements(By.tagName("td"));
		if (tableHeads.get(1).getText().trim().equalsIgnoreCase("EMPLOYEE ID")) {
			employeeGroupValues.put("empGrpEmpID", tableColumns.get(1).getText());
		}
		if (tableHeads.get(2).getText().trim().equalsIgnoreCase("NAME")) {
			employeeGroupValues.put("empGrpName", tableColumns.get(2).getText());
		}
		if (tableHeads.get(5).getText().trim().equalsIgnoreCase("DOJ")) {
			employeeGroupValues.put("empGrpDoj", tableColumns.get(5).getText());
		}
		if (tableHeads.get(6).getText().equalsIgnoreCase("DOR")) {
			employeeGroupValues.put("empGrpDor", tableColumns.get(6).getText());
		}
		if (tableHeads.get(7).getText().equalsIgnoreCase("DOL")) {
			employeeGroupValues.put("empGrpDol", tableColumns.get(7).getText());
		}
		if (tableHeads.get(8).getText().equalsIgnoreCase("F&F PAY MONTH")) {
			employeeGroupValues.put("empGrpFAndF", tableColumns.get(8).getText());
		}

		return employeeGroupValues;

	}

	public HashMap<String, String> getGetailsFromResettlementInputs() {
		HashMap<String, String> inputValues = new HashMap<String, String>();
		List<WebElement> inputsTableHeads = resettlementInputsTable.findElements(By.tagName("th"));
		List<WebElement> inputsTableColumns = resettlementInputsTable.findElement(By.tagName("tbody"))
				.findElement(By.tagName("tr")).findElements(By.tagName("td"));

		if (inputsTableHeads.get(0).getText().trim().equalsIgnoreCase("Employee ID")) {
			inputValues.put("inputsEmpID", inputsTableColumns.get(0).getText());
		}
		if (inputsTableHeads.get(1).getText().trim().equalsIgnoreCase("Name")) {
			inputValues.put("inputsName", inputsTableColumns.get(1).getText());
		}
		if (inputsTableHeads.get(2).getText().trim().equalsIgnoreCase("DOJ")) {
			inputValues.put("inputsDoj", inputsTableColumns.get(2).getText());
		}
		if (inputsTableHeads.get(3).getText().equalsIgnoreCase("DOR")) {
			inputValues.put("inputsDor", inputsTableColumns.get(3).getText());
		}
		if (inputsTableHeads.get(4).getText().equalsIgnoreCase("DOL")) {
			inputValues.put("inputsDol", inputsTableColumns.get(4).getText());
		}
		return inputValues;
	}

	public void verifyDetailsFromSelectEmployeeGroupPageToResettlementInputsPage() {
		HashMap<String, String> empGroupPageValues = getGetailsFromSelectEmployeeGroup();
		click(firstEmployeeCheckBox, "Employee Selection");
		click(resettlementRun, "Resettlement Run");
		implicitWaitSec(2000);
		HashMap<String, String> inputsPageValues = getGetailsFromResettlementInputs();
		if (empGroupPageValues.get("empGrpEmpID").equalsIgnoreCase(inputsPageValues.get("inputsEmpID"))) {
			testCaseLogger.get().pass("Same Employee Id is showing from Employee group page to resettlement inputs");
		} else {
			testCaseLogger.get()
					.fail("Same Employee Id is not showing from Employee group page to resettlement inputs");
		}
		if (empGroupPageValues.get("empGrpName").equalsIgnoreCase(inputsPageValues.get("inputsName"))) {
			testCaseLogger.get().pass("Same Employee name is showing from Employee group page to resettlement inputs");
		} else {
			testCaseLogger.get()
					.fail("Same Employee name is not showing from Employee group page to resettlement inputs");
		}
		if (empGroupPageValues.get("empGrpDoj").equalsIgnoreCase(inputsPageValues.get("inputsDoj"))) {
			testCaseLogger.get()
					.pass("Same Date Of joining is showing from Employee group page to resettlement inputs");
		} else {
			testCaseLogger.get()
					.fail("Same Date Of joining  is not showing from Employee group page to resettlement inputs");
		}
		if (empGroupPageValues.get("empGrpDor").equalsIgnoreCase(inputsPageValues.get("inputsDor"))) {
			testCaseLogger.get()
					.pass("Same Date Of Resignation is showing from Employee group page to resettlement inputs");
		} else {
			testCaseLogger.get()
					.fail("Same Date Of Resignation is not showing from Employee group page to resettlement inputs");
		}
		if (empGroupPageValues.get("empGrpDol").equalsIgnoreCase(inputsPageValues.get("inputsDol"))) {
			testCaseLogger.get()
					.pass("Same Date Of Leaving is showing from Employee group page to resettlement inputs");
		} else {
			testCaseLogger.get()
					.fail("Same Date Of Leaving  is not showing from Employee group page to resettlement inputs");
		}
	}

	public String getSystemDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void testResettlementPaymonth() {
		boolean fYear = false;
		HashMap<String, String> empGroupPageValues = getGetailsFromSelectEmployeeGroup();
		String fAndFMonthAndYear = empGroupPageValues.get("empGrpFAndF");
		int fAndFYear = Integer.parseInt(fAndFMonthAndYear.split("-")[0]);
		int fAndFMonth = Integer.parseInt(fAndFMonthAndYear.split("-")[1]);
		click(firstEmployeeCheckBox, "Employee Selection");
		click(resettlementRun, "Resettlement Run");
		implicitWaitSec(2000);
		Select resettlementPaymonth = new Select(resettlementMonthDrpDwn);
		List<WebElement> options = resettlementPaymonth.getOptions();
		String resettlementMonthAndYear = options.get(0).getText();
		int resettlementYear = Integer.parseInt(resettlementMonthAndYear.split("-")[0]);
		int resettlementMonth = Integer.parseInt(resettlementMonthAndYear.split("-")[1]);
		if (fAndFMonth == 12) {
			if (resettlementYear == fAndFYear + 1 && resettlementMonth == 01) {
				testCaseLogger.get().pass("Resettlement month is showing after the F And F Month");
			} else {
				testCaseLogger.get().fail("Resettlement month is showing before or equal to the F And F Month");
			}
		} else {
			if (resettlementMonth == fAndFMonth + 1) {
				testCaseLogger.get().pass("Resettlement month is showing after the F And F Month");
			} else {
				testCaseLogger.get().fail("Resettlement month is showing before or equal to the F And F Month");
			}
		}
		for (WebElement option : options) {
			int currentYear = Integer.parseInt(getSystemDate("yyyy"));
			if (option.getText().equalsIgnoreCase((currentYear + 1) + "-04")) {
				fYear = false;
				break;
			} else {
				fYear = true;
			}
		}
		if (fYear) {
			testCaseLogger.get().pass("Resettlement Restricted for Next Financial Year");
		} else {
			testCaseLogger.get().fail("Resettlement is not Restricted for Next Financial Year");
		}

	}

	public void verifyFAndFEmployeeInResettlement(String empID, String yM) {
		boolean result = false;
		click(resttlementHeaderSubmenu, "Resettlement");
		waitForPageLoad();
		Select yearAndMonth = new Select(resettlementMonthAndYearDrpDwn);
		waitForPageLoad(90);
		yearAndMonth.selectByVisibleText(yM);
		waitForPageLoad(90);
		List<WebElement> tableRows = employeeResettlementDataTable.findElement(By.tagName("tbody"))
				.findElements(By.tagName("tr"));
		if (tableRows.size() > 0) {
			for (WebElement row : tableRows) {
				if (!(row.getText().contains("No Resettlement Employees"))) {
					List<WebElement> empIDColumns = findElements(By.xpath("//*[@id = 'example']//td[2]"));
					for (WebElement column : empIDColumns) {
						if (column.getText().equalsIgnoreCase(empID)) {
							result = false;
							testCaseLogger.get().fail(
									"F and F not proceeded Employee / Already Resettlement processed Employee is displaying in the Resettlement");
							break;
						} else {
							result = true;
						}
					}
				} else {
					testCaseLogger.get().pass(
							"F and F not proceeded Employee / Already Resettlement processed Employee is not displaying in the Resettlement");
				}

			}

		} else {
			testCaseLogger.get().pass(
					"F and F not proceeded Employee / Already Resettlement processed Employee is not displaying in the Resettlement");
		}
		if (result) {
			testCaseLogger.get().pass(
					"F and F not proceeded Employee / Already Resettlement processed Employee is not displaying in the Resettlement");
		}
	}

	public HashMap<String, String> getGetailsFromPreviewAndGeneratePayslipPage() {
		HashMap<String, String> empValues = new HashMap<String, String>();
		List<WebElement> previewTableHeads = resettlementPreviewTable.findElements(By.tagName("th"));
		List<WebElement> previewTableColumns = resettlementPreviewTable.findElement(By.tagName("tbody"))
				.findElement(By.tagName("tr")).findElements(By.tagName("td"));
		for (int i = 0; i < previewTableHeads.size(); i++) {
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("Total Resettlement Net Pay")) {
				empValues.put("empNetPay", previewTableColumns.get(i).getText());
			}
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("Gross Income")) {
				empValues.put("empGross", previewTableColumns.get(i).getText());
			}
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("Resettlement Month(YYYY-MM)")) {
				empValues.put("empResettlementMonth", previewTableColumns.get(i).getText());
			}
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("TDS")) {
				empValues.put("empTDS", previewTableColumns.get(i).getText());
			}
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("EXTRA PAYMENTS")) {
				empValues.put("empExtraPayment", previewTableColumns.get(i).getText());
			}
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("REIMBURSEMENTS")) {
				empValues.put("empReiembursement", previewTableColumns.get(i).getText());
			}
			if (previewTableHeads.get(i).getText().trim().equalsIgnoreCase("EMPLOYEE NUMBER")) {
				empValues.put("empEmpID", previewTableColumns.get(i).getText());
			}

		}
		return empValues;
	}

	public void clickOnCompanyProfileImage() {
		scrollIntoView(companyProfleImage, viewportX);
		focus(companyProfleImage, "Company Profile Image");
		click(companyProfleImage, "Company Profile Image");
		waitForPageLoad();
	}

	public boolean getExtraSettingsCalculateResettlementOnBasicIsEnabled() {
		boolean result = false;
		scrollIntoView(calculateResettlementOnBasic, viewportX);
		focus(calculateResettlementOnBasic, "Resettlement Calculation On Basic");
		if (isCheckBoxChecked(calculateResettlementOnBasic)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public boolean getExtraSettingsCalculateResettlementOnGrossIsEnabled() {
		boolean result = false;
		scrollIntoView(extraSettingsRecoveryGross, "Recoverydays Calculation On Gross");
		focus(extraSettingsRecoveryGross, "Recoverydays Calculation On Gross");
		if (isCheckBoxChecked(extraSettingsRecoveryGross)) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public void enableCalculateResettlementOnBasic() {
		waitForPageLoad();
		if (calculateResettlementOnBasicList.size() > 0) {
			scrollIntoView(calLeOnNumOfDays, "");
			focus(calLeOnNumOfDays, "Calculate Leave Encashment On Number of Days");
			clear(calLeOnNumOfDays, "Calculate Leave Encashment On Number of Days");
			if (!getExtraSettingsCalculateResettlementOnBasicIsEnabled()) {
				scrollIntoView(calculateResettlementOnBasic, viewportX);
				focus(calculateResettlementOnBasic, "Resettlement Calculation On Basic");
				click(calculateResettlementOnBasic, "Resettlement Calculation On Basic");
				testCaseLogger.get().info("Resettlement Calculation On Basic is enabled");
			} else
				testCaseLogger.get().info("Resettlement Calculation On Basic is enabled");
		}

	}

	public void enableCalculateRecoveryOnGross() {
		waitForPageLoad();
		if (!getExtraSettingsCalculateResettlementOnGrossIsEnabled()) {
			scrollIntoView(extraSettingsEffectiveDate, viewportX);
			focus(extraSettingsEffectiveDate, "Recoverydays Calculation On Gross ");
			implicitWaitSec(2000);
			click(extraSettingsRecoveryGross, "Recoverydays Calculation On Gross");
			implicitWaitSec(1000);
			testCaseLogger.get().info("Recoverydays Calculation On Gross is enabled");
		} else
			testCaseLogger.get().info("Recoverydays Calculation On Gross is enabled");

	}

	public void disableCalculateResettlementOnBasic() {
		waitForPageLoad();
		if (calculateResettlementOnBasicList.size() > 0) {
			scrollIntoView(calLeOnNumOfDays, "");
			focus(calLeOnNumOfDays, "Calculate Leave Encashment On Number of Days");
			clear(calLeOnNumOfDays, "Calculate Leave Encashment On Number of Days");
			if (getExtraSettingsCalculateResettlementOnBasicIsEnabled()) {
				scrollIntoView(calculateResettlementOnBasic, viewportX);
				focus(calculateResettlementOnBasic, "Resettlement Calculation On Basic");
				click(calculateResettlementOnBasic, "Resettlement Calculation On Basic");
				testCaseLogger.get().info("Resettlement Calculation On Basic is disabled");
			} else
				testCaseLogger.get().info("Resettlement Calculation On Basic is disabled");
		}

	}

	public void unselectExtraSettingsGSTonRecoveryDays() {
		if (isCheckBoxChecked(extraSettingsGSTRecovery)) {
			focus(extraSettingsGSTRecovery, "GST On Recovery Days");
			implicitWaitSec(2000);
			jsClick(extraSettingsGSTRecovery, "GST On Recovery Days");
			testCaseLogger.get().info("Unselected GST on Recovery Days Successfully");
		} else {
			testCaseLogger.get().pass("GST On Recovery Days is not Selected");
		}
	}

	public void selectExtraSettingsGSTonRecoveryDays() {
		if (!isCheckBoxChecked(extraSettingsGSTRecovery)) {
			focus(extraSettingsGSTRecovery, "GST On Recovery Days");
			implicitWaitSec(2000);
			click(extraSettingsGSTRecovery, "GST On Recovery Days");
			testCaseLogger.get().info("Unselected GST on Recovery Days Successfully");
		} else {
			testCaseLogger.get().pass("GST On Recovery Days is not Selected");
		}
	}

	public void clickOnExtraSettingsSave() {
		focus(extraSettingsSaveButton, "Extra Setting Save button");
		implicitWaitSec(2000);
		click(extraSettingsSaveButton, "Extra Setting Save button");
		implicitWaitSec(2000);
	}

	public void leaveEncashmentSettings() {
		enableCalculateResettlementOnBasic();
		clickOnExtraSettingsSave();
		click(settingsPayRollSubMenu, "");
		clickOnCompanyProfileImage();
	}

	public String getBasicSalaryOfAnEmployee() {
		return ctcProrationMonthlyAmmount.get(0).getText();
	}

	public String getGrossSalaryOfAnEmployee() {
		return ctcProrationMonthlyAmmount.get(ctcProrationMonthlyAmmount.size() - 1).getText();
	}

	public void resettlementInputs(Map<String, String> data) {
		clear(lopInput, "Clear Lop Days");
		sendKeys(lopInput, data.get("LopDays"), "Lop Days");

		clear(arrearInput, "Clear Arrear Days");
		sendKeys(arrearInput, data.get("ArrearDays"), "Arrear Days");

		clear(leaveEncashmentInput, "Clear");
		sendKeys(leaveEncashmentInput, data.get("LEDays"), "Leave Encashment");

		clear(recoveryInput, "Clear");
		sendKeys(recoveryInput, data.get("RecoveryDays"), "Recovery Days");

	}

	public HashMap<String, String> getCalculationDetails(Map<String, String> data) {
		HashMap<String, String> calValues = new HashMap<String, String>();
		selectResettlementEmployeeBasedOnEmpID("Main181", "2021-07");
		NavigateToCBPage();
		switchToNewWindow();
		implicitWaitSec(2000);
		click(ctcProration, "ctc");
		String basicSalary = getBasicSalaryOfAnEmployee();
		String grossSalary = getGrossSalaryOfAnEmployee();
		System.out.println("Gross Salary = " + grossSalary);
		calValues.put("empBasicSalary", basicSalary);
		calValues.put("empMonthSalary", grossSalary);
		HashMap<String, String> taxSheetValues = getTaxtSheetData(data);
		calValues.put("TaxSheetNetTaxableIncome", taxSheetValues.get("NetTaxableIncome"));
		calValues.put("TaxSheetTaxDeductedTillDate", taxSheetValues.get("TaxDeductedTillDate"));
		switchToDefault();
		click(resettlementRun, "Resettlemet Run");
		resettlementInputs(data);
		click(nextButtonResettlementInputs, "next");
		waitForPageLoad();
		implicitWaitSec(7000);
		HashMap<String, String> empValues = getGetailsFromPreviewAndGeneratePayslipPage();
		YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(empValues.get("empResettlementMonth").split("-")[0]),
				Integer.parseInt(empValues.get("empResettlementMonth").split("-")[1]));
		int daysInMonth = yearMonthObject.lengthOfMonth();
		calValues.put("totalDays", String.valueOf(daysInMonth));
		calValues.put("empGross", empValues.get("empGross"));
		calValues.put("empTDS", empValues.get("empTDS"));
		return calValues;
	}

	public void verifyLeaveEncashement(Map<String, String> data) {
		HashMap<String, String> calValues = getCalculationDetails(data);
		int totalDays = Integer.parseInt(calValues.get("totalDays"));
		Double basic = Double.parseDouble(calValues.get("empBasicSalary").replace(",", ""));
		Double gross = Double.parseDouble(calValues.get("empGross").replace(",", ""));
		Double grossSalary = Double.parseDouble(calValues.get("empMonthSalary").replace(",", ""));
		Double leDays = Double.parseDouble(data.get("LEDays"));
		/*
		 * System.out.println(totalDays); System.out.println(basic);
		 * System.out.println(gross); System.out.println(grossSalary);
		 */
		if (gross == Math.round((basic / totalDays) * leDays)
				|| gross == Math.round((grossSalary / totalDays) * leDays)) {
			testCaseLogger.get().pass("Based On Salary Structure Leave Encashment Days are calculating");

		} else {
			testCaseLogger.get().fail("Based On Salary Structure Leave Encashment Days are calculating");
		}

	}

	public void selectResettlementEmployeeBasedOnEmpID(String empID, String yM) {
		Select yearAndMonth = new Select(resettlementMonthAndYearDrpDwn);
		waitForPageLoad(90);
		yearAndMonth.selectByVisibleText(yM);
		waitForPageLoad(90);
		List<WebElement> tableRows = employeeResettlementDataTable.findElement(By.tagName("tbody"))
				.findElements(By.tagName("tr"));
		if (tableRows.size() > 0) {
			for (WebElement row : tableRows) {
				if (!(row.getText().contains("No Resettlement Employees"))) {
					List<WebElement> empIDColumns = findElements(By.xpath("//*[@id = 'example']//td[2]"));
					int i = 1;
					for (WebElement column : empIDColumns) {
						if (column.getText().equalsIgnoreCase(empID)) {
							click(findElement(
									By.xpath("//*[@id='example']//tbody//tr[" + i + "]//td//*[@type='checkbox']")),
									"employee selection");
							testCaseLogger.get().info("Employee found");
							break;
						} else {
							testCaseLogger.get().info("Employee not found");
						}
						i++;
					}
				} else {
					testCaseLogger.get().info("Employee not found");
				}
			}

		}
	}

	public HashMap<String, String> getTaxtSheetData(Map<String, String> data) {
		MenuNavigationPage mPage = new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToSubMenu(data.get("TaxSheet"));
		refreshPage();
		waitForPageLoad();
		implicitWaitSec(5000);
		mPage.navigateToSubMenu(data.get("TaxSheet"));
		implicitWaitSec(1000);
		HashMap<String, String> taxSheetValues = new HashMap<String, String>();
		List<WebElement> tableRows = taxsheetTable.findElements(By.tagName("tr"));

		for (WebElement tRow : tableRows) {
			List<WebElement> cells = tRow.findElements(By.tagName("td"));
			if (cells.get(0).getText().equalsIgnoreCase("Net Taxable Income")) {
				taxSheetValues.put("NetTaxableIncome", cells.get(cells.size() - 1).getText().trim());
			}

			if (cells.get(0).getText().contains("Tax Deducted till Date")) {
				taxSheetValues.put("TaxDeductedTillDate", cells.get(cells.size() - 1).getText().trim());
			}

			if (cells.get(0).getText().contains("Resettlement Gross")) {
				taxSheetValues.put("ResettlementGross", cells.get(cells.size() - 1).getText().trim());
			}
			if (cells.get(0).getText().contains("Resettlement Reimbursements")) {
				taxSheetValues.put("ResettlementReimbursement", cells.get(3).getText().trim());
			}
			if (cells.get(0).getText().equalsIgnoreCase("Total Gross Salary")) {
				taxSheetValues.put("TotalGrossSalary", cells.get(cells.size() - 1).getText().trim());
			}
			if (cells.get(0).getText().equalsIgnoreCase("Gross Income after Deduction and Reimbursements")) {
				taxSheetValues.put("TotalGrossAftrReiembursement", cells.get(cells.size() - 1).getText().trim());
			}
			if (cells.get(0).getText().equalsIgnoreCase("Gross Salary")) {
				taxSheetValues.put("GrossSalary", cells.get(cells.size() - 1).getText().trim());
			}
			if (cells.get(0).getText().equalsIgnoreCase("Net Tax Payable (A)")) {
				taxSheetValues.put("NetPaybleTax", cells.get(cells.size() - 1).getText().trim());
			}

		}
		return taxSheetValues;

	}

	public int taxCalculation(int taxableIncome) {
		int tax = 0;
		if (taxableIncome >= 250000) {
			taxableIncome = taxableIncome - 250000;
			if ((taxableIncome > 250000)) {
				tax += (250000 * 0.05);
				taxableIncome = taxableIncome - 250000;
				if (taxableIncome > 500000) {
					tax += (500000 * 0.20);
					taxableIncome = taxableIncome - 500000;
					if (taxableIncome != 0) {
						tax += (taxableIncome * 0.30);
					}
				} else {
					tax += (taxableIncome * 0.20);
				}
			} else {
				tax += (taxableIncome * 0.05);

			}
		}

		tax += tax * 0.04;
		return tax;
	}

	public void verifyLeaveEncashmentTaxException(Map<String, String> data) {
		HashMap<String, String> calValues = getCalculationDetails(data);
		int tds = Integer.parseInt(calValues.get("empTDS").replace(",", ""));
		int gross = Integer.parseInt(calValues.get("empGross").replace(",", ""));
		int taxableIncome = Integer.parseInt(calValues.get("TaxSheetNetTaxableIncome").replace(",", ""));
		int taxDeductedTittDate = Integer.parseInt(calValues.get("TaxSheetTaxDeductedTillDate").replace(",", ""));
		int finalTax = taxCalculation(taxableIncome + gross - 300000);
		/*
		 * System.out.println("tds  ::"+tds);System.out.println("finalTax  ::"+finalTax)
		 * ;System.out.println("taxDeductedTittDate  ::"+taxDeductedTittDate);
		 */
		if (tds == finalTax - taxDeductedTittDate) {
			System.out.println("Leave Encashment Exception and Tax  calculations are working as excpeted");
		} else {
			System.out.println("Leave Encashment Exception and  Tax  calculations are not working as excpeted");
		}

	}

	public void verifyImportAndExport() {
		click(resettlementRun, "Resettlement Run");
		waitForPageLoad();
		if (isElementPresentAndDisplay(exportButton)) {
			testCaseLogger.get().pass("Export button is available at the top of the page");
		} else {
			testCaseLogger.get().fail("Export button is not available");
		}
		click(exportButton, "Export Button");
		implicitWaitSec(5000);
		String filePath = System.getProperty("user.dir") + "/Download/ExportResettlementData.csv";
		List<String[]> csvValues = new CSVFileReader().readCSVFile(filePath);

		if (csvValues.get(0)[9].contains("TDS")) {
			testCaseLogger.get().pass("Export file downloaded and TDS Column is showing");
		} else {
			testCaseLogger.get().fail("TDS Column is showing");
		}
		deleteCsvFile(filePath);
		click(importButton, "Import button");
		switchToNewWindow();
		String currentUrl = DriverManager.getDriver().getCurrentUrl();
		if (currentUrl.contains("https://payroll.qa.darwinbox.io/payroll/import")) {
			testCaseLogger.get().pass("Clicking on imports is redirecting to imports page");
		} else {
			testCaseLogger.get().fail("Clicking on imports is not redirecting to imports page");
		}
		switchToDefault();
	}

	public void verifyPreviewAndGeneratePayslipPage() {
		click(nextButtonResettlementInputs, "Next");
		implicitWaitSec(5000);
		List<WebElement> previewTableHeads = resettlementPreviewTable.findElements(By.tagName("th"));
		String[] expectedHeader = { "EMPLOYEE NUMBER", "EMPLOYEE NAME", "RESETTLEMENT MONTH(YYYY-MM)", "F&F MONTH",
				"DESIGNATION", "DEPARTMENT", "JOINING DATE", "QUIT DATE", "LOP DAYS", "ARREARS DAYS",
				"LEAVE ENCASHMENT", "RECOVERY DAYS", "EXTRA PAYMENTS", "REIMBURSEMENTS", "GROSS INCOME", "TDS",
				"TOTAL RESETTLEMENT NET PAY" };
		List<String> expectedHeaders = Arrays.asList(expectedHeader);
		List<String> actualHeaders = new ArrayList<String>();
		for (int i = 0; i < previewTableHeads.size(); i++) {
			actualHeaders.add(i, previewTableHeads.get(i).getText());
		}
		if (expectedHeaders.containsAll(actualHeaders)) {
			testCaseLogger.get().pass("Preview And Generate Payslip Details are showing as expected");
		} else {
			testCaseLogger.get().fail("Preview And Generate Payslip Details are not showing as expected");
		}

		if (progressBarList.get(2).getAttribute("class").contains("active")) {
			testCaseLogger.get()
					.pass("ProgressBar is getting moved from Resettlement Inputs to Preview And Generate Payslip Page");
		} else {
			testCaseLogger.get().fail(
					"ProgressBar is not getting moved from Resettlement Inputs to Preview And Generate Payslip Page");
		}
		if (isElementEnabledAndDisplay(generateResettlementPayslip)) {
			testCaseLogger.get().pass("Generate payslip Button to Generated Payslip");
		} else {
			testCaseLogger.get().fail("Generate payslip Button is not showing");
		}
		if (isElementEnabledAndDisplay(previewAndGenerateSearch)) {
			testCaseLogger.get().pass("Search is showing in Generate payslip page");
		} else {
			testCaseLogger.get().fail("Search is not showing in Generate payslip page");
		}

	}	

	public void verifyNetDaysCalculation(Map<String, String> data) {
		HashMap<String, String> calValues = getCalculationDetails(data);
		int netDays = Integer.parseInt(data.get("LEDays")) + Integer.parseInt(data.get("ArrearDays"))
				- Integer.parseInt(data.get("LopDays")) - Integer.parseInt(data.get("RecoveryDays"));
		Double basic = Double.parseDouble(calValues.get("empBasicSalary").replace(",", ""));
		Double gross = Double.parseDouble(calValues.get("empGross").replace(",", ""));
		int totalDays = Integer.parseInt(calValues.get("totalDays"));
		Double grossSalary = Double.parseDouble(calValues.get("empMonthSalary").replace(",", ""));
		/*
		 * System.out.println("totalDays :"+totalDays);
		 * System.out.println("basic :"+basic); System.out.println("gross :"+gross);
		 * System.out.println("grossSalary :"+grossSalary);
		 * System.out.println("netDays :"+netDays);
		 */
		if (gross == Math.round((basic / totalDays) * netDays)
				|| gross == Math.round((grossSalary / totalDays) * netDays)) {
			testCaseLogger.get().pass("Based On Salary Structure Net Days Days are calculating");
		} else {
			testCaseLogger.get().fail("Based On Salary Structure Net Days Days are calculating");
		}

	}

	public void netDaysSettings() {
		unselectExtraSettingsGSTonRecoveryDays();
		enableCalculateResettlementOnBasic();
		focus(recoveryDenominator, "Recovery Denominator");
		selectListBox(recoveryDenominator, "Select Days", "ByVisibleText");
		clickOnExtraSettingsSave();
		click(settingsPayRollSubMenu, "");
		clickOnCompanyProfileImage();
	}

	public void recoveryDaysSettings(Map<String, String> data) {
		if (!(data.get("GST").equalsIgnoreCase("True")) || data.get("GST") == null) {
			unselectExtraSettingsGSTonRecoveryDays();
		} else if (data.get("GST").equalsIgnoreCase("True")) {
			selectExtraSettingsGSTonRecoveryDays();
		}
		enableCalculateResettlementOnBasic();
		if (data.get("CalculationOn").equalsIgnoreCase("MonthlyGross")) {
			enableCalculateRecoveryOnGross();
			disableCalculateResettlementOnBasic();

		}
		focus(recoveryDenominator, "Recovery Denominator");
		selectListBox(recoveryDenominator, "Select Days", "ByVisibleText");
		clickOnExtraSettingsSave();
		click(settingsPayRollSubMenu, "");
		clickOnCompanyProfileImage();
	}

	public void verifyRecoveryDaysCalculation(Map<String, String> data) {
		HashMap<String, String> calValues = getCalculationDetails(data);
		Double basic = Double.parseDouble(calValues.get("empBasicSalary").replace(",", ""));
		Double gross = Double.parseDouble(calValues.get("empGross").replace(",", "").replace("-", ""));
		int totalDays = Integer.parseInt(calValues.get("totalDays"));
		Double grossSalary = Double.parseDouble(calValues.get("empMonthSalary").replace(",", ""));
		int recoveryDays = Integer.parseInt(data.get("RecoveryDays"));
		/*
		 * System.out.println("totalDays :"+totalDays);
		 * System.out.println("basic :"+basic); System.out.println("gross :"+gross);
		 * System.out.println("grossSalary :"+grossSalary);
		 * System.out.println("recoveryDays :"+recoveryDays);
		 */

		if (data.get("GST").equalsIgnoreCase("True") && data.get("CalculationOn").equalsIgnoreCase("Basic")) {
			if (gross == Math.round((basic / totalDays) * recoveryDays * 1.18)
					|| gross == Math.round((grossSalary / totalDays) * recoveryDays * 1.18)) {
				testCaseLogger.get().pass("Based On Salary Structure Recovery Days with GST Calculations are working");
			} else {
				testCaseLogger.get()
						.fail("Based On Salary Structure Recovery Days with GST Calculations are not working");
			}
		} else if (data.get("GST").equalsIgnoreCase("false") && data.get("CalculationOn").equalsIgnoreCase("Basic")) {
			if (gross == Math.round((basic / totalDays) * recoveryDays)
					|| gross == Math.round((grossSalary / totalDays) * recoveryDays)) {
				testCaseLogger.get()
						.pass("Based On Salary Structure Recovery Days without GST Calculations are working");
			} else {
				testCaseLogger.get()
						.fail("Based On Salary Structure Recovery Days without GST Calculations not are workingg");
			}

		}
		if (data.get("GST").equalsIgnoreCase("True") && data.get("CalculationOn").equalsIgnoreCase("MonthlyGross")) {
			if (gross == Math.round((basic / totalDays) * recoveryDays * 1.18)
					|| gross == Math.round((grossSalary / totalDays) * recoveryDays * 1.18)) {
				testCaseLogger.get().pass(
						"Based On Salary Structure Recovery Days with GST Monthly gross Calculations are working");
			} else {
				testCaseLogger.get().fail(
						"Based On Salary Structure Recovery Days with GST Monthly gross Calculations are not working");
			}
		} else if (data.get("GST").equalsIgnoreCase("false")
				&& data.get("CalculationOn").equalsIgnoreCase("MonthlyGross")) {
			if (gross == Math.round((basic / totalDays) * recoveryDays)
					|| gross == Math.round((grossSalary / totalDays) * recoveryDays)) {
				testCaseLogger.get().pass(
						"Based On Salary Structure Recovery Days without GST Monthly gross Calculations are working ");
			} else {
				testCaseLogger.get().fail(
						"Based On Salary Structure Recovery Days without GST Monthly gross Calculations not are workingg");
			}

		}
	}

	public void verifyResettlementInputs(Map<String, String> data) {
		click(resettlementRun, "Resettlement Run");
		resettlementInputs(data);
		if (data.get("LopDays") != null)
			testCaseLogger.get().pass("Able to Edit :" + data.get("LopDays"));
		else
			testCaseLogger.get().fail("Not Able to Edit :" + data.get("LopDays"));

		if (data.get("ArrearDays") != null)
			testCaseLogger.get().pass("Able to Edit :" + data.get("ArrearDays"));
		else
			testCaseLogger.get().fail("Not Able to Edit :" + data.get("ArrearDays"));

		if (data.get("LEDays") != null)
			testCaseLogger.get().pass("Able to Edit :" + data.get("LEDays"));
		else
			testCaseLogger.get().fail("Not Able to Edit :" + data.get("LEDays"));

		if (data.get("RecoveryDays") != null) {
			testCaseLogger.get().pass("Able to Edit :" + data.get("RecoveryDays"));
		} else {
			testCaseLogger.get().fail("Not Able to Edit :" + data.get("RecoveryDays"));
		}

		verifyAdditionalPayements(data);
	}

	public void verifyAdditionalPayements(Map<String, String> data) {
		click(additionalPayementsButton, "Additonal Payments Button");
		sendKeys(transactionName.get(0), data.get("TransNameFirst"), "Transaction Name");
		sendKeys(transactionAmount.get(0), data.get("TransAmountNegative"), "Transaction Amount");
		sendKeys(transactionAmount.get(0), data.get("TransAmountPositive"), "Transaction Amount");
		testCaseLogger.get().pass("Admin is able add Positive and Negative Extra Payments");
		click(addAnotherTransaction, "Add Another Transaction");
		if (transactionName.size() > 1) {
			testCaseLogger.get().pass("Transaction has been Added Successfully ");
		} else {
			testCaseLogger.get().fail("Transaction is not Added");
		}
		sendKeys(transactionName.get(1), data.get("TransNameSecond"), "Transaction Name");
		sendKeys(transactionAmount.get(1), data.get("TransAmountSecond"), "Transaction Amount");
		click(nonTaxable.get(0), "Non Taxable");

		click(transactionDelete.get(1), "Delete");
		if (transactionName.size() == 1) {
			testCaseLogger.get().pass("Transaction has been Deleted Successfully ");
		} else {
			testCaseLogger.get().fail("Transaction is not deleted");
		}

		clear(transactionName.get(0), "Name Cleared");
		clear(transactionAmount.get(0), "Amount Cleared");

		click(addPaymentsUpdateBtn, "Add Payments Update");
		implicitWaitSec(5000);
		testCaseLogger.get().pass("Able to Delete All added payments");
	}

	@FindBy(xpath = "//*[@class='close']")
	private List<WebElement> closeAddPaymentsDailogueBox;

	public void verifyTaxCalculations(Map<String, String> data) {
		try {
			String taxAmmonut;
			String nonTaxAmmount;
			NavigateToCBPage();
			switchToDefault();
			click(resettlementRun, "Resettlement Run");
			resettlementInputs(data);
			click(additionalPayementsButton, "Additonal Payments Button");
			sendKeys(transactionName.get(0), data.get("TransName"), "Transaction Name");
			sendKeys(transactionAmount.get(0), data.get("TransTaxAmount"), "Transaction Amount");
			click(addAnotherTransaction, "Add Another Transaction");
			sendKeys(transactionName.get(1), data.get("TransNameSecond"), "Transaction Name");
			sendKeys(transactionAmount.get(1), data.get("TransNonTaxAmount"), "Transaction Amount");
			click(nonTaxable.get(0), "Non Taxable");
			click(addPaymentsUpdateBtn, "Add Payments Update");
			implicitWaitSec(5000);
			focus(closeAddPaymentsDailogueBox.get(1), "");
			click(closeAddPaymentsDailogueBox.get(1), "close");
			click(nextButtonResettlementInputs, "Next");
			implicitWaitSec(5000);
			HashMap<String, String> empValues = getGetailsFromPreviewAndGeneratePayslipPage();
			taxAmmonut = empValues.get("empExtraPayment");
			nonTaxAmmount = empValues.get("empReiembursement");
			if (data.get("TransTaxAmount").equalsIgnoreCase(taxAmmonut)
					&& data.get("TransNonTaxAmount").equalsIgnoreCase(nonTaxAmmount)) {
				testCaseLogger.get().pass("Extra payments added Successfully");
			} else {
				testCaseLogger.get().fail("Extra payments are not Added");
			}
			click(generateResettlementPayslip, "Generate");
			implicitWaitSec(8000);
			switchToNewWindow();
			HashMap<String, String> taxSheetValues = getTaxtSheetData(data);
			/*
			 * System.out.println(taxSheetValues.get("ResettlementReimbursement"));
			 * System.out.println(data.get("TransNonTaxAmount"));
			 * System.out.println(Integer.parseInt(taxSheetValues.get(
			 * "TotalGrossAftrReiembursement")));
			 * System.err.println(taxSheetValues.get("TotalGrossSalary"));
			 * System.out.println(Integer.parseInt(taxSheetValues.get("TotalGrossSalary")));
			 * System.out.println(Integer.parseInt(taxSheetValues.get(
			 * "ResettlementReimbursement")));
			 */
			if (taxSheetValues.get("ResettlementReimbursement").equalsIgnoreCase(data.get("TransNonTaxAmount"))
					&& Integer.parseInt(taxSheetValues.get("TotalGrossAftrReiembursement")) == Integer
							.parseInt(taxSheetValues.get("TotalGrossSalary"))
							- Integer.parseInt(taxSheetValues.get("ResettlementReimbursement"))) {
				testCaseLogger.get().pass(
						"Non Taxable payments are displaying in resettlement reiembursement and excepted for tax calculations");
			} else {
				testCaseLogger.get().fail(
						"Non Taxable payments are not displaying in resettlement reiembursement and Not excepted for tax calculations");
			}
			implicitWaitSec(2000);
			/*
			 * taxable extra payment logic need to write here 1. get extra payment from tax
			 * sheet and compare with given value 2.verify that is included for tax
			 * calculations
			 */

			if (Integer.parseInt(taxSheetValues.get("ResettlementGross")) == Integer.parseInt(empValues.get("empGross"))
					&& Integer.parseInt(taxSheetValues.get("TotalGrossSalary")) == Integer
							.parseInt(taxSheetValues.get("GrossSalary"))
							+ Integer.parseInt(taxSheetValues.get("ResettlementGross"))) {
				testCaseLogger.get()
						.pass("Resettlement gross added in the Total Gross and Tax calculated on the resettlement");

			} else {
				testCaseLogger.get().fail("Resettlement gross is not added in the Total Gross");
			}

			int taxableIncome = Integer.parseInt(taxSheetValues.get("NetTaxableIncome").replace(",", ""));

			int finalTax = taxCalculation(taxableIncome);
			int netPaybleTax = Integer.parseInt(taxSheetValues.get("NetPaybleTax").replace(",", ""));
			if (netPaybleTax == finalTax || netPaybleTax == finalTax + 1) {
				testCaseLogger.get().pass("Tax calcutions are working fine");
			} else {
				System.out.println("Final Tax :"+finalTax);
				System.out.println("netPaybleTax :"+netPaybleTax);
				testCaseLogger.get().pass("Tax calcutions are not working as expected");
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	public void verifyForm12B() {
		implicitWaitSec(2000);
		findElement(By.xpath("//*[@href=\"#pay_package\"]")).click();
		implicitWaitSec(5000);
		selectDropDown(payPackageFYDrpDown, "2020", "value");
		if(isElementPresentAndDisplay(form12BMidExit) && isElementPresentAndDisplay(form12B) ) {
			testCaseLogger.get().pass("Two Form 12B are displaying");
		}else {
			testCaseLogger.get().pass("Two Form 12B are not displaying");
		}
	
	}
	
	public void verifyForm16AvailablityAndTaxCalculationsForDIfferentFY(Map<String, String> data) {
		NavigateToCBPage();
		switchToDefault();
		click(resettlementRun, "Resettlement Run");
		resettlementInputs(data);
		click(nextButtonResettlementInputs, "Next");
		implicitWaitSec(5000);
		HashMap<String, String> empValues = getGetailsFromPreviewAndGeneratePayslipPage();
		click(generateResettlementPayslip, "Generate");
		implicitWaitSec(5000);
		switchToNewWindow();
		implicitWaitSec(1000);
		click(paySlipsHeaderSubMenu, "payslips");
		selectDropDown(payslipsFYDrpDown, "2020-21", "ByVisibleText");
		if (isElementEnabledAndDisplay(form16)) {
			testCaseLogger.get().pass("Form 16 is showing for the Resettlement Financial Year");
		} else {
			testCaseLogger.get().pass("Form 16 is not showing for the Resettlement Financial Year");
		}
		selectDropDown(payslipsFYDrpDown, "2021-22", "ByVisibleText");
		if (isElementEnabledAndDisplay(form16)) {
			testCaseLogger.get().pass("Form 16 is showing for the Resettlement Financial Year");
		} else {
			testCaseLogger.get().pass("Form 16 is not showing for the Resettlement Financial Year");
		}
		HashMap<String, String> taxSheetValues = getTaxtSheetData(data);
		int taxableIncome = Integer.parseInt(taxSheetValues.get("NetTaxableIncome").replace(",", ""));
		int finalTax = taxCalculation(taxableIncome);
		int netPaybleTax = Integer.parseInt(taxSheetValues.get("NetPaybleTax").replace(",", ""));
		if (Integer.parseInt(taxSheetValues.get("ResettlementGross")) == Integer.parseInt(empValues.get("empGross"))) {
			testCaseLogger.get().pass("Resettlement Gross is displaying in the Tax sheet");
		} else {
			testCaseLogger.get().fail("Resettlement Gross is not displaying in the Tax sheet");
		}
		if (netPaybleTax == finalTax || netPaybleTax == finalTax + 1) {
			testCaseLogger.get().pass("Resettlement Financial Year Tax calcutions are working fine");
		} else {
			System.out.println("Final Tax :"+finalTax);
			System.out.println("netPaybleTax :"+netPaybleTax);
			testCaseLogger.get().fail("Resettlement Financial Year Tax calcutions are working as expected");
		}

	}
	
	public void verifyResettlementAlias() {
		focus(resettlementAlias, "Resettlement Alias");
		clear(resettlementAlias,"" );
		sendKeys(resettlementAlias, "ResettlementAutomation", "");
		focus(extraSettingsSaveButton, "Save");
		click(extraSettingsSaveButton, "Extra Settings Save");
		implicitWaitSec(5000);
		focus(resettlementAlias, "Resettlement Alias");
		if(resettlementAlias.getAttribute("value").equalsIgnoreCase("ResettlementAutomation")) {
			testCaseLogger.get().pass("Resettlement Alias is displaying");
		}else {
			testCaseLogger.get().fail("Resettlement Alias is not displaying ");
		}
		clear(resettlementAlias,"" );
		focus(extraSettingsSaveButton, "Save");
		click(extraSettingsSaveButton, "Extra Settings Save");
	}


}
