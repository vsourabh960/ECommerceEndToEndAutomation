package bluebeast.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import bluebeast.abstractcomponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent{

	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
		WebElement confirmationMessage;

	public String getConfirmationMessage()
	{
		// wait for the confirmation message to appear before fetching text
		waitForWebElementToAppear(confirmationMessage);
		return confirmationMessage.getText();
	}

}