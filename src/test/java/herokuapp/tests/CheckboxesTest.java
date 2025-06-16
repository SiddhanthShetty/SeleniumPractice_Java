package herokuapp.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxesTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test(groups = {"smoke", "regression"})
    public void checkFirstCheckbox() {
        driver.findElements(By.cssSelector("input[type='checkbox']")).get(0).click();
        Assert.assertTrue(driver.findElements(By.cssSelector("input[type='checkbox']")).get(0).isSelected());
    }

    @Test(groups = {"regression"})
    public void uncheckSecondCheckbox() {
        driver.findElements(By.cssSelector("input[type='checkbox']")).get(1).click();
        Assert.assertFalse(driver.findElements(By.cssSelector("input[type='checkbox']")).get(1).isSelected());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

