package pages;

import framework.Browser;
import framework.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorisationPage extends BasePage {

    WebDriver driver = Browser.getInstance();

    @FindBy(xpath = "//div[@id='main-panel']//input[@id='j_username']")
    private WebElement loginForm;
    @FindBy(xpath = "//div[@id='main-panel']//input[@name='j_password']")
    private WebElement passwordForm;
    @FindBy(xpath = "//div[@id='main-panel']//span[@id='yui-gen1']//button")
    private WebElement confirmButton;

    public WebElement getLoginForm() {
        return loginForm;
    }

    public WebElement getPasswordForm() {
        return passwordForm;
    }

    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public AuthorisationPage(WebDriver driver) {
        // проверить, что вы находитесь на верной странице
        if (!driver.getCurrentUrl().contains("login")) {
            throw new IllegalStateException("This is not [" + AuthorisationPage.class + "] the page you are expected");
        }
    }

    public AuthorisationPage authorisationSuccess(User user) {
        loginForm.clear();
        loginForm.sendKeys(user.getLogin());
        passwordForm.clear();
        passwordForm.sendKeys(user.getPassword());
        confirmButton.click();
        return this;
    }
}
