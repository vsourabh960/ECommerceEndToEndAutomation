package bluebeast.stepDefinations;

import java.io.IOException;

import org.testng.Assert;

import bluebeast.pageobjects.CartPage;
import bluebeast.pageobjects.CheckOutPage;
import bluebeast.pageobjects.ConfirmationPage;
import bluebeast.pageobjects.LandingPage;
import bluebeast.pageobjects.ProductCatalogue;
import bluebeast.testcomponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce website")
	public void i_Landed_on_Ecommerce_Website() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("I logged in with email {string} and password {string}")
	public void i_Logged_In_With_Email_And_Password(String email, String password)
	{
		productCatalogue = landingPage.loginApplication(email, password);
	}
	
	@When("I added the product {string} to the cart")
	public void i_Added_The_Product_To_The_Cart(String productName)
	{
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@When("I proceeded to checkout {string} and submitted the order")
	public void i_Proceeded_To_Checkout_And_Submitted_The_Order(String productName)
	{
		cartPage = productCatalogue.goToCartPage();
		
		boolean match = cartPage.verifyProductDisplays(productName);
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
		
	}
	
	@Then("I should see the confirmation message {string}")
	public void i_Should_See_The_Confirmation_Message(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("I should see the error message {string}")
	public void i_should_see_the_error_message(String expectedError) {
	    // Error message capture aur assertion
	    String actualError = landingPage.getErrorMessage();
	    Assert.assertEquals(expectedError, actualError);
	    driver.close();
	}
}
