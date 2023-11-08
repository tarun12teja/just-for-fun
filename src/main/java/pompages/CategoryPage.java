package pompages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CategoryPage {
	
	//declaration
	@FindBy(xpath = "//h1[normalize-space(text())='Category'] ")
	private WebElement pageHeader;
	
	@FindBy(xpath = "//a[text()=' New']")
	private WebElement newButton;
	
	@FindBy(xpath = "//table/tbody/tr/td[1]")
	private List<WebElement> categoryList;
	
	private String deletePath = "//td[text()=%s]//following-sibling::td/button[text()=' Delete']";
	
	@FindBy(xpath ="//h4[text()=' Success!']")
	private WebElement sucessMessage;
	
	@FindBy(xpath = "//button[@name='delete']")
	private WebElement deleteButton;
	
	//Initialization
	public CategoryPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public String getPageHeader()
	{
		return pageHeader.getText();
	}

	public void clickNewButton()
	{
		newButton.click();
	}
	
	public List<WebElement> getCategoryList()
	{
		return categoryList;
	}
	public String getSuccessMesage()
	{
		return sucessMessage.getText();
	}
	
	public void ClickDeleteButton(WebDriver driver,String coursenName)
	{
		String requiredPath=String.format(deletePath, coursenName);
		driver.findElement(By.xpath(requiredPath)).click();
	}
	
	public void ClickDelete()
	{
		deleteButton.click();
	}
}