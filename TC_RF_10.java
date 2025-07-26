package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_010 {

    @Test
    public void registerWithInvalidEmail() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Step 1 & 2 - Navigate to Register page
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // Step 3 - Fill valid details except email
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Mosharof");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Hossain");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("amotoori@");  // Invalid email
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Mosharof1@");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Mosharof1@");

        // Step 3 cont. - Newsletter and Privacy Policy
        driver.findElement(By.xpath("//label[normalize-space()='No']//input[@name='newsletter']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).click();

        // Step 5 - Click Continue
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // ER-1 - Email warning message should appear
        boolean isWarningDisplayed = driver.findElements(
                By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid') or contains(@class,'alert-dismissible')]")
        ).size() > 0;

        Assert.assertTrue(isWarningDisplayed, "Expected email validation warning not displayed for invalid email!");

        driver.quit();
    }
}
