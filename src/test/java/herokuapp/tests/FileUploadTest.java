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
import java.nio.file.Paths;

public class FileUploadTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    @Test(groups = {"regression"})
    public void uploadFileTest() {
        String filePath = Paths.get(System.getProperty("user.dir"), "src/test/resources/testfile.txt").toString();
        driver.findElement(By.id("file-upload")).sendKeys(filePath);
        driver.findElement(By.id("file-submit")).click();
        Assert.assertTrue(driver.findElement(By.tagName("h3")).getText().contains("File Uploaded!"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

