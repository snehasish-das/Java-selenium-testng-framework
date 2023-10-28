package smoke;

import core.BaseUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.landing.HomePage;

public class LoginTest extends BaseUI {
    @Test(description = "", groups = {"chrome"})
    public void loginTest(){
        WebDriver driver = initDriver("chrome");
        HomePage homePage = new HomePage(driver);
        homePage
                .navigateTo(driver,"https://www.saucedemo.com/")
                    .login(driver,"standard_user")
                ;
    }
}
