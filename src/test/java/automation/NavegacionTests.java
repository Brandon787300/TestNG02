package automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class NavegacionTests extends BaseTest {
    @Test
    public void ejercicio1Test(){
        final var url = "https://www.saucedemo.com/";

        Logs.info("Navegando a: %s", url);
        driver.get(url);

        Logs.info("Obteniendo la url actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que las urls sean iguales");
        Assert.assertEquals(currentUrl, url);
    }

    @Test
    public void ejercicio2(){
        final var urlHeroku = "https://the-internet.herokuapp.com/";
        final var urlGithub = "https://github.com/";

        Logs.info("Navegando a: %s", urlHeroku);
        driver.get(urlHeroku);

        Logs.info("Navegando a: %s", urlGithub);
        driver.get(urlGithub);

        Logs.info("Regresando a la pagina anterior");
        driver.navigate().back();

        Logs.info("Obteniendo la url actual");
        final var currentUrl = driver.getCurrentUrl();

        Logs.info("Verificando que las urls sean iguales");
        Assert.assertEquals(currentUrl, urlHeroku);
    }
}
