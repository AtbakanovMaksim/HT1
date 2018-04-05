package pages;

import framework.Browser;
import framework.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddUserPage extends BasePage {

    WebDriver driver = Browser.getInstance();

    @FindBy(xpath = "//div[@id='main-panel']//div[@class='error']")
    private WebElement errorMessage;
    @FindBy(xpath = "//div[@id='main-panel']//input[@id='username']")
    private WebElement loginForm;
    @FindBy(xpath = "//div[@id='main-panel']//input[@name='password1']")
    private WebElement passwordForm;
    @FindBy(xpath = "//div[@id='main-panel']//input[@name='password2']")
    private WebElement passwordConfirmForm;
    @FindBy(xpath = "//div[@id='main-panel']//input[@name='fullname']")
    private WebElement fullNameForm;
    @FindBy(xpath = "//div[@id='main-panel']//input[@name='email']")
    private WebElement emailForm;
    @FindBy(xpath = "//div[@id='main-panel']//span[@id='yui-gen1']//button")
    private WebElement confirmButton;

    private String expectedErrorMessage = "\"\" is prohibited as a full name for security reasons.";

    public String getExpectedErrorMessage() {
        return expectedErrorMessage;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getLoginForm() {
        return loginForm;
    }

    public WebElement getPasswordForm() {
        return passwordForm;
    }

    public WebElement getPasswordConfirmForm() {
        return passwordConfirmForm;
    }

    public WebElement getFullNameForm() {
        return fullNameForm;
    }

    public WebElement getEmailForm() {
        return emailForm;
    }

    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public AddUserPage(WebDriver driver) {
        // проверить, что вы находитесь на верной странице
        if (!driver.getCurrentUrl().contains("addUser")) {
            throw new IllegalStateException("This is not [" + AddUserPage.class + "] the page you are expected");
        }
    }

    public AddUserPage authorisationSuccess(User user) {
        loginForm.clear();
        loginForm.sendKeys(user.getLogin());
        passwordForm.clear();
        passwordForm.sendKeys(user.getPassword());
        passwordConfirmForm.clear();
        passwordConfirmForm.sendKeys(user.getConfirmPassword());
        fullNameForm.clear();
        fullNameForm.sendKeys(user.getFullName());
        emailForm.clear();
        emailForm.sendKeys(user.getEmail());
        confirmButton.click();
        return this;
    }

    public AddUserPage authorisationWithoutName(User user) {
        loginForm.clear();
        passwordForm.clear();
        passwordForm.sendKeys(user.getPassword());
        passwordConfirmForm.clear();
        passwordConfirmForm.sendKeys(user.getConfirmPassword());
        fullNameForm.clear();
        fullNameForm.sendKeys(user.getFullName());
        emailForm.clear();
        emailForm.sendKeys(user.getEmail());
        confirmButton.click();
        return this;
    }

    public boolean checkForm() {
        boolean result = false;
        if (loginForm.isDisplayed() && (loginForm.getText().equals("") && loginForm.getAttribute("type").equals("text"))) {
            if (passwordForm.isDisplayed() && (passwordForm.getText().equals("") && passwordForm.getAttribute("type").equals("password"))) {
                if (passwordConfirmForm.isDisplayed() && (passwordConfirmForm.getText().equals("") && passwordConfirmForm.getAttribute("type").equals("password"))) {
                    if (fullNameForm.isDisplayed() && (fullNameForm.getText().equals("") && fullNameForm.getAttribute("type").equals("text"))) {
                        if (emailForm.isDisplayed() && (emailForm.getText().equals("") && emailForm.getAttribute("type").equals("text"))) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }
}
