package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class BaseUI extends Base{
    private static final int MAX_WAIT_TIME = 30;
    WebDriver driver = null;
    Map<String,String> env = jsonFileToMap("config/env.conf.json");

    /**
     * The test is requesting for a specific browser
     * @param setBrowser
     */
    protected WebDriver initDriver(String... setBrowser){
        String browser = null;
        if(setBrowser.length>0){
            browser = setBrowser[0];
        }

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
        //context.setAttribute("driver",driver); //setting the test context driver variable to the driver
        return this.driver;
    }

    protected WebDriver getDriver(){
        return this.driver;
    }

    protected void setText(WebDriver driver, String locator, String value, String... params){
        if(params.length>0){
            locator = replaceParams(locator,params);
        }
        getElement(driver,locator).sendKeys(value);
        info("Setting text "+value+" to "+locator);
    }

    protected String getText(WebDriver driver, String locator, String... params){
        String text = null;
        if(params.length>0){
            locator = replaceParams(locator,params);
        }
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); //waiting for the
            WebElement element = getElement(driver,locator);
            text = element.getText();
        }catch (Exception ex){
            info("Element not found for getText()");
        }
        info("Extracted text "+text+" from "+locator);
        return text;
    }

    protected void click(WebDriver driver, String locator, String... params){
        if(params.length>0){
            locator = replaceParams(locator,params);
        }
        getElement(driver,locator).click();
        info("clicking on "+locator);
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        WebElement element;
        if(locator.contains("//")){
            element = (WebElement) fluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        }else{
            element = (WebElement) fluentWait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
        }
        return element;
    }

    private String replaceParams(String locator, String[] params) {
        for(String param : params){
            locator = locator.replaceFirst("<<param>>", param);
        }
        return locator;
    }

    private Wait fluentWait(WebDriver driver){
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_WAIT_TIME))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        return wait;
    }
}
