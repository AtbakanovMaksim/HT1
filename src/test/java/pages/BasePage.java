package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static framework.Browser.properties;

public abstract class BasePage {

    public boolean isElementDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public BasePage buttonClick(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(properties.getParameter("ExplicitTimeOut")));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        return this;
    }

    public String getButtonHexColor(WebElement element) {
        String RGBAcolor = element.getCssValue("background-color");
        return Color.fromString(RGBAcolor).asHex();
    }
}
