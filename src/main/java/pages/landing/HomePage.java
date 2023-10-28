package pages.landing;

import core.BaseUI;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class HomePage<T> extends BaseUI {
    private Map<String,String> locators = jsonFileToMap("locators/landing/HomePageLocators.json");
    private static WebDriver driver;
    private T originPage=null;

    public HomePage(WebDriver driver){
        this.driver = driver;

    }
    /**
     * Used to instantiate the browser and navigate to a given url
     * @param driver
     * @param url
     * @return
     */
    public LoginPage<T> navigateTo(WebDriver driver, String url){
        driver.navigate().to(url);
        return (LoginPage<T>) new LoginPage<>(driver, this);
    }
}
