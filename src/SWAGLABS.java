import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SWAGLABS {
	
	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void myBeforeTest() {
		driver.get("https://www.saucedemo.com/");
	}

	@Test(enabled = false)
	public void test1() throws InterruptedException {
		//Login
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.name("password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("[data-test='login-button']")).click();

		// Add All Item
		List<WebElement> Addtocartbutton = driver.findElements(By.className("btn"));
		for (int i = 0; i < Addtocartbutton.size(); i++) {
			Addtocartbutton.get(i).click();
		}

		// Checkout
		Thread.sleep(2000);
		driver.findElement(By.className("shopping_cart_badge")).click();
		//wait
		Thread.sleep(2000);
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Automation");
		driver.findElement(By.id("last-name")).sendKeys("Tester");
		driver.findElement(By.id("postal-code")).sendKeys("0000");
		Thread.sleep(2000);
		driver.findElement(By.id("continue")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("finish")).click();

		// Assertion msg
		String Actual = driver.findElement(By.className("complete-header")).getText();
		boolean containthankyou = Actual.contains("Thank you");
		// SoftAssert
		SoftAssert Assert = new SoftAssert();
		Assert.assertEquals(Actual, "Thank you for your order!");
		Assert.assertEquals(containthankyou, true);
		Assert.assertAll();

		// HardAssert
//		Assert.assertEquals(Actual,"Thank you for your order!");
	}

	@Test()
	public void test2() {

		// login
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.name("password")).sendKeys("secret_sauce");
		driver.findElement(By.cssSelector("[data-test='login-button']")).click();

		// Add All Item
		List<WebElement> Addtocartbutton = driver.findElements(By.className("btn"));
		for (int i = 0; i < Addtocartbutton.size(); i++) {
			Addtocartbutton.get(i).click();
		}

		// Checkout
		driver.findElement(By.className("shopping_cart_badge")).click();
		driver.findElement(By.id("checkout")).click();
		driver.findElement(By.id("first-name")).sendKeys("Automation");
		driver.findElement(By.id("last-name")).sendKeys("Tester");
		driver.findElement(By.id("postal-code")).sendKeys("0000");
		driver.findElement(By.id("continue")).click();

		// Assertion total
		String total = driver.findElement(By.className("summary_total_label")).getText();

		// A way 1
		String[] newtotal = total.split("\\$");
		Assert.assertEquals(newtotal[1], "140.34");

		// A Way 2
		// String newtotal = total.substring(8);
		// Assert.assertEquals(newtotal, "140.34");

		// Assertion url
		String ActualURL = driver.getCurrentUrl();
		String ExpectedURL = "https://www.saucedemo.com/checkout-step-two.html";
		Assert.assertEquals(ActualURL, ExpectedURL);
	}

	@AfterTest
	public void myAfterTest() {

	}
}