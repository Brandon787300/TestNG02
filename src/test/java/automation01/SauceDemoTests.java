package automation01;

import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class SauceDemoTests extends BaseTest {
    @Test(groups = {regression, smoke})
    public void usuarioInvalidoTest() {

        rellenarFormularioLogin("locked_out_user", "secret_sauce");

        Logs.info("Verificacion el mensaje de error");
        final var errorLabel = driver.findElement(By.cssSelector("h3[data-test='error']"));

        softAssert.assertTrue(errorLabel.isDisplayed());
        softAssert.assertEquals(errorLabel.getText(),
                "Epic sadface: Sorry, this user has been locked out.");
        softAssert.assertAll();
    }

    @Test(groups = {regression, smoke})
    public void usuarioValido(){
        rellenarFormularioLogin("standard_user", "secret_sauce");

        final var inventoryList = driver.findElement(By.className("inventory_list"));


        Logs.info("Verificando que el inventory list este visible");
        Assert.assertTrue(inventoryList.isDisplayed());
    }

    @Test(groups = {regression})
    public void detalleProductoTest(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var imageList = driver
                .findElements(By.cssSelector("img[class='inventory_item_img']"));

        Logs.info("Haciendo click en el primer elemento de la lista");
        imageList.get(0).click();

        Logs.info("Esperando que cargue el detalle del producto");
        final var inventoryName = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("inventory_details_name")));

        Logs.info("Verificacion del detalle del producto");
        softAssert.assertTrue(inventoryName.isDisplayed());
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_desc")).isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_price")).isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.xpath("//button[text()='Add to cart']")).isDisplayed()
        );
        softAssert.assertTrue(
                driver.findElement(By.className("inventory_details_img")).isDisplayed()
        );
        softAssert.assertAll();

    }

    @Test(groups = {regression})
    public void select1Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var selectWebElement = driver
                .findElement(By.cssSelector("select[data-test='product-sort-container']"));
        //casteamos a select
        final var select = new Select(selectWebElement);

        Logs.info("Sleccionamos los items de manera alfabetica Z -> A");
        select.selectByValue("za");

        final var itemList = driver.findElements(By.className("inventory_item_name"));

        Logs.info("Obteniendo el primer elemento");
        final var primerElemento = itemList.get(0).getText();

        Logs.info("Obteniendo el ultimo elemento");
        final var ultimoElemento = itemList.get(itemList.size() - 1).getText();

        Logs.info("Verificando los nombres");
        softAssert.assertEquals(primerElemento, "Test.allTheThings() T-Shirt (Red)");
        softAssert.assertEquals(ultimoElemento, "Sauce Labs Backpack");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void select2Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var selectWebElement = driver
                .findElement(By.cssSelector("select[data-test='product-sort-container']"));

        //casteamos a select
        final var select = new Select(selectWebElement);
        Logs.info("Seleccionamos los items de menor a mayor precio");
        select.selectByValue("lohi");

        final var priceList = driver.findElements(By.className("inventory_item_price"));

        Logs.info("Obteniendo el primer precio");
        final var primerPrecio = Double.parseDouble(
                priceList.get(0).getText().replace("$",""));

        Logs.info("Obteniendo el ultimo precio");
        final var ultimoPrecio = Double.parseDouble(
                priceList.get(priceList.size() - 1).getText().replace("$",""));

        Logs.info("Verificando los precios");
        softAssert.assertEquals(primerPrecio, 7.99);
        softAssert.assertEquals(ultimoPrecio, 49.99);
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void link1Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");
        final var facebookLabel = driver
                .findElement(By.xpath("//a[text()='Facebook']"));
        Logs.info("Verificando que el hypervinculo este correcto");
        softAssert.assertTrue(facebookLabel.isDisplayed());
        softAssert.assertTrue(facebookLabel.isEnabled());
        softAssert.assertEquals(facebookLabel.getAttribute("href"),"https://www.facebook.com/saucelabs");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void link2Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");

        final var linkedinLabel = driver
                .findElement(By.xpath("//a[text()='LinkedIn']"));

        Logs.info("Verificando que el hypervinculo este correcto");
        softAssert.assertTrue(linkedinLabel.isDisplayed());
        softAssert.assertTrue(linkedinLabel.isEnabled());
        softAssert.assertEquals(linkedinLabel.getAttribute("href"),
                "https://www.linkedin.com/company/sauce-labs/");
        softAssert.assertAll();
    }

    @Test(groups = {regression})
    public void link3Test(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        rellenarFormularioLogin("standard_user", "secret_sauce");

        Logs.info("Abriendo el menu burger");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        Logs.info("Esperando que abra el menu");


        final var aboutLink = wait.until(ExpectedConditions
                .elementToBeClickable(By.id("about_sidebar_link")));

        Logs.info("Verificando el vinculo de about");
        softAssert.assertTrue(aboutLink.isDisplayed());
        softAssert.assertTrue(aboutLink.isEnabled());
        softAssert.assertEquals(aboutLink.getAttribute("href"),
                "https://saucelabs.com/");
        softAssert.assertAll();


    }

    @Test(groups = {regression})
    public void logoutTest(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        rellenarFormularioLogin("standard_user", "secret_sauce");

        Logs.info("Abriendo el menu burger");
        driver.findElement(By.id("react-burger-menu-btn")).click();

        Logs.info("Esperando que abra el menu");
        final var logoutButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.id("logout_sidebar_link")));

        Logs.info("Haciendo click en el boton de logout");
        logoutButton.click();

        Logs.info("Esperando que llegue a la pagina inicial");
        final var usernameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("user-name")));

        Logs.info("Verificando un elemento de la pagina principal");
        Assert.assertTrue(usernameInput.isDisplayed());

    }

    @Test(groups = {regression})
    public void cookie1Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");

        Logs.info("Obteniendo el set de las cookies");
        var cookieSet = driver.manage().getCookies();

        Logs.info("Verificando que solo hay 1 cookie");
        Assert.assertEquals(cookieSet.size(), 1);

        Logs.info("Borrando las cookies");
        driver.manage().deleteAllCookies();

        Logs.info("Obteniendo el set de las cookies nuevamente");
        cookieSet =  driver.manage().getCookies();

        Logs.info("Verificando que su tamaño sea 0");
        Assert.assertTrue(cookieSet.isEmpty());
    }

    @Test(groups = {regression})
    public void cookie2Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");

        Logs.info("Obteniendo la info del cooke del login");
        final var cookieLogin = driver.manage().getCookieNamed("session-username");

        Logs.info("Verificando que su value sea standard_user");
        Assert.assertEquals(cookieLogin.getValue(), "standard_user");
    }

    @Test(groups = {regression})
    public void relativeLocator1Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");

        /*
        Obtengo el locator de manera relativa, este tiene un tag button
         ademas, se encuentra debajo del texto Sauce Labs Bolt T-Shirt
        */

        final var locator = (By) RelativeLocator
                .with(By.className("inventory_item_price"))
                .below(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));

        //encuentro el precio: quito dolar -> cambio a double
        final var price = Double.parseDouble(
                driver.findElement(locator).getText().replace("$", "")
        );

        Logs.info("Verificando que el precio sea correcto");
        Assert.assertEquals(price, 15.99);
    }

    @Test(groups = {regression})
    public void relativeLocator2Test(){
        rellenarFormularioLogin("standard_user", "secret_sauce");

        /*
        obtengo el locator de manera relativa, este tiene un ag button
         ademas, se encuentra debajo del texto Sauce Labs Fleece Jacket
        */
        final var locator = (By) RelativeLocator
                .with(By.tagName("button"))
                .below(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
        var cartButton = driver.findElement(locator);

        Logs.info("Verificando que el texto sea Add to cart");
        Assert.assertEquals(
                cartButton.getText(),
                "Add to cart"
        );

        Logs.info("Haciendo click en el boton de Sauce Labs Fleece Jacket");
        cartButton.click();

        Logs.debug("Refrescando referencia del boton");
        cartButton =  driver.findElement(locator);

        Logs.info("Verificando que su texto sea Remove");
        Assert.assertEquals(
                cartButton.getText(),
                "Remove"
        );
    }

    //Iniciar sesion
    private void rellenarFormularioLogin(String username, String password){

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Logs.info("Navegando a la pagina");
        driver.get("https://www.saucedemo.com/");

        Logs.info("Esperando que cargue la pagina principal");
        final var usernameInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("user-name")));

        Logs.info("Escribiendo el username");
        usernameInput.sendKeys(username);

        Logs.info("Escribiendo el password");
        driver.findElement(By.id("password")).sendKeys(password);

        Logs.info("Haciendo click en el boton de login");
        driver.findElement(By.id("login-button")).click();

        //si no es standard no vs va al shopping page
        if (username.equals("standard_user")){
            Logs.info("Esperando que cargue la pagina de shopping");
            wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.className("inventory_list")));
        }
    }
}
