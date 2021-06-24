/**
 * @author prabhudatta, Habeeba
 *
 */
package com.darwinbox.payroll.testcases;

import java.util.Map;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.darwinbox.payroll.pages.ArrearsPage;
import com.darwinbox.payroll.pages.FullAndFinalSettlementPage;
import com.darwinbox.payroll.pages.MenuNavigationPage;
import com.darwinbox.payroll.pages.PayrollAdminPage;
import com.darwinbox.payroll.pages.PayrollDashboardPage;
import com.framework.setup.TestSetUp;
import com.framework.testutils.Constants;
import com.framework.testutils.TestUtils;


public class ArrearsTest extends TestSetUp {

	
	
	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_001 to PR_TC_Arrear_008, PR_TC_Arrear_11,PR_TC_Arrear_102
	 * @Description : 	    1.	Arrears Module Tab needs to be available
							2.	Verifying that whether the admin able to select Arrears Dashboard
							3.	Verifying that whether the user able to downlaod
							4.	Verifying that whether the user able to filter Year and Month wise information
							5.	Verifying the whether the user able to search
							6.	Verifying that whether the user able to view the company wise employees arrears information
							7.	Verifying that whether the user able to view the status of the arrears process
							8.	Verifying that whether the user able to export New Joinee Arrears data
							9.  Verifying that whether the admin able to view the Arrears Processed/ On-going processing Dashboard

     *
     * 
     *
	 * @Expected:   		1.	Arrears Tab to be Displayed"
							2.	able to select Arrears option in the menu bar.
							3.	Arrears dashboard should be displayed
							4.	opened and should be able to select Arrears option in the menu bar.
							5.	User should able to download the Arrears data to the local system and it should display the employees arrears data
								Verify that the month dropdown and it should display only the months which are coming under that financial year.
							6.	Admin should be able to search by the entities and he should be able to view the particular employees information
								Verify that the dropdown should display the company name and it should allow him/her to select the particular company.
							7.	Verify that it should display the employees information only which are belongs to that company.
							8.	Verify that application should allow user to export the new joinees arrears data in the csv format.
							9.  Verify that it should display only latest status of Processed Arrears or On-Going Arrears Processing list.
     *

     *
	 * 
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyArrearPage_PR_TC_Arrear_001_to_008_11(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_001 to PR_TC_Arrear_008, PR_TC_Arrear_11,PR_TC_Arrear_102");
		printTestDataSet(data);
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		
		pPage.validateFYMonth(data.get("FY"), data.get("Month"));
		
		pPage.verifyEmployeeCompanywise(data);
		
		pPage.implicitWaitSec(5000);
		pPage.verifyArrearStatus(data);
		
		pPage.implicitWaitSec(3000);
		pPage.searchEmployeeArrear(data.get("Employee"));
		pPage.exportToCSV();
		pPage.verifyProcessDashBoard();
	}
	
	
	
	
	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_009,PR_TC_Arrear_10,PR_TC_Arrear_12
	 * @Description :	1. Verify that the admin is able to delete particular/bulk employees arrears data
						2. Verifying whether the admin able to add new arrears
						3. Verify that the admin is able to edit the existing/already created employees arrears data

     *
     *
	 * @Expected:    1. Login should be successfull with the valid credentials.
	 *				 2.	Verify that the same means all enterd data is displaying correctly under the Arrears Dashboard
	 *				 3. User should be able to delete the data.
	 * 
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void createEditDeleteArrear_PR_TC_Arrear_009_10_12(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_009,PR_TC_Arrear_10,PR_TC_Arrear_12");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.validateFYMonth(data.get("FY"), data.get("Month"));
		pPage.addArrear(data);
		pPage.verifyExistingArrear();
		pPage.implicitWaitSec(3000);
		pPage.verifyEditArrear(data);
		pPage.deleteArrears(data);
		
	}
	
	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_027,PR_TC_Arrear_029,PR_TC_Arrear_188,PR_TC_Arrear_030,PR_TC_Arrear_031,PR_TC_Arrear_039,PR_TC_Arrear_141,PR_TC_Arrear_142,PR_TC_Arrear_143,PR_TC_Arrear_144,PR_TC_Arrear_133,PR_TC_Arrear_134,PR_TC_Arrear_135
	 * 1.Restriction of Arrear days deletion in case if Payslip is generated/available for the month
	 * 2.Delete of Arrear Should not allow once payroll / Payslip is processed
	 * 3.Arrear already Processed for total days it should restrict
	 * 4.Should allow to enter negative values in LOP Reversal
	 * 5.Negative LOP Should refer the Previous Deducted LOP and already processed LOP
	 * 6.Negative Entries of the respective months should be considered as LOP and For reversal in Future
	 * 7.Negative LOP Should Restrict if the whole month days are Recovered already
	 * 8.Check if Arrear compted earlier for Previous month LOP Days provided
	 * 9.Consideration of Days based on LOP Days in the month
	 * 10.LOP Days check in previous month
	 * 11.Edit Arrear days & Month in Front End, after Processing it should restrict
	 * 12.Restriction of Arrear days addition in case if Payslip is generated/available for the month
	 * 13.Vaidation while adding Employees to Arrears Page: Employee, Arrear Type, Days, Month
	 * Expected:
	 * @Expected:  1.Verify that the apllication should restrict or do not allow user to delete arrear days once the payslip is generated.
	 *			   2.Verify that the apllication should restrict or do not allow user to delete arrears days once the payslip is processed.
	 *			   3.Verify that the same means all enterd data is displaying correctly under the Arrears Dashboard
	 *			   4.Verify that the same means all enterd data is displaying correctly under the Arrears Dashboard
	 *			   5. Verify that the same means all enterd data is displaying correctly under the Arrears Dashboard 
	 *			   6. Verify that the same means all enterd data is displaying correctly under the Arrears Dashboard
	 *			   7.Verify that the same means all enterd data is displaying correctly under the Arrears Dashboard
	 *			   8,9,10. Process needs to be failed if there is no LOP days for previous months
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyArrearCompletionProcess(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_027,PR_TC_Arrear_029,PR_TC_Arrear_188,PR_TC_Arrear_030,PR_TC_Arrear_031,PR_TC_Arrear_039,PR_TC_Arrear_141,PR_TC_Arrear_142,PR_TC_Arrear_143,PR_TC_Arrear_144,PR_TC_Arrear_133,PR_TC_Arrear_134,PR_TC_Arrear_135");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		mPage.navigateToMenu(data.get("PaySlipMenu"));
		mPage.navigateToSubMenu(data.get("PaySlipSubMenu"));
		pPage.generatePaySlip(data.get("PayMonthEarlier"),data.get("PaySlipMonthEarlier"));
		
		pPage.implicitWaitSec(5000);
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.validateFYMonth(data.get("FY"), data.get("Month"));
		pPage.addArrear(data);
		pPage.verifyExistingArrear();
		pPage.implicitWaitSec(3000);
		pPage.verifyEmptyTable(data);
		pPage.completeArrear(data);
		
		mPage.navigateToMenu(data.get("PaySlipMenu"));
		mPage.navigateToSubMenu(data.get("PaySlipSubMenu"));
		pPage.generatePaySlip(data.get("PayMonthCurrent"),data.get("PaySlipMonthCurrent"));
		
		pPage.implicitWaitSec(5000);
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.verifyRestriction(data);
		
		mPage.navigateToMenu(data.get("PaySlipMenu"));
		mPage.navigateToSubMenu(data.get("PaySlipSubMenu"));
		pPage.deletePaySlip(data);
	}	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_037,PR_TC_Arrear_038
	 * PR_TC_Arrear_073 to PR_TC_Arrear_077
	 * PR_TC_Arrear_080,PR_TC_Arrear_091,PR_TC_Arrear_106,PR_TC_Arrear_107,PR_TC_Arrear_109,PR_TC_Arrear_110,PR_TC_Arrear_111,PR_TC_Arrear_118
	 * ,PR_TC_Arrear_122,PR_TC_Arrear_123,PR_TC_Arrear_136,PR_TC_Arrear_141,PR_TC_Arrear_142
	 * 
	 * 1.Is Arrear bifurcating and computing individual Component wise in case of New Joinee Arrear
	 * 2.Re-compute Arrear in case of modification in Salary Structure
	 * 3.PF Arrear Computation if Option Pf arrear is ticked or not
	 * 4.PF Arrear Computation if Option Pf arrear is ticked or not
	 * 5.PF Arrear check based on PF Option updated in Profile either restricted / Un-restricted
	 * 6 PF Arrear check based on PF Option updated in Profile either restricted / Un-restricted
	 * 7.PF Arrear check based on PF Option updated in Profile either restricted / Un-restricted
	 * 8.Arrear check post disabling "Affects Arrears" option for component in Salary Structure
	 * 9.Arrear check post re-enabling "Affects Arrears" option for component in Salary Structure
	 * 10. Arrear Processing after Change in Salary Structure and its calcaultion
	 * 11. Check Arrear in case of Multiple Salary Structure changes
	 * 12.Master Salary Consideration for the Arrear computation based on specified Month- Lop and Increment
	 * 13. PF Paid difference check with Arrear based on Restricted PF - Lop and Increment
	 * 
	 * 
	 * Expected: If affect arrears selected the component should have been considered for arrear calculation, else no calculation of arrears
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifySalaryStructureArrear(Map<String, String> data)
	{

		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_037,PR_TC_Arrear_038\n"
				+ "	  PR_TC_Arrear_073 to PR_TC_Arrear_077\n"
				+ "	  PR_TC_Arrear_080,PR_TC_Arrear_091,PR_TC_Arrear_106,PR_TC_Arrear_107,PR_TC_Arrear_109,PR_TC_Arrear_110,PR_TC_Arrear_111,PR_TC_Arrear_118\n"
				+ "	 ,PR_TC_Arrear_122,PR_TC_Arrear_123,PR_TC_Arrear_136,PR_TC_Arrear_141,PR_TC_Arrear_142");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		mPage.navigateToSubMenu(data.get("LowerSubMenu"));
		pPage.eidtSalaryStructure(data);
		
		mPage.navigateToMenu(data.get("ArrearMenu"));
		mPage.navigateToSubMenu(data.get("ArrearSubMenu"));
		
		pPage.validateFYMonth(data.get("FY"), data.get("Month"));
		pPage.addArrear(data);
		pPage.verifyExistingArrear();
		pPage.implicitWaitSec(3000);
		pPage.verifyEmptyTable(data);
		pPage.computeArrear(data);
	
	}
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_87,PR_TC_Arrear_ 88,PR_TC_Arrear_129,PR_TC_Arrear_130
	 * 
	 * Arrears accumilation in Benefits Page
	 * 
	 * expected: Arrear must be shown in Benefits page
	 * 
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyFlexiBenefits(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_87,PR_TC_Arrear_ 88,PR_TC_Arrear_129,PR_TC_Arrear_130");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.verifyFlexiArrear();
	}
	
	
	
	/**
	 * @author Habeeba
	 * @TestCaseID : APR_TC_Arrear_089,PR_TC_Arrear_084,PR_TC_Arrear_085,PR_TC_Arrear_086,PR_TC_Arrear_126,PR_TC_Arrear_127,PR_TC_Arrear_128,PR_TC_Arrear_131,PR_TC_Arrear_150
	 *	PR_TC_Arrear_158 to PR_TC_Arrear_162
	 *  PR_TC_Arrear_219 to PR_TC_Arrear_230
	 * 
	 * @PreCondition- Payslip must be process for may month which have arrears on april month
	 * 
	 * 1. Arrears consideration in Section 10 Exemption
	 * 2.PF Arrears consideration for Exemption
	 * 3.Arrears consideration in Income Tax
	 * 4.Basic Arrears and HRA Arrears consideration for HRA Exemption
	 * 5.Arrear display and consideration in Tax Sheet
	 * 6.Values of Arrear Should add in taxhseet with breakup in the Income table
	 * 7.Arrear Values should add in the Gross Salary,Total Gross Salary and Net Taxable Salary
	 * 8.Tax Should calcaulte after Including arrear calcaultions
	 * 9.HRA Exemption should consider the calcualtion for respective Month for which arrera is processed 
	 * 
	 * expected: Arrear value to be considered for Exemption and added to IT Declaration and shown in Taxsheet
	 * 
		 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyTaxSheetComputation(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_089,PR_TC_Arrear_084,PR_TC_Arrear_085,PR_TC_Arrear_086,PR_TC_Arrear_126,PR_TC_Arrear_127,PR_TC_Arrear_128,PR_TC_Arrear_131,PR_TC_Arrear_150\n"
				+ "	 	PR_TC_Arrear_158 to PR_TC_Arrear_162\n"
				+ "	  PR_TC_Arrear_219 to PR_TC_Arrear_230");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.implicitWaitSec(5000);
		pPage.taxyearDropdown(data.get("FY"));
		pPage.taxSheetComputation(data);
	}

	/**
	 * @author Habeeba
	 * @TestCaseID : APR_TC_Arrear_089
	 * @PreCondition- Payslip must be process for may month which have arrears on april month
	 * Description: March Month Joinees arrear Processing sgoud have option in April month to select March as Arrear to be processed
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyMarchJoineeArrear(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : APR_TC_Arrear_089");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.implicitWaitSec(5000);
		pPage.validateFYMonth(data.get("FY"), data.get("FYMonth"));
		pPage.addArrear(data);
		pPage.verifyExistingArrear();
		pPage.implicitWaitSec(3000);
	}
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_047 to PR_TC_Arrear_052
	 *      1.	Verify if New Joinee Arrears is Clickable from payroll Inputs
			2.	Verify if LOP arrears for previous months is Clickable from payroll Inputs
			3.	Verify if Increment Arrears is Clickable from payroll Inputs
			4.	Verify if Permission Holder is able to add on New Joinee Arrears from payroll Inputs
			5.	Verify if Permission Holder is able to click on Lop arrear for previous month from payroll Inputs
			6.	Verify if Permission Holder is able to click on Increment arrear from payroll Inputs
			
	 * @expected:   1. New joinee arrears Tab Should be clickable
					2. LOP arrears for previous months Tab Should be clickable
					3. Increment Arrears Tab Should be clickable
					4. New joinee arrears Tab Should be clickable
					5. LOP arrears for previous months Tab Should be clickable
					6. Increment Arrears Tab Should be clickable

	 * @PreCondition- Payslip must be process for may month which have arrears on april month
	 * Description: March Month Joinees arrear Processing sgoud have option in April month to select March as Arrear to be processed
	 * @param : data
	 */
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyDashBoardTabs(Map<String, String> data)
	{

		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_047 to PR_TC_Arrear_052");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		mPage.navigateToMenu(data.get("Menu"));
		pPage.implicitWaitSec(3000);
		pPage.companySelection(data.get("Company"));
		pPage.implicitWaitSec(10000);
		pPage.dashboardTabs(data.get("LinkName"));
	}
	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_082,PR_TC_Arrear_083,PR_TC_Arrear_090,PR_TC_Arrear_124,PR_TC_Arrear_125,PR_TC_Arrear_132,PR_TC_Arrear_167,PR_TC_Arrear_168,PR_TC_Arrear_204
	 * PR_TC_Arrear_217,PR_TC_Arrear_218,PR_TC_Arrear_236,PR_TC_Arrear_237
	 * PR_TC_Arrear_240 to PR_TC_Arrear_250
	 * 
	 *      1.	Arrears display in Salary Register
			2.  Arrears display in PF ECR
			3. TDS Report-Arrear Values should add in the Gross Salary
			4. TDS Reprot-Monthly tax should show the breakup of Raw tax, Surcharge, Cess after adding the Arrear breakup
			5. Arrear Monthly report
			6. Salary Register in Reports
			7. Salary Register Ongoing Process in Reports
			8. Arrear Report to Verify Monthly Arrear Values
			9. Arrear Report on Pay Month
		    10. Arrear Columns to be added
			11. PF ECR Report
			12. ESI Format Roster in Reports
			13. ESI Monthly Statement Roster in Reports
			14. PT Report Roster in Reports
			15. Future Extra Payment Roster in Reports
			16. Extra Payment Roster in Reports
			17. 
			
			
	 * @expected:   Show the value based on the selection month for the respective components Arrear isthere, needs to display the value as per process
	 * 		
	 * @PreCondition- Payslip must be process for may month which have arrears on april month
	 * Description: March Month Joinees arrear Processing sgoud have option in April month to select March as Arrear to be processed
	 * @param : data
	 */
	
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyArrearInReports(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_082,PR_TC_Arrear_083,PR_TC_Arrear_090,PR_TC_Arrear_124,PR_TC_Arrear_125,PR_TC_Arrear_132,PR_TC_Arrear_167,PR_TC_Arrear_168,PR_TC_Arrear_204\n"
				+ "	  PR_TC_Arrear_217,PR_TC_Arrear_218,PR_TC_Arrear_236,PR_TC_Arrear_237\n"
				+ "	  PR_TC_Arrear_240 to PR_TC_Arrear_250");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		
		PayrollDashboardPage page=new PayrollDashboardPage().getPayrollPage();
		page.searchAndClickOnProfile(data);
		pPage.clickCompensation();
		pPage.implicitWaitSec(3000);
		mPage.navigateToSubMenu(data.get("FSubMenu"));
		pPage.implicitWaitSec(5000);
		pPage.taxyearDropdown(data.get("FY"));
		pPage.implicitWaitSec(3000);
		pPage.fetchValues(data.get("EmpName"));
		
		mPage.navigateToMenu(data.get("Menu"));
		pPage.implicitWaitSec(3000);
		pPage.generateAndVerifyReport(data);
	}
	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_214 to PR_TC_Arrear_216
	 *     Arrear Should appear in a respective column next to base component and get added in Income/Gross and other impact like PF, ESI, Tax and Total Decuctions, Netpay
			
	 * @expected:   
	 * 		Result : Based ont he selection of Group company/Employee all the respective Arrear paid values to be displayed in the report
	 * 
	 * @PreCondition- Payslip must be process for may month which have arrears on april month
	 * Description: March Month Joinees arrear Processing sgoud have option in April month to select March as Arrear to be processed
	 * @param : data
	 */
	
	
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyArrearRunPayroll(Map<String, String> data)
	{
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_214 to PR_TC_Arrear_216");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		
	    FullAndFinalSettlementPage fPage = new FullAndFinalSettlementPage().getFullAndFinalSettlementPage();
	        fPage.navigateToFAndF(data.get("SubMenu"));
	        fPage.selectFAndFGroupCompanyDropdown(data.get("GroupCompany"));
	        fPage.selectAnFAndFEmployee();
	        fPage.clickOnRunPayroll();
	        fPage.clickOnNextButtonInFAndFInputsPage();
	        fPage.goToSalaryRegister();
	        pPage.implicitWaitSec(8000);
	        pPage.verifySalaryRegister();

	}
	
	/**
	 * @author Habeeba
	 * @TestCaseID : PR_TC_Arrear_103 to 105
	 *      1.	
			
	 * @expected:   
	 * 		1.
	 * @PreCondition- Payslip must be process for may month which have arrears on april month
	 * Description: March Month Joinees arrear Processing sgoud have option in April month to select March as Arrear to be processed
	 * @param : data
	 */
	@Test(dataProviderClass = TestUtils.class, dataProvider = "dpone")
	public void verifyArrearCutOffDays(Map<String, String> data)
	{
		
		testCaseLogger.get().assignAuthor("Habeeba");
		testCaseLogger.get().assignCategory(Constants.REGRESSION_CATEGORY);
		testCaseLogger.get().info("Test Cases covered as part of automation are : PR_TC_Arrear_103 to 105");
		if (!data.get("Runmode").equalsIgnoreCase("Y")) {
			testCaseLogger.get().skip("RunMode is set to No in test data");
			throw new SkipException("RunMode is set to No in test data");
		}
		printTestDataSet(data);
		navigateToBaseURL();
		testCaseLogger.get().info("Navigate to url");
		ArrearsPage pPage = new ArrearsPage().getPayrollPage();
		MenuNavigationPage mPage  = new MenuNavigationPage().getMenuNavigationPage();
		pPage.doPayrollLogin(data.get("Username"), data.get("Password"));
		pPage.switchToAdmin();
		
		mPage.navigateToMenu(data.get("Menu"));
		mPage.navigateToSubMenu(data.get("SubMenu"));
		pPage.validateFYMonth(data.get("FY"), data.get("Month"));
		pPage.addArrear(data);
		pPage.verifyExistingArrear();
		pPage.verifyEmptyTable(data);
		pPage.processCutOffDaysArrears(data);
		pPage.implicitWaitSec(3000);
	//	pPage.restrictionPreGeneratedPayslip();
	}
	
}
