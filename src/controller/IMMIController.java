package controller;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class IMMIController {
    ChromeDriver driver;
    WebDriverWait wait;

    IMMIController() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
    }

    public boolean run_bot(String username, String password) {
        try {
            driver = new ChromeDriver();
            driver.manage().window().setSize(new Dimension(1000, 1000));
            driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 1, 200);

            driver.get("https://online.immi.gov.au/lusc/login");
            while (driver.findElements(By.xpath("//button[contains(.,'Login')]")).isEmpty()) {
                driver.get("https://online.immi.gov.au/lusc/login");
            }

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
            driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='wc-cell']//button[contains(.,'Next')]")));
            driver.findElement(By.xpath("//button[contains(.,'Continue')]")).click();

            return true;
        } catch (Exception e) {
            driver.quit();
            setNull();
            return false;
        }
    }

    public void setNull() {
        driver = null;
        wait = null;
    }

    public void KillChromeDriver() {
        try {
            Runtime.getRuntime().exec("taskkill -f -im chromedriver.exe");
        } catch (Exception ignored) { }
    }

    public static void main(String[] args) {
        IMMIController immiController = new IMMIController();
        immiController.KillChromeDriver();
        boolean run = immiController.run_bot("dinadir863@terkoer.com", "dinadir863@terkoer.com.VN@");
        while(!run) {
            run = immiController.run_bot("dinadir863@terkoer.com", "dinadir863@terkoer.com.VN@");
        }
    }
}
