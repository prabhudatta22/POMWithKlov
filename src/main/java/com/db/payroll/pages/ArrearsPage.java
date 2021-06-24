/**
 * @author prabhudatta, Habeeba
 *
 */
package com.darwinbox.payroll.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.framework.setup.TestSetUp;
import com.framework.testutils.DriverManager;
import com.framework.ui.pageobjects.BasePage;

public class ArrearsPage extends BasePage {
	
	@FindBy(id="UserLogin_username")
	private WebElement empidinput;
	
	@FindBy(id="UserLogin_password")
	private WebElement passwordinput;
	
	@FindBy(id="login-submit")
	private WebElement submitBtn;
	
	@FindBy(css="div.col-md-4.text-right.mt-16.desktopDisplay > ul > li:nth-child(3) > div > div")
	private WebElement profileImage;
	
	@FindBy(css="div.col-md-4.text-right.mt-16.desktopDisplay > ul > li:nth-child(3) > div > ul > li.border-tb.mb-4 > a")
	private WebElement switchToAdminLink;
	
	@FindBy(xpath=".//*[@href='/payroll/setup/dashboard']")
	private List<WebElement> payRollTile;
	
	@FindBy(xpath=".//*[@href='/payroll/arrears/index']")
	private WebElement arrearsHeaderSubmenu;
	
	@FindBy(css="div.container-full.container-minheight.pt-68.clearfix > div > section > div > div > div.col-md-2 > div")
	private WebElement arrearsDashboardTitle;
	
	@FindBy(id="for_year")
	private WebElement fyearDropDwn;
	
	@FindBy(id="for_month")
	private WebElement fyMonthDrpDwn;
	
	@FindBy(id="dataTable-arrears")
	private WebElement arrearDataTable;
	
	@FindBy(id="dataTable-arrears_filter")
	private WebElement searchFilter;
	
	@FindBy(xpath="//*[@type='search']")
	private WebElement searchInput;
	
	@FindBy(id="parent_company_id")
	private WebElement companyDrpDwn;
	
	@FindBy(xpath = ".//*[@title='Export New Joinee Arrears Data']")
	private WebElement exportToCSVBtn;
	
	@FindBy(id="add_arrears")
	private WebElement addArrearsBtn;
	
	@FindBy(id="employee-search-tokenfield")
	private WebElement addArrearEmpID;
	
	@FindBy(id="ArrearsData_pay_month")
	private WebElement arrearPayMonthSelect;
	
	@FindBy(id="ArrearsData_arrear_month")
	private WebElement arrearMonthSelect;
	
	@FindBy(xpath="//*[@value='Select Some Options']")
	private WebElement arrearMonthSelectInc;
	
	@FindBy(id="ArrearsData_arrear_days")
	private WebElement arrearDaysInput;
	
	@FindBy(id="create_arrears_data_btn")
	private WebElement saveArrearBtn;
	
	@FindBy(id="delete_arrears")
	private List<WebElement> deleteArrearsBtn;
	
	@FindBy(xpath="//*[@id='dataTable-arrears']//tbody//*[@class='db-checkbox']")
    private List<WebElement> arrearsDashboardCheckBoxList;
   
    @FindBy(id="process_arrears")
    private WebElement processArrear;
    
    @FindBy(xpath="//*[@class='title-1']")
    private WebElement processDashboardTitle;
    
    @FindBy(xpath="//*[@title='Arrears Processed Dashboard']")
    private WebElement processDashboardButton;
 
    @FindBy(xpath="//*[@class='db-Edit fill-circle']")
    private List<WebElement> editIcon;
    
    @FindBy(xpath="//*[@class='info_message_text message_area']")
    private WebElement successMessage;
  
    @FindBy(xpath="//*[@class='clearfix alert alert-danger block-message error']")
    private WebElement errorMessage;
    
    @FindBy(xpath="//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content global-search-autocomplete']/li")
    private List<WebElement> nameSuggestions;
   
    @FindBy(xpath="(//*[@aria-hidden='true'])[5]")
    private WebElement closeButton;	
   
    @FindBy(id="employee-search_dashboard")
    private WebElement searchBar;	
   
    @FindBy(xpath = ".//*[@href='#generate-salary-slip-form']")
	private WebElement generateSalarySlipBtn;

	@FindBy(xpath = ".//select[@name='SalaryProcessForm[for_month]']")
	private List<WebElement> generatePaySlipMpnthDrpDwn;
   
	@FindBy(id = "generate-salary-slip")
	private WebElement processPaySlipBtn;
	
	@FindBy(id = "dataTable_processed_arrears")
	private WebElement processTable;
	
	@FindBy(id = "status")
	private WebElement statusDd;
	
	@FindBy(xpath = "//*[@class=\"alert alert-info clearfix\"]")
	private WebElement emptyTitle;
	
	@FindBy(xpath = "//*[@id='dataTable_processed_arrears']/thead/tr/th[13]")
	private WebElement errorTd;
	
	@FindBy(xpath = "//*[@id='dataTable_processed_arrears']/tbody/tr/td[13]")
	private WebElement errorTdMessage;
	
	@FindBy(xpath = "//*[@class='db-checkbox']")
	private List<WebElement> checkBoxtd;
	
	@FindBy(xpath = "//*[@id='ArrearsData_arrear_type']")
	private WebElement arrearType;
	
	@FindBy(xpath = "//*[@id='ArrearsData_has_pf_arrear']")
	private WebElement pfArrear;
	
	@FindBy(xpath = "//*[@id='ArrearsData_has_esic_arrear']")
	private WebElement esicArrear;
	
	@FindBy(xpath = "(//*[@class='table db-table table-hover'])[1]")
	private WebElement payslipTable;
	
	@FindBy(xpath = "//td//*[contains(text(),'RELEASE PAYSLIP')]")
	private WebElement releaseTable;
	
	@FindBy(xpath = "//*[@id='dataTable_processed_arrears']/tbody/tr/td[10]")
	private WebElement completeStatus;
	
	@FindBy(xpath = "//*[@id=\"salary-slip-table\"]//*[@class=\"db-Delete\"]")
	private WebElement deletePayslip;
	
	@FindBy(xpath = "//a[contains(text(),'Company Salary Structures')]")
	private WebElement companySalaryStructuresSubMenu;
	
	@FindBy(xpath = "//input[@aria-controls='business_unit_table']")
	private WebElement searchSalaryStructureTextbox;
	
	@FindBy(xpath = "//i[@title='Overwrite']")
	private WebElement salaryStructureEditIcon;
	
	@FindBy(xpath = "(//*[@class='base-2 pos-relative posi-t-6 mb-0 inline-block'])[2]")
	private WebElement basicSal;
	
	@FindBy(xpath = "//*[@id='general-2']/form/div[6]/input")
	private WebElement arrearCheckBoxBasic;
	
	@FindBy(xpath = "//a[@href='#calculation-2']")
	private WebElement calTabBasic;
	
	@FindBy(xpath = "//a[@href='#calculation-7']")
	private WebElement calTabPF;
	
	@FindBy(xpath = "//*[@id='calculation-2']/div[1]/form/div[2]/div[3]/input")
	private WebElement basiccValue;
	
	@FindBy(xpath = "(//*[@class='base-2 pos-relative posi-t-6 mb-0 inline-block'])[7]")
	private WebElement pf;
	
	@FindBy(xpath = "//*[@id'item-1']/div[1]/div/div[1]")
	private WebElement medicalAllowance;
	
	@FindBy(xpath = "//*[@id='general-7']/form/div[6]/input")
	private WebElement arrearCheckboxPF;
	
	@FindBy(xpath = "//*[@id='calculation-7']/div[1]/form/div[2]/div[3]/input")
	private WebElement PFcValue;

	@FindBy(id = "btn_edit_salary_structure_save_auto")
	private WebElement salaryStructureSaveButton;
	
	
	@FindBy(xpath = "//*[@id='dataTable_processed_arrears']/tbody/tr/td[7]")
	private WebElement pfTd;
	
	@FindBy(xpath = "//*[@id='dataTable_processed_arrears']/tbody/tr/td[15]")
	private WebElement basicTd;
	
	@FindBy(xpath = "//*[@id='dataTable_processed_arrears']/tbody/tr/td[8]")
	private WebElement esicTd;
	
	@FindBy(xpath = "//*[@class='dbBenefits']")
	private WebElement benefitsTab;

	@FindBy(xpath = "(//*[@class='db-Accordion-Expand'])[1]")
	private WebElement expandIcon;
	
	@FindBy(xpath = "//*[@class='db-table-one']")
	private WebElement flexiTable;

	@FindBy(xpath = "(//*[@class='btn btn-primary db-btn ripple db-btn-sm'])[2]")
	private WebElement viewProfile;		
		
	@FindBy(xpath = "//*[@name='taxsheet_year']")
	private WebElement taxYear;	
	
	@FindBy(xpath="//*[@class='positionRelative']")
	private List<WebElement> horizontalTab;
	
	@FindBy(xpath="//*[@class='col-md-9 pr-0']")
	private WebElement tabtitle;

	@FindBy(xpath="(//input[@class='search'])[1]")
	private WebElement companyDd;
	
	@FindBy(xpath="//*[@class='item selected']")
	private WebElement compNameSuggestion;
	
	@FindBy(xpath="//*[@id='sel_main_roster_chosen']/a/span")
	protected WebElement selectCategory;
	
	@FindBy(xpath="//*[@id='sel_main_roster_chosen']/div/div/input")
	protected WebElement enterCategory;
	
	@FindBy(xpath="//*[@id='sel_payroll_roster_chosen']/a/span")
	protected WebElement selectPayrollRoster;
	
	@FindBy(xpath="//*[@id='sel_payroll_roster_chosen']/div/div/input")
	protected WebElement enterRosterType;

	@FindBy(xpath="(//*[@name='opt_view_sel'])[2]")
	protected WebElement customRadioButton;
	
	@FindBy(id="fromDate")
	protected WebElement fromDate;
	
	@FindBy(id="toDate")
	protected WebElement toDate;
	
	@FindBy(id="filters_grp_company")
	protected WebElement selectEmp;
	
	@FindBy(id="employee-search-1-tokenfield")
	protected WebElement enterEmpName;
	
	@FindBy(id="btn_refresh")
	protected WebElement generateButton;
	
	@FindBy(id="ui-id-2")
	protected WebElement suggestedName;
	
	@FindBy(id="output")
	protected WebElement reporttable;
	
	@FindBy(xpath="//*[@id='taxSheetTable']/div[1]/div/div/div/table/tbody/tr[4]/td[3]")
	private WebElement hra;
	
	@FindBy(xpath="//*[@id=\"taxSheetTable\"]/div[1]/div/div/div/table/tbody/tr[35]/td[2]")
	private WebElement tdsTotal;
	
	@FindBy(xpath="//*[@id='taxSheetTable']/div[1]/div/div/div/table/tbody/tr[4]/td[4]")
	private WebElement hraGrid;
	
	@FindBy(xpath="//*[@id='taxSheetTable']/div[1]/div/div/div/table/tbody/tr[35]/td[3]")
	private WebElement tdsTotalGrid;

	@FindBy(xpath="//a[contains(text(),'Compensation')]")
	private WebElement compensation;
	
	@FindBy(id="date-of-conversion")
	private WebElement futurePaymentDate;

	@FindBy(xpath="//*[@id=\"taxSheetTable\"]/div[1]/div/div/div/table/tbody/tr[5]/td[2]")
	private WebElement totalEsicGross;
	
	@FindBy(xpath="//*[@id=\"taxSheetTable\"]/div[1]/div/div/div/table/tbody/tr[5]/td[3]")
	private WebElement totalEsicGrossGrid;
	
	@FindBy(xpath="//*[@id='sel_statutory_roster_chosen']/a/span")
	protected WebElement satutarySelect;
	
	@FindBy(xpath="//*[@id='sel_statutory_roster_chosen']/div/div/input")
	protected WebElement satutaryEntry;
	
	@FindBy(xpath="//*[@name='year']")
	protected WebElement satYear;
	
	@FindBy(xpath="//*[@name='month']")
	protected WebElement satMonth;
	
	@FindBy(xpath="//*[@id='admin_panel']")
	protected WebElement salaryRegisterTable;
	
	@FindBy(xpath="//*[@class='active-result']")
	private List<WebElement> incMonthList;
	
	@FindBy(xpath="//*[@id='dataTable-arrears']/tbody/tr")
	private List<WebElement> tbodyArrear;
	
	@FindBy(xpath="//select[@id='for_year']//option")
	private List<WebElement> yearList;
	
	private static boolean flag=false;
	private static String pfValue="";
	private static String basicValue="";
	
	private static String tdsValue="";
	private static String hraValue="";
	private static String esicGross="";
	
	public ArrearsPage getPayrollPage() {

		return (ArrearsPage) openPage(ArrearsPage.class);
	}

	
	public boolean doPayrollLogin(String username, String password)
	{
		
		boolean isLoggedIn = false;
		
		try {
			sendKeys(empidinput, username, username);
			sendKeys(passwordinput, password, password);
			click(submitBtn, "Submit");
			isLoggedIn= true;
			waitForPageLoad();
			implicitWaitSec(3000);
			
		}
		catch(Exception e) {
			testCaseLogger.get().fail("Couldn't login to the system due to "+e.getLocalizedMessage());
			Assert.assertFalse(true);
			isLoggedIn = false;
		}
		
		return isLoggedIn;
	}

	public void switchToAdmin() {

		waitForElementToBeDisplayed(profileImage, 180, "Profile Image");
		click(profileImage, "Profile Image");
		click(switchToAdminLink, "Switch to Admin");
		waitForPageLoad(90);
		implicitWaitSec(5000);
	}

	public void navigateToPayRoll(String submenu) {
		waitForElementToBeDisplayed(payRollTile.get(1), 30, "PayRoll Tile");
		scrollIntoView(payRollTile.get(1), "PayRoll Tile");
		waitForPageLoad(90);
		click(payRollTile.get(1), "Payroll module");
		waitForPageLoad(45);
		
		switch(submenu.toUpperCase())
		{
		
		case "ARREARS":
			jsClick(arrearsHeaderSubmenu, "Arrears Header Submenu");
			testCaseLogger.get().pass("Successfully navigated to Arrears sub menu");
			waitForPageLoad(45);
			isElementPresentAndDisplay(arrearsDashboardTitle);
			break;
			
		default:
			break;
		}
		
		
	}
	public void selectYear(String year)
	{
		for(WebElement ele:yearList)
		{
			String yearsList=ele.getText();
			if(yearsList.equals(year))
			{
				click(ele, year);
				break;
			}
		}
	}
	public void validateFYMonth(String fyear, String fymonth) {
		
		try {
			implicitWaitSec(5000);
			jsClick(fyearDropDwn, fyear);
			if(fyear.contains(".0"))
			{
			fyear.replace(".0", "");
			}
			selectYear(fyear);
		implicitWaitSec(2000);
		}
		catch(Exception e)
		{
			testCaseLogger.get().info("Issue in selecting value from dropdown");
		}
		selectDropDown(fyMonthDrpDwn, fymonth, "ByVisibleText");
		waitForPageLoad(50);
		
		isElementPresentAndDisplay(arrearDataTable);
		boolean isValid = getArrearDataVerified(fyear, fymonth);
		if(isValid)
		{
			testCaseLogger.get().pass("Employee Details are verified");
		}
		else {
			testCaseLogger.get().pass("Table is empty");
		}
		
	}

	private boolean getArrearDataVerified(String fyear, String fymonth) {
		
		boolean isValid = false;
		
		try {
			isElementPresentAndDisplay(arrearDataTable);
			List<WebElement> tableRows = arrearDataTable.findElements(By.tagName("tr"));
			for(WebElement row: tableRows)
			{
			
				List<WebElement> tableCells = row.findElements(By.tagName("td"));
				for(WebElement cell: tableCells)
				{
					if(cell.getText().contains(fymonth))
					{
						testCaseLogger.get().pass("Found the Arrear for the period "+fymonth);
						break;
					}
				}break;
			}
			isValid= true;
			
		}
		catch(Exception e) {
			isValid = false;
			testCaseLogger.get().fail("Couldn't able to Find the Arrear for the period "+fymonth);
		}
		
		return isValid;
		
	}

	public void searchEmployeeArrear(String employee) {

		jsClick(searchFilter, "Search Filter");
		implicitWaitSec(2000);
		sendKeys(searchInput, employee, "Searching for Employee ");
		isElementPresentAndDisplay(arrearDataTable);
		
	}

	public void verifyEmployeeCompanywise(Map<String, String> data) {

		selectListBox(companyDrpDwn, data.get("Company"), "Company selected");
		waitForPageLoad(45);
		boolean isPresent = isDataPresent(arrearDataTable, data.get("Employee"));
		
		selectListBox(companyDrpDwn, data.get("Company"), "Company selected");
		waitForPageLoad(45);
		boolean isVisible = isDataPresent(arrearDataTable,data.get("Employee"));
		
		if(isPresent && !isVisible)
		{
			testCaseLogger.get().pass("Employee data are unique across the companies");
		}
		else {
			testCaseLogger.get().pass("Employee data are unique across the companies");
		}
	}

	protected boolean isDataPresent(WebElement arrearDataTable2, String employee) {
		
		boolean isPresent = false;
		
		try {
			
			List<WebElement> tableRows = arrearDataTable2.findElements(By.tagName("tr"));
			
			for(WebElement row: tableRows)
			{
				if(row.getText().contains(employee))
				{
					isPresent = true;
					testCaseLogger.get().pass("There are employee data present in the table");
				}
			}
			
		}
		catch(Exception e) {
			isPresent = false;
			testCaseLogger.get().pass("Couldn't get the search table data ");
		}
		return isPresent;
	}
	
	protected boolean isDataPresentInTable(WebElement table, String toBeVerified, String colmnuHeader)

	{

		boolean isPresent = false;

		boolean isHeaderPresent = false;

		try {

			List<WebElement> tableRows = table.findElements(By.tagName("tr"));

			List<WebElement> tableHeaders = table.findElements(By.tagName("th"));

			int i = 0;

			System.out.println(tableRows.size());

			for (WebElement colHeader : tableHeaders)

			{

				if (colHeader.getText().equalsIgnoreCase(colmnuHeader))

				{
						System.out.println("Headervalue:  "+colHeader.getText());
					testCaseLogger.get().pass("Column header is present in the table");

					isHeaderPresent = true;

					break;

				}

				i++;

			}

			if (isHeaderPresent) {

				for (WebElement row : tableRows)

				{

					List<WebElement> tableData = row.findElements(By.tagName("td"));

					if (!tableData.isEmpty()) {

						if (tableData.get(i).getText().contains(toBeVerified))

						{
							System.out.println("Headervalue:  "+tableData.get(i).getText());
							testCaseLogger.get().pass("Data is present in the table");

							isPresent = true;

							break;

						}

						else {

							isPresent = false;

						}

					}

				}
			}

			else {

				testCaseLogger.get().fail("The exepcted Table header is not present in table");

			}

			if (isPresent)

			{

				testCaseLogger.get().pass("Data is present in the table");

			}

			else {

				testCaseLogger.get().fail("Data is not present in the table");

			}

		}

		catch (Exception e) {

			isPresent = false;

			testCaseLogger.get().info("Couldn't load the table properly " + e.getLocalizedMessage());

		}

		return isPresent;

	}

	public void verifyArrearStatus(Map<String, String> data) {

		isDataPresentInTable(arrearDataTable, data.get("ArrearStatus"), data.get("ColumnHeader"));
	}

	public void exportToCSV() {

		isElementEnabledAndDisplay(exportToCSVBtn);
		click(exportToCSVBtn, "Export to CSV");
		testCaseLogger.get().pass("Data exported successfully");
	}
	
	
	public void addArrear(Map<String, String> data)
	{
		
		isElementEnabledAndDisplay(addArrearsBtn);
		implicitWaitSec(3000);
		click(addArrearsBtn, "Add Arrears");
		isElementPresentAndDisplay(addArrearEmpID);
		jsSendKeys(addArrearEmpID, data.get("Employee"));
		implicitWaitSec(3000);
		enterKeys(addArrearEmpID, Keys.SPACE, "Emp Id Field");
		enterKeys(addArrearEmpID, Keys.SPACE, "Emp Id Field");
		implicitWaitSec(3000);
		for(WebElement name : nameSuggestions)
		{
			if(StringUtils.isNotEmpty(name.getText()) && name.getText().contains(data.get("Employee")))
			{
				jsClick(name, "Clicked on the given name suggestion");
			}
			if(!isElementPresentAndDisplay(name))
			{
				enterKeys(addArrearEmpID, Keys.SPACE, "Emp Id Field");
			}
			
		}
		
		click(arrearPayMonthSelect, " ArrearPayMonth");
		implicitWaitSec(3000);
		selectDropDown(arrearPayMonthSelect, data.get("ArrearPayMonth"), "ByVisibleText");
		
		selectDropDown(pfArrear, data.get("pfValue"), "ByVisibleText");

		selectDropDown(esicArrear, data.get("esicType"), "ByVisibleText");
		
		selectDropDown(arrearType, data.get("arrearType"), "ByVisibleText");
	
		if(data.get("arrearType").contains("Increment Arrear"))
		{
			String[] months=data.get("ArrearMonth").split(",");
			click(arrearMonthSelectInc, "Month field");
			for(String month:months)
			{
				System.out.println("Given month "+month);
				
				implicitWaitSec(2000);
				for(int i=0;i<incMonthList.size();i++)
				{
					click(arrearMonthSelectInc, "Month field");
					String value=incMonthList.get(i).getText();
					System.out.println(value);
					
					if(!StringUtils.isEmpty(value) && value.contains(month))
					{
						click(incMonthList.get(i),"Clicked on Month");
						break;
					}

				}
				
				}
		}
		else {
			
			click(arrearMonthSelect, "ArrearMonth");
			implicitWaitSec(3000);
			selectDropDown(arrearMonthSelect, data.get("ArrearMonth"), "ByVisibleText");
			clear(arrearDaysInput, "No of arrear days added");
			sendKeys(arrearDaysInput, data.get("ArrearDays"), "No of arrear days added");
			}
		click(saveArrearBtn, "Arrear Saved");
		implicitWaitSec(5000);
		
		
		
	}

	public void verifyExistingArrear()
	{
		try {
			if(errorMessage.getText().contains("already exists"))
			{
				testCaseLogger.get().pass("Given data is already exist in the system");
				jsClick(closeButton, "Close Button");
			}
			}
			catch(Exception e)
			{
				testCaseLogger.get().info("Data entered is unique");
				testCaseLogger.get().pass("Successfully added arrears to the employee ");
			}
			
			
	}
	public void deleteArrears(Map<String,String> data) {

		isElementEnabledAndDisplay(deleteArrearsBtn.get(0));
		jsClick(deleteArrearsBtn.get(0), "Deleted arrears");
		acceptAlert();
		testCaseLogger.get().pass("Deleted arrears successfully ");
	}

	public HashMap<String, String> getSelectedEmployeeIdAndArrearDays() {
        HashMap<String, String> empIdAndDays = new HashMap<String, String>();
        try {
            isElementPresentAndDisplay(arrearDataTable);
            List<WebElement> tableHead = arrearDataTable.findElements(By.tagName("th"));
            List<WebElement> tableColumn = arrearDataTable.findElements(By.tagName("td"));
            int i = 0;
            for (WebElement tHead : tableHead) {
                if (tHead.getText().equalsIgnoreCase("Arrear Days")) {
                    int j = 0;
                    for (WebElement tColumn : tableColumn) {
                        if (j == i) {
                            String arrearDays = tColumn.getText();
                            empIdAndDays.put("Arrear days", arrearDays);
                            break;
                        }
                        j++;
                    }
                }
                if (tHead.getText().equalsIgnoreCase("Employee ID")) {
                    int j = 0;
                    for (WebElement tColumn : tableColumn) {
                        if (j == i) {
                            String empID = tColumn.getText();
                            empIdAndDays.put("Employee Id", empID);
                            break;
                        }
                        j++;
                    }
                }
                i++;
            }

        } catch (Exception e) {
            testCaseLogger.get().info("No employee details are present");
        }
        return empIdAndDays;
    }
	
	 public void selectEmployeeArrearsDashboardCheckList(int empCount) {
		    int count = 0;
	        if (arrearsDashboardCheckBoxList.size() >= empCount) {
	            for (WebElement checkBox : arrearsDashboardCheckBoxList) {
	                click(checkBox, "Employee Selection");
	                implicitWaitSec(1000);
	                count++;
	                if (count == empCount) {
	                    break;
	                }
	            }

	        } else {
	            testCaseLogger.get().info("Number of entries are greater than employees present in the list");
	        }
	    }

	    public void clickOnProcessArrears() {
	        click(processArrear, "Process Arrear");
	    }

	    public void verifyProcessDashBoard()
	    {
	    	jsClick(processDashboardButton, "Arrears Process Dashboard Button");
	    	Set<String> set = DriverManager.getDriver().getWindowHandles();
			List<String> windows = new ArrayList<String>(set);
			switchHandle(windows.get(1));
			implicitWaitSec(5000);
			String actualTitle=processDashboardTitle.getText();
			Assert.assertEquals(actualTitle, "Arrears Processed Dashboard");
			testCaseLogger.get().pass("Admin able to view the Arrears Processed/ On-going processing Dashboard");
	    }

	    public void verifyEditArrear(Map<String, String> data)
	    {
	    	jsClick(editIcon.get(0), "Edit Button");
	    	implicitWaitSec(5000);
	    	clear(arrearDaysInput, "Arrear days added");
	    	sendKeys(arrearDaysInput, data.get("EditArrearDays"), "No of arrear days added");
	    	enterKeys(arrearDaysInput, Keys.ENTER, "Hit entered to save");
	    	implicitWaitSec(3000);
	    	String success=successMessage.getText();
	    	Assert.assertEquals(success, "Succesfully Saved the Arrears Data");
	    	testCaseLogger.get().pass("Admin able to edit the existing Arrears successfully and success message showns as: "+success);
	    }

	    public void restrictionPreGeneratedPayslip()
	    {
	    	clickOnProcessArrears();
	    	implicitWaitSec(5000);
	    
	    	if(completeStatus.getText().contains("Completed"))
			{
				testCaseLogger.get().pass("Arrear process is completed successfully");
				this.flag=true;
			}
	    	
	    	else if(errorTdMessage.getText().contains("already generated!"))
	    	{
	    		testCaseLogger.get().pass("Restriction for arrear process for pre-generated payslip is successfully executed");
	    	}
	    	else if(errorTdMessage.getText().contains("not generated yet!"))
			{
				testCaseLogger.get().pass("Payslip is not yet generated");
			}
			
			else if(errorTdMessage.getText().contains("is zero only!"))
			{
				testCaseLogger.get().pass("LOPs of arrear month is zero only! ");
			}
	    	else
	    	{
	    		testCaseLogger.get().info("Restriction for arrear process for pre-generated payslip is not executed");
	    	}
	  
	    }

	    public void generatePaySlip(String month,String monthExists)
	    {
	    	
    		if(isDataPresent(payslipTable, monthExists))
    		{
    			testCaseLogger.get().pass("Payslip is already generated");
    		}
    		else 
    		{
    			clickAction(generateSalarySlipBtn, "Generate Salary Slip Btn");
    			implicitWaitSec(5000);
				selectDropDown(generatePaySlipMpnthDrpDwn.get(0), month, "ByVisibleText");
				implicitWaitSec(3000);
				jsClick(processPaySlipBtn, "Process Pay Slip");
				testCaseLogger.get().pass("Successfully generated payslip");
				implicitWaitSec(5000);
    		}
	    }

	    public void selectStatus(String value)
	    {
	    	jsClick(statusDd, viewportX);
	    	selectDropDown(statusDd,value,"ByVisibleText");
	    }
	    
	    public void verifyEmptyTable(Map<String,String> data)
	    {
	    	
	    	
	    	if(arrearDataTable.getText().contains("Seems you don't have any arrears to process"))
	    		{
	        	selectStatus("Not Completed");
	        	testCaseLogger.get().pass("Switched to InComplete status");
	        	implicitWaitSec(5000);
		        	if(arrearDataTable.getText().contains("Seems you don't have any arrears to process"))
		        	{
		        		selectStatus("Completed");
		        		testCaseLogger.get().pass("Switched to Complete status");
		        	}
		        	implicitWaitSec(5000);
		        	if(arrearDataTable.getText().contains("Seems you don't have any arrears to process"))
		        	{
		        		testCaseLogger.get().pass("Arrear is hidden");
		        	}
		        		implicitWaitSec(5000);
			    		List<WebElement> tabledata=arrearDataTable.findElements(By.tagName("tr"));
			    		System.out.println("size: "+tabledata.size());
			    		for(int i=0;i<=tabledata.size();i++)
			    		{
			    			String value=tabledata.get(i).getText();
			    			System.out.println(value);
			    			System.out.println("Given ids: "+data.get("EmpId"));
			    			if(value.contains(data.get("EmpId")))
			    			{
			    				click(arrearsDashboardCheckBoxList.get(i), "checkbox");
			    				testCaseLogger.get().pass("Selected the user: "+data.get("EmpId"));
			    				break;
			    			}
			    			else
			    			{
			    				selectStatus("Completed");
			    				implicitWaitSec(5000);
			    				if(arrearDataTable.getText().contains("Seems you don't have any arrears to process"))
					        	{
					        		testCaseLogger.get().pass("Arrear is hidden");
					        	}
			    				else {
			    				click(arrearsDashboardCheckBoxList.get(i), "checkbox");
			    				testCaseLogger.get().pass("Selected the user: "+data.get("EmpId"));
			    				}
			    				break;
			    			}
		    			
		    		}
		    	}
	    		else {
	    			List<WebElement> tabledata=arrearDataTable.findElements(By.tagName("tr"));
		    		System.out.println("size: "+tabledata.size());
		    		for(int i=0;i<=tabledata.size();i++)
		    		{
		    			String value=tabledata.get(i).getText();
		    			System.out.println(value);
		    			System.out.println("Given ids: "+data.get("EmpId"));
		    			if(value.contains(data.get("EmpId")))
		    			{
		    				click(arrearsDashboardCheckBoxList.get(i), "checkbox");
		    				testCaseLogger.get().pass("Selected the user: "+data.get("EmpId"));
		    				break;
		    			}
		    			else
		    			{
		    				selectStatus("Completed");
		    				implicitWaitSec(5000);
		    				if(arrearDataTable.getText().contains("Seems you don't have any arrears to process"))
				        	{
				        		testCaseLogger.get().pass("Arrear is hidden");
				        	}
		    				else {
		    				click(arrearsDashboardCheckBoxList.get(i), "checkbox");
		    				testCaseLogger.get().pass("Selected the user: "+data.get("EmpId"));
		    				}
		    				break;
		    			}
		    			
		    		}
	        }
	       
	    }
	    
	    
	    
	    public void validateProcess(Map<String,String> data)
	    {
	    	if(successMessage.getText().contains("Please select atleast one employee to process arrears!"))
			{
				testCaseLogger.get().pass("Arrear is already processed");
			}
	    	else
	    	{
	    		completeArrear(data);
	    	}
	    	
	    }
	    
	    public void completeArrear(Map<String,String> data)
	    {
    			clickOnProcessArrears();
    			implicitWaitSec(5000);
    			if(completeStatus.getText().contains("Completed"))
    			{
    				testCaseLogger.get().pass("Arrear process is completed successfully");
    				this.flag=true;
    			}
    			else if (errorTdMessage.getText().contains("already generated!"))
    			{
    				testCaseLogger.get().pass("Payslip is already generated");
    			}
    			else if(errorTdMessage.getText().contains("not generated yet!"))
    			{
    				testCaseLogger.get().pass("Payslip is not yet generated");
    			}
    			
    			else if(errorTdMessage.getText().contains("is zero only!"))
    			{
    				testCaseLogger.get().pass("LOPs of arrear month is zero only! ");
    			}
    	}
	    	
	    
	    
	    public void releasePayslip(Map<String,String> data)
	    {
	    	
	    	List<WebElement> tabledata=payslipTable.findElements(By.tagName("tr"));
    		System.out.println("size: "+tabledata.size());
    		for(WebElement ele: tabledata)
    		{
    			if(ele.getText().contains(data.get("PaySlipMonthCurrent")))
    			{
    				if(ele.getText().contains("RELEASED"))
    				{
    					testCaseLogger.get().pass("Payslip released pre exists ");
    					flag=true;
    				}
    				else if (isElementEnabledAndDisplay(releaseTable))
    				{
    				click(releaseTable, "Click on release");
    				implicitWaitSec(3000);
    				acceptAlert();
    				implicitWaitSec(5000);
    				testCaseLogger.get().pass("Payslip released successfully ");
    				flag=true;
    				}
    			}
    			
    		}
	    }
	    
	    public void verifyRestriction(Map<String,String> data)
	    {
	    	if(flag)
	    	{
	    		selectStatus("Completed");
	    		implicitWaitSec(5000);
	    		if(isDataPresent(arrearDataTable,"Seems you don't have any arrears to process"))
	    		{
	    			testCaseLogger.get().pass("Arrear restrictions are verified successfully");
	    		}
	    	}
	    	else if(isDataPresent(arrearDataTable,data.get("EmpId")))
	    	{
	    		testCaseLogger.get().info("Arrear is still availble under completed state. Please check for payslip released");
	    	}
	    		
	    		
	    }
	    
	    public void deletePaySlip(Map<String,String> data)
	    {
	    	if(isDataPresent(payslipTable, data.get("PaySlipMonthCurrent")))
	    	{
	    		click(deletePayslip, "Delete Payslip");
	    		acceptAlert();
	    		implicitWaitSec(5000);
	    		testCaseLogger.get().pass("Deleted Payslip");
	    	}
	    }
	    
	    public void eidtSalaryStructure(Map<String,String> data)
	    {
	    	sendKeys(searchSalaryStructureTextbox, data.get("SalaryStructureName"), "searchSalaryStructureTextbox");
	    	implicitWaitSec(5000);
	    	click(salaryStructureEditIcon, "salaryStructureEditIcon");
	    	implicitWaitSec(5000);
	    	
	    	//Basic Sal
	    	jsClick(basicSal, "BASIC");
	    	implicitWaitSec(3000);
	    	
	    	if(isElementPresentAndDisplay(arrearCheckBoxBasic))
	    	{
	    	checkBox(arrearCheckBoxBasic, data);
	    	testCaseLogger.get().pass("Checkbox for BasicArrear");
	    	}
	    	
	    	jsClick(calTabBasic, "CalculationTab");
	    	implicitWaitSec(1000);
	    	
	    	long  bval=(long)Double.parseDouble(data.get("BasicVal"));
			String basic=String.valueOf(bval);
			clear(basiccValue, "Basic Value field");
			implicitWaitSec(1000);
	    	sendKeys(basiccValue,basic, "Basic Value");
	    	
	    	this.basicValue=basic;
	    	testCaseLogger.get().pass("Admin able to manipulate the value of basic "+this.basicValue);
	    	
	    	//PF
	    	jsClick(pf, "PF");
	    	implicitWaitSec(3000);
	    	if(isElementPresentAndDisplay(arrearCheckboxPF))
	    	{
	    	checkBox(arrearCheckboxPF, data);
	    	testCaseLogger.get().pass("Checkbox for PFsArrear");
	    	}
	    	
	    	jsClick(calTabPF, "CalculationTab");
	    	implicitWaitSec(1000);
	    	
	    	long  pfval=(long)Double.parseDouble(data.get("PFVal"));
			String pf=String.valueOf(pfval);
	    	clear(PFcValue, "PF Value Field");
	    	implicitWaitSec(1000);
	    	jsSendKeys(PFcValue, pf);
	    	implicitWaitSec(2000);
	    	this.pfValue=pf;
	    	testCaseLogger.get().pass("Admin able to manipulate the value of PF "+this.pfValue);
	    	
	    	jsClick(salaryStructureSaveButton, "salaryStructureSaveButton");
			implicitWaitSec(8000);
			
	    }
	    
	    
	    public void checkBox(WebElement element,Map<String,String> data)
	    {
	    	boolean cBoxAction = Boolean.parseBoolean(data.get("IsCheck").toLowerCase());
	    	if(cBoxAction)
			{
				if(!isCheckBoxChecked(element))
				{
					jsClick(element, "ArrearChecked");
					testCaseLogger.get().pass("Admin able to check the Arrear checkbox");
					
				}
				else {
					
					testCaseLogger.get().pass("Checkbox is pre-selected");
					 }
			}
	    	else
	    	{

				if(isCheckBoxChecked(element))
					{
					jsClick(element, "Arrear UnChecked");
					testCaseLogger.get().pass("Admin able to un-check the checkbox ");
					}
				else 
					{
					testCaseLogger.get().pass("Checkbox is already un-checked");
				    }
	    	}
	    	
	    }

	    public void computeArrear(Map<String,String> data)
	    {
	    	
	    	click(processArrear, "Process Arrear");
	    	
	    	implicitWaitSec(5000);
	    	if(completeStatus.getText().contains("Completed"))
			{
				testCaseLogger.get().pass("Arrear process is completed successfully");
				
				//basic
				long  bval=(long)Double.parseDouble(data.get("BasicVal"));
				String basicInput=String.valueOf(bval);
				int basicY= Integer.parseInt(basicInput);
				int basicM=basicY/12;
				String actualBasic=basicTd.getText();
				int actualBValue=Integer.parseInt(actualBasic);
				
				String aday=data.get("ArrearDays").replace(".0", "");
				System.out.println(aday+"Value of arrear day input");
				int arrearday=Integer.parseInt(aday);
				
				String arrearmonth=data.get("ArrearMonth");
				
				int resultbasic;
				if(monthsdays().contains(arrearmonth)) 
				{
					resultbasic=(basicM/31)*arrearday;
					System.out.println("Basic formula "+resultbasic);
				}
				else
				{
					resultbasic=(basicM/30)*arrearday;
					System.out.println("Basic formula "+resultbasic);
				}
				String basicV=Integer.toString(resultbasic);
				if(basicV.contains("-"))
				{
					int calBasic=Math.decrementExact(resultbasic);
					assertEquals(actualBValue, calBasic);
					testCaseLogger.get().pass("Basic is calculated as expected");
					System.out.println("Calculated basic value: "+calBasic);
					System.out.println("Actual basic value: "+actualBValue);
				}
				else
				{
					long calBasic=Math.round(resultbasic);
					assertEquals(actualBValue, calBasic);
					testCaseLogger.get().pass("Basic is calculated as expected");
					System.out.println("Calculated basic value: "+calBasic);
					System.out.println("Actual basic value: "+actualBValue);
				}
				//PF
				String pfFlag=pfTd.getText();
				assertTrue(pfFlag.contains(data.get("pfValue")));
				testCaseLogger.get().pass("Basic is calculated as expected");
				//ESIC
				String esicValue=esicTd.getText();
				assertTrue(esicValue.contains(data.get("esicType")));
				testCaseLogger.get().pass("Basic is calculated as expected");
				
				
			}
	    	
	    	
	    	else if (errorTdMessage.getText().contains("already generated!"))
			{
				testCaseLogger.get().pass("Payslip is already generated");
			}
			else if(errorTdMessage.getText().contains("not generated yet!"))
			{
				testCaseLogger.get().pass("Payslip is not yet generated");
			}
			
			else if(errorTdMessage.getText().contains("is zero only!"))
			{
				testCaseLogger.get().pass("LOPs of arrear month is zero only! ");
			}
	    	
	    }

	    public void verifyFlexiArrear()
	    {
	    	waitForElementToBeDisplayed(profileImage, 180, "Profile Image");
			click(profileImage, "Profile Image");
			
			waitForElementToBeDisplayed(viewProfile, 5, "Profile Image");
			click(viewProfile, "View Profile");
			
			waitForPageLoad(3000);
			
	    	if(isElementPresentAndDisplay(benefitsTab))
	    	{
	    	jsClick(benefitsTab, "BenefitsTab");
	    	implicitWaitSec(3000);
	    	jsClick(expandIcon, "Expand button");
	    	implicitWaitSec(3000);
	    	isDataPresent(flexiTable, "Arrear Days");
	    	implicitWaitSec(3000);
	    	testCaseLogger.get().pass("Arrears days for flexi/Benefits are verified");
	    	}
	    	else
	    	{
	    		testCaseLogger.get().pass("Benefits are not assigned to user");
	    	}
	    }
	    
	    public void taxSheetComputation(Map<String,String> data)
	    {
	    	String aday=data.get("ArrearDays").replace(".0", "");
			System.out.println(aday+"Value of arrear day input");
			int arrearday=Integer.parseInt(aday);
			WebElement arrearValue;
	    	for(int i=3;i<=12;i++)
	    	{
	    	WebElement comp=DriverManager.getDriver().findElement(By.xpath("//*[@id='taxSheetTable']/div[1]/div/div/div/table/tbody/tr["+i+"]/td[3]"));
	    	int j=i+1;
	    	if(TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("True"))
	    	{
	    		 arrearValue=DriverManager.getDriver().findElement(By.xpath("//*[@id='taxSheetTable']/div[1]/div/div/div/table/tbody/tr["+j+"]/td[4]"));
	    		 System.out.println(arrearValue);
	    	}
	    	else
	    	{
	    		 arrearValue=DriverManager.getDriver().findElement(By.xpath("//*[@id='taxSheetTable']/div[1]/div/div/div/table/tbody/tr["+j+"]/td[3]"));
	    		 System.out.println(arrearValue);
	    	}
	    	System.out.println(comp);
	    	
	    	String compS=comp.getText();
	    	int compValue=Integer.parseInt(compS);
	    	implicitWaitSec(3000);
	    	String actualValue=arrearValue.getText();
	    	System.out.println("Value of comp: "+compValue+"Value of arrear: "+actualValue);
	    	
	    	
	    	Float compF=Float.parseFloat(compS);
	    	
	    	
	    	String compFinalS;
	    	
	    	long resultArrear;
	    	if(monthsdays().contains(data.get("ArrearMonth"))) 
			{
		    	compFinalS=Float.toString((float)(compF/31)*arrearday);
		    	
	    		resultArrear=(compValue/31)*arrearday;
				System.out.println("comp cal value "+resultArrear);
			}
			else
			{
				compFinalS=Float.toString((float)(compF/30)*arrearday);
				
				resultArrear=(compValue/30)*arrearday;
				System.out.println("comp cal value "+resultArrear);
			}
	    	String arrearR=Integer.toString((int) resultArrear);
			if(arrearR.contains("-"))
			{
				if(compFinalS.contains(".5"))
				{
					long calArrear=Math.subtractExact(resultArrear,2);
					System.out.println("Calculated Arrear value: "+calArrear);
					System.out.println("Actual Arrear value: "+actualValue);
					
					String expected=Long.toString((long)calArrear);
					System.out.println(expected);
					Assert.assertTrue(actualValue.contains(expected));
				}
				else {
					if(compFinalS.contains(".6")||compFinalS.contains(".7")||compFinalS.contains(".8")||compFinalS.contains(".9"))
					{
					long calArrear=Math.decrementExact(resultArrear);
					System.out.println("Calculated Arrear value: "+calArrear);
					System.out.println("Actual Arrear value: "+actualValue);
					String expected=Long.toString((long)calArrear);
					System.out.println(expected);
					Assert.assertTrue(actualValue.contains(expected));
					testCaseLogger.get().pass("Arrear is calculated as expected");
					}
					else 
					{
					String expected=Long.toString((long)resultArrear);
					System.out.println(expected);
					Assert.assertTrue(actualValue.contains(expected));
					testCaseLogger.get().pass("Arrear is calculated as expected");
					}
				}
			}
			else
			{
				long calArrear=Math.round(resultArrear);
				
				System.out.println("Calculated Arrear value: "+calArrear);
				System.out.println("Actual Arrear value: "+actualValue);
				String expected=Long.toString((long)calArrear);
				Assert.assertTrue(actualValue.contains(expected));
				testCaseLogger.get().pass("Arrear is calculated as expected");
			}
			i++;
	    	}
	    	
	    }
		 public ArrayList<String> monthsdays()
		 {
				ArrayList<String> monthList = new ArrayList<String>();
				monthList.add("2021-01");
				monthList.add("2021-03");
				monthList.add("2021-05");
				monthList.add("2021-07");
				monthList.add("2021-08");
				monthList.add("2021-10");
				monthList.add("2021-12");
				
				return monthList;
		 }
		 
		 public void taxyearDropdown(String year)
		 {
			 jsClick(taxYear, "Year Dropdown");
			 implicitWaitSec(2000);
			 selectDropDown(taxYear, year, "ByVisibleText");
		 }
	    
		 
		 public void dashboardTabs(String linkName)
		 {
			// List<WebElement> list=horizontalTab.findElements(By.tagName("a"));
			 System.out.println(horizontalTab.size());
			 		for(int i=0;i<horizontalTab.size();i++)
			 		{
			 			
			 		String value=horizontalTab.get(i).getText();
			 		if(horizontalTab.get(i).getText().equalsIgnoreCase(linkName))
			 		{
			 			click(horizontalTab.get(i), linkName);
			 			implicitWaitSec(8000);
			 			String title=tabtitle.getText();
			 			assertTrue(title.contains(linkName));
			 			testCaseLogger.get().pass("Successfully clicked on tab"+title);
			 			break;
			 		}
			 	}
		 }
		 
		 public void companySelection(String compValue)
		 {
			 jsClick(companyDd, "Company Dropdown");
			 sendKeys(companyDd, compValue, "Company field");
			 waitForElementToBeDisplayed(compNameSuggestion, 5, compValue);
			 jsClick(compNameSuggestion, compValue);
		 }
		 
		 public void generateAndVerifyReport(Map<String,String> data)
		 {
			 click(selectCategory, "Report Category");
			 sendKeys(enterCategory, data.get("Report Category"), "Enter Report Type");
			 implicitWaitSec(2000);
			 enterKeys(enterCategory, Keys.ENTER, "Send keys");
			
			 String reprots=data.get("RosterType");
			 String[] report=reprots.split(",");
			 for(String rValue:report)
			 {
				 boolean flag=false;
				 if(rValue.equalsIgnoreCase("ESIC Format Roster")|| rValue.equalsIgnoreCase("ESIC Monthly Statement") ||rValue.equalsIgnoreCase("PF ECR Roster") )
				 {
					 click(satutarySelect, "Satutatory Report Roster Type");
					 sendKeys(satutaryEntry, rValue, "Satutatory Report Roster Type");
					 implicitWaitSec(2000);
					 enterKeys(satutaryEntry, Keys.ENTER, "Send keys");
					 selectDropDown(satYear, data.get("ESIYear"), reprots);
					 selectDropDown(satMonth, data.get("ESIMonth"), reprots);
					 flag=true;
				 }
				 

				 else if(rValue.equalsIgnoreCase("Future Extra Payments Roster")) 
					{
					 	 implicitWaitSec(2000);
						 click(selectPayrollRoster, "Payroll Roster");
						 sendKeys(enterRosterType, rValue, "Enter Report Roster Type");
						 implicitWaitSec(2000);
						 enterKeys(enterRosterType, Keys.ENTER, "Send keys");
					 
						click(futurePaymentDate, "Enter date");
						sendKeys(futurePaymentDate, data.get("FromDate"), "Enter Date For Conversion");
						 flag=true;
					}
				 
				 else
				 {
				 click(selectPayrollRoster, "Payroll Roster");
				 sendKeys(enterRosterType, rValue, "Enter Report Roster Type");
				 implicitWaitSec(2000);
				 enterKeys(enterRosterType, Keys.ENTER, "Send keys");
				 
				 if(rValue.equalsIgnoreCase("Annual Tax Report"))
					 {
						testCaseLogger.get().pass("Annual Tax Report");
					 }
				
				else {
					
					 click(customRadioButton, "Custom Radio Button");
					 click(fromDate, "From calender");
					 clear(fromDate, "From calender");
					 sendKeys(fromDate, data.get("FromDate"), "Enter From date");
					 enterKeys(fromDate, Keys.ENTER, "Send keys");
					 
				     click(toDate, "To calender");
					 clear(toDate, "To calender");
					 sendKeys(toDate, data.get("ToDate"), "Enter To date");
					 enterKeys(toDate, Keys.ENTER, "Send keys");
					 
					}
				 }
			 
			 click(selectEmp, "selectEmp");
			 selectDropDown(selectEmp,  data.get("SearchOtp"), "ByVisibleText");
			 
			 clear(enterEmpName, "selectEmp");
			 sendKeys(enterEmpName, data.get("EmpName"), "Enter employee name");
			 enterKeys(enterEmpName, Keys.SPACE, "Send keys");
			 enterKeys(enterEmpName, Keys.SPACE, "Send keys");
			 implicitWaitSec(5000);
			 click(suggestedName, "Click on employee name");
			 
			 implicitWaitSec(3000);
			 click(generateButton, "Generate Button");
			 
			 implicitWaitSec(5000);
			 
			 verifyReports(rValue,data);
			 }
		 }
		 
		 public void verifyReports(String reportValue,Map<String,String> data)
		 {
			 if(reportValue.equalsIgnoreCase("Arrear Report on Arrear Month"))
			 {
				 isDataPresentInTable(reporttable, "LOP Arrear", "Arrear Type");
				 implicitWaitSec(2000);
				 isDataPresentInTable(reporttable,this.hraValue , "HRA Alias");
				 isDataPresent(reporttable, "Arrear Month");
				 
				 testCaseLogger.get().pass("Arrear Report on Arrear Month is executed successfully");
			 }
			 
			 if(reportValue.equalsIgnoreCase("Annual Tax Report"))
			 {
				 isDataPresent(reporttable, "Basic Arrears");
				 isDataPresent(reporttable, "HRA Arrears");
				 isDataPresent(reporttable, "Conveyance Allowance Arrears");
				 
				 testCaseLogger.get().pass("Annual Tax Report is executed successfully");
			 }
			 
			 if(reportValue.equalsIgnoreCase("Salary Register Roster")|| reportValue.equalsIgnoreCase("Salary Register On Going Process"))
			 {
				 isDataPresent(reporttable, "Arrear Days");
				 isDataPresent(reporttable, "HRA Arrears");
				 isDataPresent(reporttable, "Conveyance Allowance Arrears");
				 
				 testCaseLogger.get().pass(reportValue+" is executed successfully");
			 }
			 
			 if(reportValue.equalsIgnoreCase("Future Extra Payments Roster")|| reportValue.equalsIgnoreCase("Extra Payments Roster"))
			 {
				 assertFalse(isDataPresent(reporttable, "Arrear Days"));
				 testCaseLogger.get().pass(reportValue+" is executed successfully");
			 }
			 
			 if(reportValue.equalsIgnoreCase("TDS Report Roster"))
			 {
				 isDataPresent(reporttable, "Total Income Tax");
				 isDataPresentInTable(reporttable,this.tdsValue , "Total Income Tax");
				 testCaseLogger.get().pass(reportValue+" is executed successfully");
			 }
			 
			 if(reportValue.equalsIgnoreCase("ESIC Monthly Statement"))
			 {
				 isDataPresentInTable(reporttable,this.esicGross, "ESI Gross");
				 testCaseLogger.get().pass(reportValue+" is executed successfully");
			 }
			 if(reportValue.equalsIgnoreCase("ESIC Format Roster"))
			 {
				 isDataPresentInTable(reporttable,this.esicGross, "Total Monthly Wages");
				 testCaseLogger.get().pass(reportValue+" is executed successfully");
			 }
			 if(reportValue.equalsIgnoreCase("PF ECR Roster"))
			 {
				 isDataPresent(reporttable, "EPF WAGES");
				 testCaseLogger.get().pass(reportValue+" is executed successfully");
			 }
		
			 
		 }
		 public void clickCompensation()
		 {
				click(compensation, "Compensation");
		 }
		 
		 public void fetchValues(String ename)
		 {
			 if(ename.equalsIgnoreCase("Esiccheck Test"))
			 {
					if(TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("True"))
			    	{
						 this.esicGross=totalEsicGrossGrid.getText(); 
			    	}
					else
					{
						 this.esicGross=totalEsicGross.getText(); 
					}
					
					System.out.println(" ESIC GROSS: "+	this.esicGross);
			 }
			 
			else 
			{
				if(TestSetUp.configProperty.getProperty("SELENIUM_GRID").equalsIgnoreCase("True"))
		    	{
				this.tdsValue=tdsTotalGrid.getText();
				this.hraValue=hraGrid.getText();
		    	}
				else
				{
					this.tdsValue=tdsTotal.getText();
					this.hraValue=hra.getText();
				}
				System.out.println("TDS total "+this.tdsValue+"  HRA "+this.hraValue);
			 }
			
		 }
		 
		 public void verifySalaryRegister()
		 {
			 isDataPresent(salaryRegisterTable, "ARREARS");
			 testCaseLogger.get().pass("Payroll Salary Register executed successsfully");
		 }
		 
		 public void processCutOffDaysArrears(Map<String,String> data)
		 {
			 String[] a=data.get("ArrearMonth").split(",");
			 int count=a.length;
			 System.out.println("Count of checkbox input "+count);
			 selectEmployeeArrearsDashboardCheckList(count);
			 implicitWaitSec(3000);
			 clickOnProcessArrears();
		 }
		 
		 
		@Override
		public ExpectedCondition getPageLoadCondition() {
			
			return ExpectedConditions
					.visibilityOf(DriverManager.getDriver().findElement(By.id("UserLogin_username")));
		}
}
