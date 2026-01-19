package bluebeast.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bluebeast.abstractcomponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = "tr td:nth-child(3)")
		List<WebElement> productNames;
	
	By findBy = By.cssSelector("tr td:nth-child(3)");
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// waiting for the list elements to appear
		waitForElementToAppear(findBy);
	}
	
	public boolean verifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(x -> x.getText().equalsIgnoreCase(productName));
		return match;
	}
	
}
