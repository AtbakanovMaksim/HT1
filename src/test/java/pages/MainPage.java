package pages;

import framework.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Browser.properties;

public class MainPage extends BasePage {

    WebDriver driver = Browser.getInstance();

    @FindBy(xpath = "//div[@class='task']/a[@href='/manage']")
    private WebElement manageButton;
    @FindBy(xpath = "//div[@id='right-top-nav']//a[contains(@href,'?auto_refresh')]")
    private WebElement autoRefreshButton;

    public WebElement getManageButton() {
        return manageButton;
    }

    public WebElement getAutoRefreshButton() {
        return autoRefreshButton;
    }

    public MainPage(WebDriver driver) {
        // проверить, что вы находитесь на верной странице
        if (!driver.getCurrentUrl().equalsIgnoreCase(properties.getParameter("Url") + "/")) {
            throw new IllegalStateException("This is not [" + MainPage.class + "] the page you are expected");
        }
    }

    public MainPage enableAutoRefresh() {
        if (autoRefreshButton.getText().equals("Включить автообновление страниц")) {
            autoRefreshButton.click();
        }
        return this;
    }

    public MainPage disableAutoRefresh() {
        if (autoRefreshButton.getText().equals("Отключить автообновление страниц")) {
            autoRefreshButton.click();
        }
        return this;
    }
}
