package utilities;

import listeners.SuiteListeners;
import listeners.TestListeners;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

@Listeners({TestListeners.class, SuiteListeners.class})
public class BaseTest {
    protected SoftAssert softAssert;
    protected WebDriver driver;
    protected final String regression = "regression";
    protected final String smoke = "smoke";

    @BeforeMethod(alwaysRun = true)
    public void masterSetUp() {

        softAssert = new SoftAssert();
        Logs.debug("Inializando el driver");
        driver = new ChromeDriver();

        Logs.debug("Maximizando pantalla");
        driver.manage().window().maximize();

        Logs.debug("Borrando cookies");
        driver.manage().deleteAllCookies();

        Logs.debug("Asignando driver al webdriver provider");
        new WebDriverProvider().set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void masterTearDown() {
        Logs.debug("Matando el driver");
        driver.quit();
    }
}
