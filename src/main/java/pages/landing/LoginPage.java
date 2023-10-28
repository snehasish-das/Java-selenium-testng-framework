package pages.landing;

import core.BaseUI;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class LoginPage<T> extends BaseUI {
    private Map locatorMap = jsonFileToMap("locators/landing/LoginPageLocators.json");
    private static WebDriver driver;
    private T originPage;
    public LoginPage(WebDriver driver, T originPage) {
        this.driver = driver;
        this.originPage = originPage;
    }

    public LoginPage<T> login(WebDriver driver, String userkey){
        Map userCreds = jsonFileToMap("config/users.conf.json");
        Map<String,String> userDetails = (Map) userCreds.get(userkey);
        String username = userDetails.get("username");
        String password = userDetails.get("password");

        setText(driver, locatorMap.get("usernameInput").toString(), username);
        setText(driver, locatorMap.get("passwordInput").toString(), password);
        click(driver, locatorMap.get("loginButton").toString());

        return this;
    }

}
