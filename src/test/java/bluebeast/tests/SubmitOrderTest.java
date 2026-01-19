package bluebeast.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bluebeast.pageobjects.CartPage;
import bluebeast.pageobjects.CheckOutPage;
import bluebeast.pageobjects.ConfirmationPage;
import bluebeast.pageobjects.OrderPage;
import bluebeast.pageobjects.ProductCatalogue;
import bluebeast.testcomponents.BaseTest;
import bluebeast.testcomponents.Retry;

public class SubmitOrderTest extends BaseTest{
	
	String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\bluebeast\\data\\PurchaseOrder.json";
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups = {"purchase"}, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws IOException
	{ 
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		boolean match = cartPage.verifyProductDisplays(input.get("product"));
		Assert.assertTrue(match);
		
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		//String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		//System.out.println("Confirmation Message: " + confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
	}
	
	// Verify orders 
	// To verify ZARA COAT 3 is present in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyOrderHistory()
	{
		// "ZARA COAT 3"
		ProductCatalogue productCatalogue = landingPage.loginApplication("gaurav324@gmail.com", "Gaurav324@");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonDataToMap(filePath);
		Object[][] objData = new Object[data.size()][1];
		for(int i = 0; i < data.size(); i++)
		{
			objData[i][0] = data.get(i);
		}
		return objData;
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//	HashMap<String, String> map = new HashMap<>();
//	map.put("email", "gaurav324@gmail.com");
//	map.put("password", "Gaurav324@");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<>();
//	map1.put("email", "saurabh12342@gmail.com");
//	map1.put("password", "Rahul@1725");
//	map1.put("product", "ADIDAS ORIGINAL");
//		return new Object[][] {{}, {}};
//	}
}
