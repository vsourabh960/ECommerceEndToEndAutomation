package bluebeast.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bluebeast.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	
	//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
		List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button")
		WebElement checkOutEle;
	@FindBy(css = ".cartSection h3")
		WebElement cartProduct;
	
	By checkOutBy = By.cssSelector(".totalRow button");
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public Boolean verifyProductDisplays(String productName)
	{
		waitForWebElementToAppear(cartProduct);
		Boolean match = cartProducts.stream().anyMatch(x -> x.getText().equalsIgnoreCase(productName));
		return match;
	} 
	
	public CheckOutPage goToCheckOut()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", checkOutEle);
		waitForElementToBeClickable(checkOutBy);
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
	
}
