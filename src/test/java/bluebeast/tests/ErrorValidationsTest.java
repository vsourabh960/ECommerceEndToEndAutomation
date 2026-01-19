package bluebeast.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import bluebeast.pageobjects.CartPage;
import bluebeast.pageobjects.ProductCatalogue;
import bluebeast.testcomponents.BaseTest;
import bluebeast.testcomponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	@Test(groups={"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException
	{
		
		landingPage.loginApplication("gaura24@gmail.com", "Gaurav3@");
		// cssSelector = [aria-label*='Incorrect']
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void productErrorValidation() throws IOException
	{
		String productName = "ZARA COAT 3";
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("gaurav324@gmail.com", "Gaurav324@");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		boolean match = cartPage.verifyProductDisplays("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
