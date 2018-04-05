package pages;

import framework.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Browser.properties;

public class ManagePage extends BasePage {

    WebDriver driver = Browser.getInstance();

    @FindBy(xpath = "//div[@class='manage-option']/a[@href='securityRealm/']")
    private WebElement manageUsersButton;
    @FindBy(xpath = "//div[@class='manage-option']/a[@href='securityRealm/']//dt[contains(text(),'Управление пользователями')]")
    private WebElement testField1;
    @FindBy(xpath = "//div[@class='manage-option']/a[@href='securityRealm/']//dd[contains(text(),'Создание, удаление и модификция пользователей, имеющих право доступа к Jenkins')]")
    private WebElement testField2;

    public WebElement getManageUsersButton() {
        return manageUsersButton;
    }

    public WebElement getTestField1() {
        return testField1;
    }

    public WebElement getTestField2() {
        return testField2;
    }

    public ManagePage(WebDriver driver) {
        // проверить, что вы находитесь на верной странице
        if (!driver.getCurrentUrl().equalsIgnoreCase(properties.getParameter("Url") + "/manage")) {
            throw new IllegalStateException("This is not [" + ManagePage.class + "] the page you are expected");
        }
    }
}