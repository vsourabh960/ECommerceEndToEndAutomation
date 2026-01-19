package bluebeast.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bluebeast.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// WebElement userEmail = driver.findElement(By.id("userEmail"));
	// driver.findElement(By.id("userPassword"))
	//PageFactory
	@FindBy(id = "userEmail")
		WebElement userEmail;
	
	@FindBy(id = "userPassword")
		WebElement password;
	
	@FindBy(id = "login")
		WebElement submit;
	
	@FindBy(css = "[aria-label*='Incorrect']")
		WebElement errorMessage;
	
	By sub = By.id("login");
	
	public ProductCatalogue loginApplication(String email, String pass) 
	{
		userEmail.sendKeys(email);
		password.sendKeys(pass);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submit);
		waitForElementToBeClickable(sub).click();
		//submit.click();
		return new ProductCatalogue(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/dashboard/dash");
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
