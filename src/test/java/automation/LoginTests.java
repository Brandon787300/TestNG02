package automation;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.BaseTest;
import utilities.Logs;

public class LoginTests  extends BaseTest {
    private final LoginPage loginPage = new LoginPage();

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        Logs.info("Navegando a la url");
        driver.get("https://www.saucedemo.com/");

        loginPage.waitPageToLoad(); //espero qur cargue la pagina
    }

    @Test (groups = {regression})
    public void usuarioInvalidoTest(){
        loginPage.fillLogin("locked_out_user", "secret_sauce");
        loginPage.verifyErrorMenssage(
                "Epic sadface: Sorry, this user has been locked out."
        );
    }

    @Test(groups = {regression, smoke})
    public void verifyLoginPageTest() {
        loginPage.verifyPage();
    }




}
