package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BaseUI extends Base{
    WebDriver driver = null;
    Map<String,String> env = jsonFileToMap("config/env.conf.json");
    /**
     * Used to instantiate the browser and navigate to a given url
     * @param browser
     * @param url
     * @return
     */
    public WebDriver navigateTo(String url, String... browser){
        if(browser.length>0){
            this.driver = this.getDriver(browser[0]);
        }
        else {
            this.driver = this.getDriver(null);
        }
        this.driver.navigate().to(url);
        return this.driver;
    }

    /**
     * The test is requesting for a specific browser
     * @param browser
     */
    private WebDriver getDriver(String browser){

        if(System.getProperty("browser") != null){
            browser = System.getProperty("browser");
        }

        ArrayList<String> optionsList = new ArrayList<>(
                Arrays.asList(
                        "--disable-extensions",
                        "--disable-gpu",
                        "--no-sandbox",
                        //"--headless",
                        "--disable-dev-shm-usage",
                        "--window-size=1920,1040"
                )
        );

        if(browser!=null && browser.toUpperCase().equals("CHROME")){
            info("Initiating chrome...");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            for(String option : optionsList){
                options.addArguments(option);
            }
            this.driver = new ChromeDriver(options);
        }
        else{
            info("Initiating firefox...");
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            for(String option : optionsList){
                options.addArguments(option);
            }
            this.driver = new FirefoxDriver(options);
        }
        this.driver.manage().window().maximize();
        return this.driver;
    }

    @AfterMethod
    public void tearDown(ITestContext context){
        this.driver.close();
    }
}
