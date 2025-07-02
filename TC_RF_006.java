package tutorialsninja.register;

import java.util.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_006 {

    @Test
    public void registerTestWithNewsletterNo() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Step 1 & 2 - Navigate to registration page
        driver.findElement(By.cssSelector("a[title='My Account'] span[class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.cssSelector("a[href*='route=account/register']")).click();

        // Step 3 - Fill the registration form
        driver.findElement(By.id("input-firstname")).sendKeys("Mosharof");
        driver.findElement(By.id("input-lastname")).sendKeys("Hossain");
        driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("0123456789");
        driver.findElement(By.id("input-password")).sendKeys("Test1234@");
        driver.findElement(By.id("input-confirm")).sendKeys("Test1234@");

        // Step 4 - Select 'No' for Newsletter
        driver.findElement(By.xpath("//label[normalize-space()='No']//input[@name='newsletter']")).click();

        // Step 5 - Agree to Privacy Policy and click Continue
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("input[value='Continue']")).click();

        // ER-1 - Account Success Page
        Assert.assertTrue(driver.getTitle().contains("Your Account Has Been Created"), "Account Success Page Not Found");

        // Step 6 - Click Continue on success page
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();

        // ER-2 - Account page
        Assert.assertTrue(driver.getTitle().contains("My Account"), "User not redirected to Account page");

        // Step 7 - Subscribe / unsubscribe to newsletter
        driver.findElement(By.xpath("//a[normalize-space()='Subscribe / unsubscribe to newsletter']")).click();

        // ER-3 - Assert 'No' is selected
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Newsletter']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='0']")).isSelected(), "'No' option is not selected!");

        driver.quit();
    }

    public static String generateNewEmail() {
        return new Date().toString().replaceAll("\\s", "").replaceAll(":", "") + "@gmail.com";
    }
}
