package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.Footer;
import pages.LoginPage;
import pages.ShoppingPage;
import utilities.BaseTest;
import utilities.Logs;

public class FooterTests extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final ShoppingPage shoppingPage = new ShoppingPage();
    private final Footer footer = new Footer();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logs.info("Navegando a la url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad(); // espero que cargue la pagina

        loginPage.fillLogin("standard_user", "secret_sauce");
        shoppingPage.waitPageToLoad();
    }

    @Test(groups = {regression})
    public void socialMediaLinksTests() {
        footer.verifySocialMediaLinks(
            "https://twitter.com/saucelabs",
                "https://www.linkedin.com/company/sauce-labs/",
                "https://www.facebook.com/saucelabs"
        );
    }
}
