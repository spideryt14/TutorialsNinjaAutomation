package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_009 {

    @Test
    public void registerWithExistingEmail() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Step 1 & 2 - Navigate to Register page
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // Step 3 - Enter existing account details
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Mosharof Hossain");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Shrabon");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys("akibmahmud870@gmail.com");
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("966560665201");
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Mosharof1@");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Mosharof1@");

        // Newsletter and Privacy Policy
        driver.findElement(By.xpath("//label[normalize-space()='No']//input[@name='newsletter']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).click();

        // Step 4 - Click on Continue button
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // ER-1 - Validate warning message for existing email
        String warningMsg = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]"))
                .getText();
        Assert.assertTrue(warningMsg.contains("Warning: E-Mail Address is already registered!"),
                "Expected warning for existing email not displayed.");

        driver.quit();
    }
}
