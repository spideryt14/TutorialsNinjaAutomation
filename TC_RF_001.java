package tutorialsninja.register;

import java.util.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_001 {

    @Test
    public void registerTest() {
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Navigate to registration page
        driver.findElement(By.cssSelector("a[title='My Account'] span[class='hidden-xs hidden-sm hidden-md']")).click();
        driver.findElement(By.cssSelector("a[href*='route=account/register']")).click();

        // Fill the form
        driver.findElement(By.id("input-firstname")).sendKeys("Mosahrof Hossain");
        driver.findElement(By.id("input-lastname")).sendKeys("Shrabon");
        driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("+966560665201");
        driver.findElement(By.id("input-password")).sendKeys("Mosharof1@");
        driver.findElement(By.id("input-confirm")).sendKeys("Mosharof1@");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.cssSelector("input[value='Continue']")).click();

        // Assert success
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed(), "Logout link is not displayed");

        // Capture page content after registration
        String actualProperDetails = driver.findElement(By.id("content")).getText();

        // Expected messages
        String expectedProperDetailsOne = "Your Account Has Been Created!";
        String expectedProperDetailsTwo = "Congratulations! Your new account has been successfully created!";
        String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String expectedProperDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
        String expectedProperDetailsSix = "contact us.";

        // Validate the content
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsSix));

        // Continue to account page
        driver.findElement(By.cssSelector("a.btn.btn-primary")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Edit account info link not found");

        driver.quit();
    }

    public static String generateNewEmail() {
        return new Date().toString().replaceAll("\\s", "").replaceAll(":", "") + "@gmail.com";
    }
}
