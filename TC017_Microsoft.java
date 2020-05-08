package twentyOneDay;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC017_Microsoft {
//Evaluation
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();

		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("--disable-notifications");

		driver.get("https://azure.microsoft.com/en-in/");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, 30);

		//2) Click on Pricing
		driver.findElementByXPath("//a[text()='Pricing']").click();

		//3) Click on Pricing Calculator
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//a[@data-event='global-navigation-secondarynav-clicked-topmenu'])[2]")));
		driver.findElementByXPath("(//a[@data-event='global-navigation-secondarynav-clicked-topmenu'])[2]").click();


		//4) Click on Containers
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//button[text()='Containers']")));
		driver.findElementByXPath("//button[text()='Containers']").click();

		//5) Select Container Instances
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//span[text()='Easily run containers on Azure without managing servers'])[3]")));

		//Thread.sleep(3000);
		driver.findElementByXPath("(//span[text()='Easily run containers on Azure without managing servers'])[3]").click();

		//6) Click on Container Instance Added View
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='View']")));
		driver.findElementByXPath("//a[text()='View']").click();

		//7) Select Region as "South India"
		WebElement sortby = driver.findElementByName("region");
		Select sort = new Select(sortby);
		sort.selectByValue("south-india");

		//8) Set the Duration as 180000 seconds
		driver.findElementByName("seconds").clear();
		driver.findElementByName("seconds").sendKeys("80000");

		//9) Select the Memory as 4GB
		WebElement memory = driver.findElementByName("memory");
		Select sortmemory = new Select(memory);
		sortmemory.selectByValue("4");

		//10) Enable SHOW DEV/TEST PRICING
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();

		//11) Select Indian Rupee  as currency
		Thread.sleep(2000);
		WebElement currdrpdwn = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selcurr = new Select(currdrpdwn);
		selcurr.selectByValue("INR");

		//12) Print the Estimated monthly price
		String monthlyprice = driver.findElementByXPath("(//span[text()='Monthly cost']/parent::div/span[@class='numeric']/span)[1]").getText();
		System.out.println("Estimated Monthly Price is "+ monthlyprice);


		//13) Click on Export to download the estimate as excel
		driver.findElementByXPath("(//button[@type='button'])[36]").click();

		//14) Verify the downloded file in the local folder
		Thread.sleep(3000);
		File file = new File("C:\\Users\\kunch\\Downloads\\ExportedEstimate.xlsx");
		if(file.exists()){
			System.out.println("Estimate Downloaded successfully");
		}	else	{
			System.out.println("Estimate does not exists in the expected folder");
		}


		//15) Navigate to Example Scenarios and Select CI/CD for Containers
		Thread.sleep(3000);
		WebElement examplescenario = driver.findElementByXPath("//a[text()='Example Scenarios']");
		Actions builder = new Actions(driver);
		builder.moveToElement(examplescenario).click().build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();

		//16) Click Add to Estimate
		JavascriptExecutor js = (JavascriptExecutor) driver;	
		js.executeScript("window.scrollBy(0,500)");

		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='Add to estimate']").click();

		//17) Change the Currency as Indian Rupee
		Thread.sleep(8000);
		WebElement currdrpdwn2 = driver.findElementByXPath("//select[@class='select currency-dropdown']");
		Select selcurr2 = new Select(currdrpdwn2);
		selcurr2.selectByValue("INR");

		//18) Enable SHOW DEV/TEST PRICING
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Show Dev/Test Pricing']").click();

		//19) Export the Estimate
		driver.findElementByXPath("(//button[@type='button'])[40]").click();

		//20) Verify the downloded file in the local folder
		Thread.sleep(3000);
		File file1 = new File("C:\\Users\\kunch\\Downloads\\ExportedEstimate.xlsx");
		if(file1.exists()){
			System.out.println("CI/CD Estimate Downloaded successfully");
		}	else	{
			System.out.println("CI/CD Estimate does not exists in the expected folder");
		}
	}

}
