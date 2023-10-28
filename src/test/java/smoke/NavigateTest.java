package smoke;

import core.BaseUI;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import pages.landing.HomePage;

public class NavigateTest extends BaseUI {
    @Test(description = "Navigate to https://www.saucedemo.com/", groups = {"chrome"})
    public void launchChrome(){
        WebDriver driver = initDriver("chrome");
        HomePage homePage = new HomePage(driver);
        homePage
                .navigateTo(driver,"https://www.saucedemo.com/");
    }

    @Test(description = "Navigate to https://www.saucedemo.com/ using firefox", groups = {"firefox"})
    public void launchFirefox(){
        WebDriver driver = initDriver();
        HomePage homePage = new HomePage(driver);
        homePage
                .navigateTo(driver,"https://www.saucedemo.com/");
    }
}
