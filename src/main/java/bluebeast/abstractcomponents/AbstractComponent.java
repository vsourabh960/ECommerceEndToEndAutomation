package bluebeast.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import bluebeast.pageobjects.CartPage;
import bluebeast.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions actions;
	
	@FindBy(css = "[routerlink*='myorders']")
		WebElement orderHeader;
	@FindBy(css = "[routerlink*='cart']")
		WebElement cart;
	
	By order = By.cssSelector("[routerlink*='myorders']");
	By cartHeader = By.cssSelector("[routerlink*='cart']");
	// Spinner/overlay used by the application (ngx-spinner)
	By spinnerOverlay = By.cssSelector(".ngx-spinner-overlay");
	
	public AbstractComponent(WebDriver driver) 
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.actions = new Actions(driver);
		PageFactory.initElements(driver, this);
	}

	public void waitForElementToAppear(By findBy) 
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public WebElement waitForElementToBeClickable(By findBy)
	{
		return wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForPresenceOfElement(By findBy)
	{
		wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
	}
	
	public CartPage goToCartPage()
	{
		waitForPresenceOfElement(cartHeader);
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerOverlay));
			} catch (Exception e) {
				// ignore - if spinner not present, continue
			}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", cart);
		try {
			waitForElementToBeClickable(cartHeader);
			cart.click();
		} catch (Exception e) {
			// JS Click coordinates ki parwah nahi karta aur interception bypass kar deta hai
			js.executeScript("arguments[0].click();", cart);
		}		
		return new CartPage(driver);
	}
	
	public OrderPage goToOrdersPage()
	{
		waitForWebElementToAppear(orderHeader);
		// Wait for spinner/overlay to disappear before interacting
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerOverlay));
		} catch (Exception e) {
			// ignore if not present
		}
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", orderHeader);
		try {
	        waitForElementToBeClickable(order);
	        orderHeader.click();
	    } catch (Exception e) {
	        // JS Click coordinates ki parwah nahi karta aur interception bypass kar deta hai
	        js.executeScript("arguments[0].click();", orderHeader);
	    }
		//orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
