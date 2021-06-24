package com.darwinbox.payroll.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.darwinbox.payroll.pages.ArrearsPage;
import com.darwinbox.payroll.pages.FullAndFinalSettlementPage;
import com.darwinbox.payroll.pages.MenuNavigationPage;
import com.darwinbox.payroll.pages.PayrollAdminPage;
import com.darwinbox.payroll.pages.ResettlementPage;
import com.framework.setup.TestSetUp;
import com.framework.testutils.Constants;
import com.framework.testutils.TestUtils;

public class ResettlementTest extends TestSetUp {
	
	/*
	 * pre-conditions:
	 * used for all the calculations testcases-
	 * //https://payroll.qa.darwinbox.io/payroll/employees/view/id/328151 -(Main181)-(2021-07) - Le,arrear,recovery,lop applied only Basic and payscale month on month
	 * testResettlementForm16AvailablityAndTaxCalculationsForDIfferentFY
	 * //https://payroll.qa.darwinbox.io/payroll/employees/view/tab/payslips/id/241538#pay_slips (MAIN21)- (2021-03)Different Fy Employee
	 */	
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_448_449_450_451_452_453_454_456_457
	 * @Description : " 1. Login to the application
						2. Switch to admin mode 
						3. Go to Payroll 
						4. Go to Resettlement
	 * @Expected: "	1.Resettlement Additional tab needs to be displayed
	 * 				2.Admin should be able to process resettlement only for F&F employees
	 * 				3.Admin should be able to input all the values for which component resettlement is being done
	 * 				4.Admin should be able to select the payout month from the drop down for which resettlement is done
	 * 				5.Once admin clicks on Next a salry register should be displayed in Step 3
	 * 				6.Admin should be able to generate payslip once all these details are correct
	 * 				7."Generate Resettlement Slip" to be enabled for the admin to geenrate the slip
	 * 				8.Once the payslip is generated then a successfull validation message should show up "Payslip has been genrated successfully"
	 * 				9.If there is any error in generating the payslip then system should throw an error
	 * @param : data
	 */
	//Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlement_TC_448_449_450_451_452_453_454_456_457(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.SMOKE_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("SubMenu"));
		//rPage.selectResettlementMonthAndYearDropdown(data.get("YearAndMonth"));
		rPage.selectResettlementGroupCompanyDropdown(data.get("GroupCompany"));
		rPage.selectResettlementEmployee();
		//rPage.selectResettlementEmployees(1);
		rPage.clickOnResettlement();
		rPage.verifyAdminIsAbleToInputAllTheValuesInResettlement();
		rPage.verifyResettlementMonthIsAbleToSelect();
		rPage.clickOnNextButtonInResettlementInputsPage();
		rPage.verifyClicksOnNextSalaryRegisterIsOpened();
		rPage.verifyAdminIsAbleToResettlementOnlyForFAndFEmployees();
		rPage.clickOnNextButtonInResettlementPreviewPage();
		rPage.clickOnGenerateResettlementPayslip();
		rPage.verifyResettlementPayslipGeneration();
	}
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_457_To_466
	 * @Description : " 1. Login to the application
						2. Switch to admin mode 
						3. Go to Payroll 
						4. Go to Resettlement
	 * @Expected: "	1.Resettlement Payslip should also have the option to download in PDF
	 * 				2.The payslip should have a header "RESETTLEMENT PAYSLIP" at the top of the payslip below Group company name
	 * 				3.Resettlemnet employee shouldn't be part of any regular payroll and salary register
	 * 				4.Only resettlement applicable components to be shown in Resettlement Payslip
	 * 				5.No Statutory deductions to be considered except TDS
	 * 				6.Under Earnings column only componenets which are processed should be shown
	 * 				7.Only leave enashment component to be visible in Payslip under earnings column
	 * 				8.Only leave enashment & Bonus component to be visible in Payslip under earnings column
	 * 				9.Only TDS component to be displayed under deduction column (if applicable)
	 * 				10.LOP Monthwise also needs to be removed, because month wise lop cannot be calculated in the resettlement
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementPayslip_TC_457_To_466(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateResettlementEmp();
		PayrollAdminPage pPage = new PayrollAdminPage().getPayrollAdminPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.viewSalarySlip();
		rPage.verifyResettlementPayslip();
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_001_TO_005,TC_008,TC_012,TC_015_TO_TC_022
	 * @Description : " 1. 1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month and group company
	 * @Expected: "	1.User should be logged into the application and should be landed on the dash board of resettlement(Landing) page with the selected group company
	 * 				2.User should be logged into the application and should be landed on the dash board of resettlement(Landing) page with the all as company option
	 * 				3.User should be logged into the application and should be landed on the dash board of resettlement(Landing) page with the Year & Month Selection
	 * 				4.User should be logged into the application and should be landed on the dash board of resettlement(Landing) page with the Year & Month Selection in header
	 * 				5.Table with following data to be visible:1.Employee ID2. Employee Name3. Designation4. Department5. Date of joining6. Date of Resignation 
	 * 				  7.Last working day (Exit Date)8. F&F Month9. Actions---View symbol (direct to C&B Page)
	 * 				6.Direct to C&B Page needs to redirect to Separation Page of the employee
	 * 				7.Employee id needs to same as for whom we needs to do Resettlement
	 * 				8.Employee Name needs to same as for whom we needs to do Resettlement
	 * 				9.Employee designation needs to same as for whom we needs to do Resettlement
	 * 				10.Department needs to same as for whom we needs to do Resettlement as per Employee profile
	 * 				11.Employee Date of Joining needs to same as for whom we needs to do Resettlement as per employee profile
	 * 				12.Employee date of Resignation needs to same as for whom we needs to do Resettlement in the selected month
	 * 				13.Employee date of Leaving needs to same as for whom we needs to do Resettlement in the selected month
	 * 				14.Employee F&F month needs to shown for whom we needs to do Resettlement in the selected month
	 * 				15.Resettlement dashboard employee to be shown, if fnf already processed
	 * 
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementSelectEmployeeGroupPage(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("SubMenu"));
		rPage.verifyResttlementYearAndCompanyDropdowns();
		rPage.findResettlementEmployee();
		rPage.verifyEmployeeGroupPageTable();
	}
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_010_TO_014,TC_023_TO_026,TC_028
	 * @Description : " 1.1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month and group company
	 * 					5. Resettlement employee data to be shown in table
	 * 					6. Selection option to be avaialble
	 *
	 * @Expected: 1.Check box for each column to be available
	 * 			  2.Check box for selecting all employees in the list/Table to be available
	 * 			  3.3 stpe progress bar to be shown
	 * 			  4.Each step going forwar in resettlement to have respective headers on the top to indicate the progress and step
	 * 			  5.Progress needs to move from Step1 to Step2
	 * 			  6.Error to be thrown "No Employee Selected" or "Please Select an Employee"
	 * 			  7.Progress needs to be in Step2 " Resettlement Inputs" as header with the month and year
	 * 			  8.If Multiple employees selected. Count needs to be shown with scroll bars if more in a page to scroll up and down and Left to Right
	 * 
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementProgressBar(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("SubMenu"));
		rPage.findResettlementEmployee();
		rPage.verifyResettlementEmployeeCheckBoxeSelectionAndProgressBar();
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_027,TC_029,TC_030,TC_036_TO_038
	 * @Description : " 1.1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month and group company
	 * 					5. Resettlement employee data to be shown in table
	 * 					6. Selection option to be avaialble
	 *
	 * @Expected: 1.Employee/Employees selected in Step 1 needs to be shown in Step2 with details pre populated
	 * 			  2.Employee id needs to same as for whom we needs to do Resettlement and selected in Step1
	 * 			  3.Employee Name needs to same as for whom we needs to do Resettlement and selected in Step1
	 * 			  4.Employee Date of Joining needs to same as for whom we needs to do Resettlement as per employee profile
	 * 			  5.Employee date of Resignation needs to same as for whom we needs to do Resettlement in the selected month
	 * 			  6.Employee Last working day (Exit Date) needs to same as for whom we needs to do Resettlement in the selected month
	 * 
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementDetailsFromSelectEmployeeGroupPageToResettlementInputsPage(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("SubMenu"));
		rPage.findResettlementEmployee();
		rPage.verifyDetailsFromSelectEmployeeGroupPageToResettlementInputsPage();
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_030_TO_035,39
	 * @Description : " 1-1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month and group company
	 * 					5. Employee to be selected (Multi/Individual)
	 * 					6. Run Resettlement Button to be clicked
	 * @Expected: 1.Resettelemnt Month should be based on current date
	 * 			  2.Admin should be able to select any month  after the F&F Pay month
	 * 			  3.Resettlement month also can be exit month if FnF and Exit months are same
	 * 			  4.Resettlement month Should not be able to select any month before F&F Pay month
	 * 			  5.Resettelemnt month to be Restricted for the months to next financial year end
	 * 			  6.Employee F&F month needs to shown for whom we needs to do Resettlement in the selected month based on the Last working date and Custom Pay cycle
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementPayMonth(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("SubMenu"));
		rPage.findResettlementEmployee();
		rPage.testResettlementPaymonth();
	}
	
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_006,TC_007
	 * @Description : " 1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month Group Company
	 * 					5.FnF should already processed in Exit month
	 *
	 * @Expected: 1.Resettlement dashboard employee will not to be shown, if fnf not processed in the selected month for Exit Employee
	 * 			  2.Resettlement dashboard employee not show, if resettelemnet already processes already processed		
	 * 		https://payroll.qa.darwinbox.io/payroll/employees/view/tab/payslips/id/110631#pay_slips -Done Resettlement
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementEmployeeFAndFStatus(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		FullAndFinalSettlementPage fPage = new FullAndFinalSettlementPage().getFullAndFinalSettlementPage();
		fPage.navigateToFAndF(data.get("SubMenu"));
		HashMap<String, String> details =fPage.selectAnFAndFEmployee();
		rPage.verifyFAndFEmployeeInResettlement(details.get("empID"),details.get("SelectedYearAndMonth"));
		rPage.verifyFAndFEmployeeInResettlement(data.get("EmpID"), data.get("Month"));	
	}
	
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_046,TC_047,TC_048,TC_049
	 * @Description : " 1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month Group Company
	 * 					5.FnF should already processed in Exit month
	 *
	 * @Expected: 1.Based on Salary structure Setting (Enable for Leave Encashment component)
	 * 			  2.If Leave Encashment enabled for the component , will considered for per day calculcation and multiply with no.of days
	 * 			  3.All components for the salary structure applied at current FnF month considered for calculation and all added together to get value
	 * 			  4.Sample Calculation:Salary structure : Monthly values (Basic (Enabled LE) - 30000,  - No.of Days in FnF month = 30 --> 30000/30 = 1767
	 * 				Leave Encashmnet Days - 12 Amount=12*1767 = 21200
	 * 	//https://payroll.qa.darwinbox.io/payroll/employees/view/id/328151 -Delete Aug
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementLeaveEncashmentCalculations(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		MenuNavigationPage mPage= new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		mPage.navigateToSubMenu(data.get("LowerSubMenu"));
		rPage.leaveEncashmentSettings();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.verifyLeaveEncashement(data);

	}
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_050,TC_051
	 * @Description : " 1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month Group Company
	 * 					5.FnF should already processed in Exit month
	 *
	 * @Expected: 1.On the calculated amount: Tax exemption should only be applied on Basic irrespective of the selection of Basic or Gross
	 * 			  2.Maximum tax exemption is 3 lacs for Leave Encashment
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void tesLeaveEncashmentTaxCalculation(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.verifyLeaveEncashmentTaxException(data);
	}
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_065_TO_TC_068 ,TC_077,TC_080,TC_084,TC_085
	 * @Description : " 1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month and group company
	 * 					5. Employee to be selected (Multi/Individual)
	 * 					6. Run Resettlement Button to be clicked
	 * 					7. Data pre populates in Step2
	 * @Expected: 1.There should be Export button available at top of the page
	 * 			  2.Upon clicking export button the sample file shoud be downloaded
	 * 			  3.There should be TDS column added in the export file
	 * 			  4.By clicking on Imports button - Should redirect to the imports page to import all Resettlement information
	 * 			  5.Next will be moving to Step 3: "Preview and Generate Payslip" with Month and Year along with it in Header
	 * 			  6.All data for below fields needs to be pre-populated from Step 2 to Step3:
	 * 			  7.Search to search for required employee to verify values
	 * 			  8.Generate Payslips button to generate the payslips for the resettlement processed employees from Step 2 to Step3
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResetlementExportAndPreviewAndGeneratePayslipPage(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.selectResettlementEmployee();
		rPage.verifyImportAndExport();
		rPage.verifyPreviewAndGeneratePayslipPage();
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_081_TC_082
	 * @Description : " 1-Login as Admin
	 * 					2-Click on Payroll Icon from dashboard
	 * 					3-Click on Resettlement
	 * 					4-Select Year & Month and group company
	 * 					5. Employee to be selected (Multi/Individual)
	 * 					6. Run Resettlement Button to be clicked
	 * 					7. Next in Step2 after updating data manually or import
	 * @Expected: 1.LOP Days = 5, Arrear Days= 7, Leave Encashment Days = 14, Recovery Days = 6, Net Days= Leave Encashmnet days+Arrear Days-Recovery Days - LOP Days = 10
	 * 			  2.Based on Net Days and Salary structure settings - Amount needs to be calculated for Income i.e, Gross
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testNetDaysCalculations(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		MenuNavigationPage mPage= new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		mPage.navigateToSubMenu(data.get("LowerSubMenu"));
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.netDaysSettings();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.verifyNetDaysCalculation(data);	
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_091_TO_TC_096
	 * @Description : 	1-Login as Admin
	 * 				  	2-Click on settings
	 * 				  	3. Click on Payroll
	 * 					4. Click on Extra settings
	 * @Expected: 	1.If Enabled , GST will be applied on recovery days. else normal calculation will be happening
	 * 				2.If Apply GST is selected as “Yes”, the the Recovery calculation should be Basi c (or Gross) * Recovery days * (1.18) to account for GST of 18%
	 * 				3.If Apply GSt is No, it would be the same as now i.e., Basic (or Gross) * Recovery Days
	 * 				4.If in Extra settings "Calculate Recovery on Monthly Gross" is selected then recovery should be on Gross amount
	 * 				5.If in Extra settings "Calculate Recovery on Monthly Gross" is not selected then recovery should be based on components selected in Salary structure
	 * 				6.the recovery amount calculation should be based on denominator selected in extra settings
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testRecoveryDaysCalculations(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		MenuNavigationPage mPage= new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		mPage.navigateToSubMenu(data.get("LowerSubMenu"));
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.recoveryDaysSettings(data);
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.verifyRecoveryDaysCalculation(data);
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_040_TO_045_TC_052_058,TC_061,TC_062
	 * @Description : 	1-Login as Admin
	 * 2-Click on Payroll Icon from dashboard
	 * 3-Click on Resettlement
	 * 4-Select Year & Month and group company
	 * 5. Employee to be selected (Multi/Individual)
	 * 6. Run Resettlement Button to be clicked
	 * 6. Run Resettlement Button to be clicked
	 * @Expected: 	1.If LOP has been imported/sync happened data needs to be pre-populated, if needed can be overridden as it is editable field
	 * 				2.If No LOP days data available, Total LOP days - editable field with default as 0
	 * 				3.If Total LOP Arrear days has been imported/synced from Arrear module data needs to be pre-populated, if needed can be overridden as it is editable field
	 * 				4.If No Total LOP Arrear days data available, Total LOP Arrear days - editable field with default as 0
	 * 				5.If Leave encashment days has been imported/synced from Leave module data needs to be pre-populated, if needed can be overridden as it is editable field
	 * 				6.If Leave encashment days data available, Leave encashment days - editable field with default as 0
	 * 				7.If Recovery Days has been imported/synced from Arrear module data needs to be pre-populated, if needed can be overridden as it is editable field
	 * 				8.If No Recovery Days data available, Recovery Days - editable field with default as 0
	 * 				9.Editable with an option to Add taxable and non-taxable Extra payments
	 * 				10.Admin can add various Extra Payments with positive or negative values along with Transaction Name
	 * 				11.Transaction Name and amount needs to be entered in the fields provided, if needed can click on add another transaction
	 * 				12.Update for finalizing the added transactions for Extra Paymnets
	 * 				13.Additional fields for each transaction to select (Non Taxable) next to Amount. If selected, then it will be non- taxable, elese by default value will be taxable
	 * 				14.Action (Delete ) option will be available to delete the transaction, if not needed once added
	 * 				15.Once added if wants to delete all and make extra Payment as Zero, Delete all transactions and make last transaction amount as zero and remove the name to make extra payment as Zero
	 * @param : data	
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementInputs(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.selectResettlementEmployee();
		rPage.verifyResettlementInputs(data);
	}
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_051,TC_083,TC_102,TC_103,TC_105,TC_108,TC_109,
	 * @Description : 	1-Login as Admin
	 * 2-Click on Payroll Icon from dashboard
	 * 3-Click on Resettlement
	 * 4-Select Year & Month and group company
	 * 5. Employee to be selected (Multi/Individual)
	 * 6. Run Resettlement Button to be clicked
	 * 7. Data pre populates in Step2
	 * @Expected: 	1.Only taxable payments should be considered for exemption, while calculcating Tax
	 * 				2. Tax needs to be calculated based on the income and slab rate by including Extra paymnets which are taxable
	 * 				3.The Resettlement Payout should reflect in the Payout month
	 * 				4.If there are more than one process or Pay slips (Regular, F&F or Resettlement in the same month), then the amounts should be added
	 * 				5.Recovery and Leave encashment should be used based on selection from the Salary structure based on the Exit month
	 * 				6.Non-taxable Extra Payments should be exempted in Annual tax calculations
	 * 				7.Non taxable Extra Payments should be shown as "Resettleemnt Reimbursements" under tax sheet and should be exempted from tax
	 * 				8.Exit month, F&F and Resettlement month are in Same financial year: In this case, the tax is calculated based on the total Pay from regular, F&F and Resettlement
	 * 	
	 * @param : data	
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone",priority=15)
	public void testResettlementExtraPaymentsAndTaxCalculations(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.selectResettlementEmployee();
		//rPage.selectResettlementEmployeeBasedOnEmpID("SHA_196","2021-09");
		rPage.verifyTaxCalculations(data);
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToSubMenu("SubMenu");
		PayrollAdminPage pPage = new PayrollAdminPage().getPayrollAdminPage();
		pPage.deleteSalarySlips();
		
	}

	
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : TC_110,TC_111,TC_113,TC_114,TC_115,TC_116,TC_117,TC_118
	 * @Description : 	1. Employee/Admin Login
	 * 					2. Compensation
	 * 					3. Taxsheet
	 * @Expected: 	1.Exit month, F&F month and Resettlement are in different financial years: In this case, for current financial year, the tax calculations should be based on the Pay in the current year
	 * 				2.For next FY, the tax should be based on the Income during Resettlement - Leave encashment will be exempted
	 * 				3.Exit/F&F month and Resettlement month are in different financial years: In this case, we need to generate 2 different Form 16’s - one for Exit year and one for Resettlement Year
	 * 				4.Both the Form 16’s should be visible based on the Financial Year selection in Pay package (later on Pay slip tab).
	 * 				5.Exit/F&F month and Resettlement month are in Same financial year: In this case, Resettlement outputs should be added to Form 12B
	 * 				6.Exit/F&F month and Resettlement month are in different financial year: In this case, we need to generate 2 different Form 12B’s - one for Exit year and one for Resettlement Year
	 * 				7.Both the Form 12B’s should be visible based on the Financial Year selection in Pay package (later on Pay slip tab)
	 * 				8 .The values in the Form 16 should be added based on F&F and Resettlement. We should have only one Form 12B per year.	
	 * //https://payroll.qa.darwinbox.io/payroll/employees/view/tab/payslips/id/241538#pay_slips
	 * @param : data	
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementForm16B12BAvailablityAndTaxCalculationsForDifferentFYs(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.navigateToResettlement(data.get("Resettlement"));
		rPage.selectResettlementEmployeeBasedOnEmpID(data.get("EmpID"),data.get("Month")); //"Main21","2021-03"
		rPage.verifyForm16AvailablityAndTaxCalculationsForDIfferentFY(data);
		PayrollAdminPage pPage = new PayrollAdminPage().getPayrollAdminPage();
		pPage.deleteSalarySlips();
		rPage.verifyForm12B();
	}
	
	/**
	 * @author Rajashekar
	 * @TestCaseID : DBX-13490_001, DBX-13490_002
	 * @Description : 	1. Employee/Admin Login
	 * 					2. Compensation
	 * 					3. Taxsheet
	 * @Expected: 	1.Alias option required in Extra settings for giving alias name
	 * 				2.Deault  name will be Resettlement 
	 * @param : data	
	 */

	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void testResettlementAlias(Map<String, String> data)
			throws InterruptedException {
		testCaseLogger.get().assignAuthor("Rajashekar");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage aPage = new ArrearsPage().getPayrollPage();
		aPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		aPage.implicitWaitSec(2000);
		aPage.switchToAdmin();
		MenuNavigationPage mPage= new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		mPage.navigateToSubMenu(data.get("LowerSubMenu"));
		ResettlementPage rPage = new ResettlementPage().getResettlementPage();
		rPage.verifyResettlementAlias();
	}
}









