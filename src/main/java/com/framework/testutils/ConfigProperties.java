package com.framework.testutils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({
	"file:src\\test\\resources\\propertyFiles\\${environment}.properties"
})

public interface ConfigProperties extends Config{
	
	@Key("baseURI")
	String getBaseURI();
	
	@Key("basePath")
	String getBasePath();
	
	@Key("sheetName")
	String getSheetName();
	
	@Key("secretKey")
	String getSecretKey();
	
	@Key("invalidSecretKey")
	String getInvalidSecretKey();

   /**
    * 
    * Below properties are for configProperty reader	
    * 
    */
	
	@Key("url")
	String getUrl();

	@Key("browser")
	String getBrowser();
	
	@Key("SELENIUM_GRID")
	boolean isSeleniumGRID();
	
	@Key("server")
	String getServer();
	
	@Key("extentServer")
	String getExtentServer();
	
	@Key("executionEnvironment")
	String getExecutionEnv();
	
	@Key("extentX")
	boolean isExtentX();
	
	@Key("prodTestSheetName")
	String getProdTestSheetName();
	
	@Key("stageTestSheetName")
	String getStageTestSheetName();
	
	@Key("uatTestSheetName")
	String getUATTestSheetName();
	
	@Key("qaTestSheetName")
	String getQATestSheetName();
	
	@Key("Selenium_GRID_URL")
	String getSeleniumGridURL();

	@Key("execution")
	String isBase();
	
	@Key("isVisualReg")
	boolean isVisualReg();
	
	@Key("isPortable")
	boolean isPortable();
	
	@Key("viewportX")
	String getViewportX();
	
	@Key("viewportY")
	String getViewPortY();

	@Key("jmeter_prop")
	String getJmeterPath();
	
	@Key("jmeter_home")
	String getJmeterHome();
}
