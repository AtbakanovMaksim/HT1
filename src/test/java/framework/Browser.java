package framework;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    public static Properties properties = new Properties();
    private static WebDriver driver;


    public static synchronized WebDriver getInstance() {
        if (driver == null) {
            driver = BrowserFactory.chooseBrowser(properties.getParameter("BrowserName"));
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getParameter("ImplicitTimeOut")), TimeUnit.SECONDS);
        return driver;
    }
}