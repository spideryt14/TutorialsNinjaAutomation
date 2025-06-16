package tutorialsninja.register;

import java.util.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_004 {

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
        driver.findElement(By.id("input-telephone")).sendKeys("0000000000");
        driver.findElement(By.id("input-password")).sendKeys("Mosharof1@");
        driver.findElement(By.id("input-confirm")).sendKeys("Mosharof1@");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']")).click();
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Subscribe / unsubscribe to newsletter']")).click();
        
        
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Newsletter']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='1']")).isSelected());
        
        driver.quit();
        
        
 
    
    
    }
    
    
    public static String generateNewEmail() {
        return new Date().toString().replaceAll("\\s", "").replaceAll(":", "") + "@gmail.com";
}



}
