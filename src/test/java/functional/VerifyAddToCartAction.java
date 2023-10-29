package functional;

import core.BaseUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.landing.HomePage;

public class VerifyAddToCartAction extends BaseUI {
    @Test(description = "Add \"Sauce Labs Bike Light\" to the cart and verify", groups = {"InventoryPageTests"})
    public void loginTest(){
        WebDriver driver = initDriver("chrome");
        HomePage homePage = new HomePage(driver);
        homePage
                .navigateTo(driver,"https://www.saucedemo.com/")
                .login(driver,"standard_user")
                .addProductToCart(driver,"Sauce Labs Bike Light")
        ;
    }
}
