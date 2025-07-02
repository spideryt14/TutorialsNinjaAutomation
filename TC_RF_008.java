package tutorialsninja.register;

import java.util.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_008 {

    @Test
    public void registerWithMismatchedPasswords() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Step 1 & 2 - Navigate to Register page
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // Step 3 - Fill required fields
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Mosharof");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Hossain");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(generateNewEmail());
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("0123456789");

        // Step 4 & 5 - Enter different passwords
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("abcde");

        // Step 3 cont. - Newsletter and Privacy Policy
        driver.findElement(By.xpath("//label[normalize-space()='No']//input[@name='newsletter']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).click();

        // Step 6 - Click on Continue button
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // ER-1 - Validate warning message
        String warning = driver.findElement(By.xpath("//div[contains(text(),'Password confirmation does not match password')]")).getText();
        Assert.assertTrue(warning.contains("Password confirmation does not match password!"), "Expected warning message not displayed.");

        driver.quit();
    }

    public static String generateNewEmail() {
        return new Date().toString().replaceAll("\\s", "").replaceAll(":", "") + "@gmail.com";
    }
}
