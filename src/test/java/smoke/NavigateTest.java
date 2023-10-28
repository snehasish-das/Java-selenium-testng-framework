package smoke;

import core.BaseUI;
import org.testng.annotations.Test;

public class NavigateTest extends BaseUI {
    @Test(description = "Navigate to https://www.saucedemo.com/", groups = {"chrome"})
    public void launchChrome(){
        navigateTo("https://www.saucedemo.com/", "chrome");
    }

    @Test(description = "Navigate to https://www.saucedemo.com/ using firefox", groups = {"firefox"})
    public void launchFirefox(){
        navigateTo("https://www.saucedemo.com/");
    }
}
