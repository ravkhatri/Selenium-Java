import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class BasicWebForm {

    ChromeDriver driver;
    @BeforeMethod
    public void before() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
//        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        Thread.sleep(1000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test() {
        try {

            System.out.println("Test");
            driver.findElement(By.xpath("//input[@name='my-text']")).sendKeys("ravik");
            Thread.sleep(500);

            driver.findElement(By.xpath("//input[@name='my-password']")).sendKeys("12345");
            Thread.sleep(500);

            System.out.println("Enablement: "+driver.findElement(By.xpath("//input[@name='my-disabled']")).isEnabled());
            Thread.sleep(500);

            System.out.println("Get text: "+ driver.findElement(By.xpath("//input[@name='my-readonly']")).getAttribute("value"));
            Thread.sleep(500);

            Select dd = new Select(driver.findElement(By.xpath("//select[@name='my-select']")));
            dd.selectByValue("2");
            Thread.sleep(500);

            driver.findElement(By.xpath("//input[@name='my-datalist']")).click();
            Thread.sleep(500);

            driver.findElement(By.xpath("//input[@name='my-datalist']")).sendKeys("Chicago");
            Thread.sleep(1000);

            driver.findElement(By.xpath("//input[@id='my-check-2']")).click();
            Thread.sleep(1000);

            JavascriptExecutor js = (JavascriptExecutor)driver;
            js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@id='my-radio-2']")));

            Thread.sleep(500);


            Color black = Color.fromString("white");
//			System.out.println("Color Hex: "+BLACK.asHex());
//			System.out.println("Color RGB: "+BLACK.asRgb());
//			System.out.println("Color RGBA: "+BLACK.asRgba());

            Color col = Color.fromString(driver.findElement(By.xpath("//input[@name='my-colors']")).getCssValue("color"));
            Color bcol = Color.fromString(driver.findElement(By.xpath("//input[@name='my-colors']")).getCssValue("background-color"));

            System.out.println("Color: "+col);
            System.out.println("BColor: "+bcol);

            System.out.println("ColorRGB: "+col.asRgb());
            System.out.println("BColorRGB: "+bcol.asRgb());

            System.out.println("ColorHEX: "+col.asHex());
            System.out.println("BColorHEX: "+bcol.asHex());

            driver.findElement(By.xpath("//input[@name='my-date']")).click();
            Thread.sleep(1000);
            //To-Do
            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("(//div[@class='datepicker-days']//tr)[2]//th[@class='datepicker-switch']")));
            driver.findElement(By.xpath("(//div[@class='datepicker-days']//tr)[2]//th[@class='datepicker-switch']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("(//div[@class='datepicker-months']//tr)[2]//th[@class='datepicker-switch']")));
            driver.findElement(By.xpath("(//div[@class='datepicker-months']//tr)[2]//th[@class='datepicker-switch']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.
                    xpath("(//div[@class='datepicker-years']//tr)[2]//th[@class='datepicker-switch']")));
            driver.findElement(By.
                    xpath("(//div[@class='datepicker-years']//tr)[2]//th[@class='datepicker-switch']/../../..//*[.='2023']")).click();
            Thread.sleep(1000);
            driver.findElement(By.
                    xpath("((//div[@class='datepicker-months']//tr)[2]//th[@class='datepicker-switch'])/../../..//*[.='Mar']")).click();

            Thread.sleep(1000);
            driver.findElement(By.
                    xpath("((//div[@class='datepicker-days']//tr)[2]//th[@class='datepicker-switch'])/../../..//*[.='31']")).click();
            Thread.sleep(10000);

            WebElement slider = driver.findElement(By.xpath("//input[@name='my-range']"));

            Actions builder = new Actions(driver);
            builder.moveToElement(slider)
                    .click()
                    .dragAndDropBy(slider,30, 0)
                    .build()
                    .perform();

            Thread.sleep(1000);
            for (int counter=0; counter<3; counter++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
                Thread.sleep(1000);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @AfterMethod
    public void after() {
        driver.quit();
        System.out.println("After");
    }
}
