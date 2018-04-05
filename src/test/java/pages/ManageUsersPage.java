package pages;

import framework.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static framework.Browser.properties;

public class ManageUsersPage extends BasePage {

    WebDriver driver = Browser.getInstance();

    @FindBy(xpath = "//div[@id='side-panel']//div[@class='task']//a[@href='addUser']")
    private WebElement addUsersButton;
    @FindBy(xpath = "//table[@id='people']//tr//td//a[contains(text(),'someuser')]")
    private WebElement someUserTable;
    @FindBy(xpath = "//table[@id='people']//tr//td//a[@href='user/someuser/delete']")
    private WebElement someUserDeleteButton;
    @FindBy(xpath = "//table[@id='people']//a[@href='user/someuser/delete']")
    private WebElement adminDeleteButton;

    public WebElement getAddUsersButton() {
        return addUsersButton;
    }

    public WebElement getSomeUserTable() {
        return someUserTable;
    }

    public WebElement getSomeUserDeleteButton() {
        return someUserDeleteButton;
    }

    public WebElement getAdminDeleteButton() {
        return adminDeleteButton;
    }

    public ManageUsersPage(WebDriver driver) {
        // проверить, что вы находитесь на верной странице
        if (!driver.getCurrentUrl().equalsIgnoreCase(properties.getParameter("Url") + "/securityRealm/")) {
            throw new IllegalStateException("This is not [" + ManageUsersPage.class + "] the page you are expected");
        }
    }
}

