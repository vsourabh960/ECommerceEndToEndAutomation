package bluebeast.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import bluebeast.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	Actions actions;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);	
		this.actions = new Actions(driver);
	}
	
	@FindBy(css = "[placeholder='Select Country']")
		WebElement country;
	@FindBy(css = ".ta-item:nth-of-type(2)") // xpath -> //button[contains(@class, ta-item)][2]
		WebElement selCountry;
	@FindBy(css = ".action__submit")
		WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		actions.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(results);
		selCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		return new ConfirmationPage(driver);
	}
}
