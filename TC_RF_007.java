package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_007 {

    @Test
    public void verifyDifferentWaysOfNavigatingToRegisterAccountPage() {
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        // Step 1 & 2 - Click on 'My Account' Drop menu and Click on 'Register' option (ER-1)
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Register']")).click();

        // Validate Register Page only once
        Assert.assertTrue(driver.getTitle().contains("Register Account"), "User is not redirected to Register Account page.");

        // Step 3 & 4 - Click on 'My Account' Drop menu and 'Login' option
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // Step 5 - Click on 'Continue' button inside 'New Customer' box (ER-1)
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // Validate Register Page again (No need to repeat registration)
        Assert.assertTrue(driver.getTitle().contains("Register Account"), "User is not redirected to Register Account page.");

        // Step 6 - Repeat Steps 3 & 4 again
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        // Step 7 - Click on 'Register' option from Right Column options (ER-1)
        driver.findElement(By.xpath("//a[text()='Register'][@class='list-group-item']")).click();

        // Final Assert - Verify Register Account page is displayed
        Assert.assertTrue(driver.getTitle().contains("Register Account"), "User is not redirected to Register Account page.");

        driver.quit();
    }
}
