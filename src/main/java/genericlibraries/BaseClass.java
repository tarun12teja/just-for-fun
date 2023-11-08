package genericlibraries;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import pompages.AddCategoryPage;
import pompages.AddNewUser;
import pompages.AddnewCourse;
import pompages.AdminHomePage;
import pompages.CategoryPage;
import pompages.CourseListPage;
import pompages.LoginPage;
import pompages.UserPage;
import pompages.WelcomePage;

public class BaseClass {
	//@BeforeSuite
	//@BeforeTest
	protected PropertiesUtility property;
	protected ExcelUtility excel;
	protected WebDriverUtitlity webUtil;
	protected WebDriver driver;//WEbdriver is a non static variable
	protected javaUtility jutil;
	
	public static WebDriver sdriver;//this is created because we need to use this in lisiners because lisiners thread is different for that we are creating this satic methods
	public static javaUtility sjutil;
	
	
	protected WelcomePage welcome;
	protected LoginPage login;
	protected AdminHomePage home;
	protected UserPage users;
	
	protected CategoryPage category;
	protected CourseListPage course;
	protected AddNewUser addUser;
	protected AddnewCourse addcourse;
	protected AddCategoryPage addcategory;
	
	
	
	@BeforeClass
	public void classConfig()
	{
		property= new PropertiesUtility();
		excel= new ExcelUtility();
		jutil = new javaUtility();
		webUtil = new WebDriverUtitlity();
		
		property.propertiesIntilization(IConstantpath.propertiesPath);
		driver = webUtil.lunchBrowser(property.readFromProperties("browser"));
		sdriver=driver;
		sjutil=jutil;
		
	}
	
	@BeforeMethod
	public void methodConfig() 
	{
		excel.excelInitilization(IConstantpath.excelPath);
		
		welcome = new WelcomePage(driver);
		login = new LoginPage(driver);
		home = new AdminHomePage(driver);
		course = new CourseListPage(driver);
		category = new CategoryPage(driver);
		addUser = new AddNewUser(driver);
		addcourse = new AddnewCourse(driver);
		addcategory = new AddCategoryPage(driver);
		
		webUtil.navigateToApp(property.readFromProperties("url"));
		
		long time = Long.parseLong(property.readFromProperties("timeouts"));
		
		webUtil.waitUntilElementFound(time);
		
		welcome.clickLoginButton();
		login.setEmail(property.readFromProperties("username"));
		login.setPassword(property.readFromProperties("password"));
		login.clickLogin();
	}
	
	@AfterMethod
	public void methodTeardown()
	{
		excel.CloseExcel();
		home.singOutApp();
	}
	
	@AfterClass
	public void classTeardown()
	{
		webUtil.closeAllWindow();
	}
	//@AfterTest
	//@AfterSuite

}