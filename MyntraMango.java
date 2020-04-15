package dayOne;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.google.common.collect.Table;

public class MyntraMango {

	public static void main(String[] args) throws InterruptedException {
		//disable notification
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		//1) Open https://www.myntra.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(options);	
		driver.get("https://www.myntra.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


		//2) Mouse over on WOMEN (Actions -> moveToElement
		WebElement Women = driver.findElementByXPath("//a[text()='Women']");
		Actions builder = new Actions(driver);
		builder.moveToElement(Women).perform();

		//3) Click Jackets & Coats
		WebElement Jc = driver.findElementByXPath("//a[text()='Jackets & Coats']");
		Thread.sleep(2000);
		builder.click(Jc).perform();

		//4) Find the total count of item (top) -> getText -> String
		//String str = driver.findElementByClassName("title-count").getText();
		//split, 
		// String text = str.replaceAll("\\D","") -> String
		// Integer.parseInt(text) -> int
		String str = driver.findElementByClassName("title-count").getText();
		// split, 
		String text = str.replaceAll("\\D",""); //-> String
		int totaljc = Integer.parseInt(text); //-> int
		//System.out.println(text); 

		//	5) Validate the sum of categories count matches	
		String jacketscount = driver.findElementByXPath("(//span[@class='categories-num'])[1]").getText();
		String jcount = jacketscount.replaceAll("\\D",""); //-> String
		int totalj = Integer.parseInt(jcount); //-> int
		String coatscount = driver.findElementByXPath("(//span[@class='categories-num'])[2]").getText();
		String ccount = coatscount.replaceAll("\\D","");  //-> String
		int totalc = Integer.parseInt(ccount); //-> int

		int  Totalcountverification= totalj  +  totalc;
		System.out.println("Total count of Jackets and coats are "+Totalcountverification);

		if(Totalcountverification==totaljc)		 {
			System.out.println("Total count is matching"); 
		}
		else {
			System.out.println("Total count Mismatches");
		}

		//6) Check Coats
		driver.findElementByXPath("(//div[@class='common-checkboxIndicator'])[2]").click();
		//7) Click + More option under BRAND	
		Thread.sleep(2000);
		driver.findElementByXPath("//div[@class='brand-more']").click();
		//8) Type MANGO and click checkbox
		Thread.sleep(2000);
		driver.findElementByXPath("//input[@class= 'FilterDirectory-searchInput']").sendKeys("MANGO");
		driver.findElementByXPath("(//label[text()='MANGO'])[2]").click();
		//9) Close the pop-up x
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		//10) Confirm all the Coats are of brand MANGO
		//findElements (brand) -> List<WebElement> 
		//foreach -> getText of each brand 
		//compare > if(condition)
		//List <WebElement> brands = driver.findElementsByClassName("product-brand");

		//List <WebElement> brands = driver.findElementsByXPath("//h3[@class='product-brand']");
		//int brandcount =0;
		Thread.sleep(5000);
		List<WebElement> brands = driver.findElementsByXPath("//h3[@class='product-brand']"); 
		int brandCount = 0; 
		for (WebElement eachProduct : brands) {
			if(eachProduct.getText().equals("MANGO")) {
				brandCount = brandCount + 1; 
			}
		}
		if(brandCount == brands.size()) {
			System.out.println("All displayed products are from MANGO brand.");
		} else {
			System.out.println("Displaying brands other than MANGO");

		}
		//11) Sort by Better Discount
		builder.moveToElement(driver.findElementByClassName("sort-sortBy")).build().perform(); 
		driver.findElementByXPath("//label[text()='Better Discount']").click(); 

		//12) Find the price of first displayed item
		//findElements (price) -> List<WebElement> 
		//get(0) -> WebElement -> getText -> String -> int
		Thread.sleep(2000);
		List<WebElement> Price =driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		System.out.println("Price of first displayed item is " + Price.get(0).getText());
		//13) Mouse over on size of the first item
		builder.moveToElement(driver.findElementByClassName("product-productMetaInfo")).build().perform(); 
		//14) Click on WishList Now
		driver.findElementByXPath("(//span[text()='wishlist now'])[1]").click(); 
		//15) Close Browser
		driver.close();
	}



}
