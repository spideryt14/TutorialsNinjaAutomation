package tutorialsninja.register;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_011 {

    @Test
    public void registerWithInvalidPhoneNumber() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Step 1 & 2 - Navigate to Register page
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // Step 3 - Fill required valid fields
        driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Mosharof");
        driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Hossain");
        driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(generateNewEmail());

        // Step 4 - Enter invalid phone number (Example: "111")
        driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("abcdehbbrib3456765");

        // Step 3 cont. - Password and other fields
        driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("Mosharof1@");
        driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("Mosharof1@");
        driver.findElement(By.xpath("//label[normalize-space()='No']//input[@name='newsletter']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).click();

        // Step 5 - Click Continue
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // ER-1 - Check if a warning appears for invalid phone number
        boolean isWarningDisplayed = driver.findElements(
                By.xpath("//div[contains(@class,'alert-dismissible')] | //input[@id='input-telephone' and contains(@class,'is-invalid')]"))
                .size() > 0;

        Assert.assertTrue(isWarningDisplayed, "Expected warning for invalid phone number not displayed!");

        driver.quit();
    }

    public static String generateNewEmail() {
        return new Date().toString().replaceAll("\\s", "").replaceAll(":", "") + "@gmail.com";
    }
}
