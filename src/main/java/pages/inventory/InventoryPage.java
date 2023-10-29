package pages.inventory;

import core.BaseUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;
import java.util.NoSuchElementException;

public class InventoryPage<T> extends BaseUI {
    private Map locatorMap = jsonFileToMap("locators/inventory/InventoryPageLocators.json");
    private static WebDriver driver;
    private T originPage;
    public InventoryPage(WebDriver driver, T originPage) {
        this.driver = driver;
        this.originPage = originPage;
    }

    public InventoryPage<T> addProductToCart(WebDriver driver, String productName){
        int initialValue = 0;
        try{
            initialValue = Integer.parseInt(getText(driver,locatorMap.get("shoppingCartItems").toString()));
        }catch (Exception ex){

        }
        click(driver, locatorMap.get("productButton").toString(),productName);
        int cartItems = Integer.parseInt(getText(driver,locatorMap.get("shoppingCartItems").toString()));

        //Assertions for to confirm the add to cart action
        Assert.assertEquals(getText(driver,locatorMap.get("productButton").toString(),productName), "Remove","Product button text must be remove");
        Assert.assertEquals(cartItems, initialValue+1,"Item not added to cart successfully");

        return this;
    }

}
