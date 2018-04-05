package pages;

import framework.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteUserPage extends BasePage {

    WebDriver driver = Browser.getInstance();

    @FindBy(xpath = "//div[@id='main-panel']//form[@name='delete']")
    private WebElement errorMessage;
    @FindBy(xpath = "//div[@id='main-panel']//form[@name='delete']//button")
    private WebElement confirmButton;

    private String expectedErrorMesssage = "Вы уверены, что хотите удалить пользователя из Jenkins?";

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public WebElement getConfirmButton() {
        return confirmButton;
    }

    public String getExpectedErrorMesssage() {
        return expectedErrorMesssage;
    }

    public DeleteUserPage(WebDriver driver) {
        // проверить, что вы находитесь на верной странице
        if (!driver.getCurrentUrl().contains("delete")) {
            throw new IllegalStateException("This is not [" + DeleteUserPage.class + "] the page you are expected");
        }
    }
}