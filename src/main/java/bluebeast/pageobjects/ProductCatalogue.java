package bluebeast.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bluebeast.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
		List<WebElement> products;
	@FindBy(css = ".ng-animating")
		WebElement spinner;
	@FindBy(css = ".card-body button:last-of-type")
		WebElement addToCartButton;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() 
	{
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getProductList().stream().filter(product -> 
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		return prod;
	}
	
	public void addProductToCart(String productName)
	{
		WebElement prod = getProductByName(productName);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", prod);
		
		try {
			waitForElementToBeClickable(addToCart);
			prod.findElement(addToCart).click();
		}catch(Exception e) {
			js.executeScript("arguments[0].click", addToCartButton);	
		}
		
		
		// wait for the toast to appear
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
