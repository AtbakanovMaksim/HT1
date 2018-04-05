package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    static WebDriver chooseBrowser(String browserName) {
        WebDriver driver = null;
        String osName = getOsName();
        switch (browserName) {
            case "Firefox":
                if (osName.startsWith("win")) {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                    driver = new FirefoxDriver();
                } else {
                    if (osName.equals("linux")) {
                        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
                        driver = new FirefoxDriver();
                    }
                }
                break;

            case "Chrome":
                if (osName.startsWith("win")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                    driver = new ChromeDriver();
                } else {
                    if (osName.equals("linux")) {
                        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
                        driver = new ChromeDriver();
                    }
                }
                break;
        }
        return driver;
    }

    private static String getOsName() {
        String os = System.getProperty("os.name").toLowerCase();
        return os;
    }
}