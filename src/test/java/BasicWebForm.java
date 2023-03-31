import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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
