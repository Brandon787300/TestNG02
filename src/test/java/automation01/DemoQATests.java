package automation01;

import com.github.javafaker.Faker;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.time.Duration;

public class DemoQATests extends BaseTest {
    @Test //Este test puede fallar si el navergador esta muy cerca
    public void keyboardTest(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var fullName = faker.name().fullName();
        Logs.debug("fullName: %s", fullName);

        final var usernameInput = driver.findElement(By.id("userName"));

        Logs.info("Hago click para dar focus, Presionando SHIFT y escribiendo en masyuculas");
        new Actions(driver)
                .click(usernameInput) //hago click para dar focus
                .keyDown(Keys.SHIFT) //presiono shift
                .sendKeys(fullName) //escribo el fullname
                .keyUp(Keys.SHIFT) //dejo de presionar shift
                .perform(); //realizo las acciones

        Logs.info("Verificando que el input este en masyuculas");
        Assert.assertEquals(
                usernameInput.getAttribute("value"),
                fullName.toUpperCase()
        );
    }

    @Test
    public void keyboard2Test(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/text-box");

        final var faker = new Faker();
        final var address = faker.address().fullAddress();
        Logs.debug("Address: %s", address);

        final var currentAddressInput = driver.findElement(By.id("currentAddress"));

        /*
        para macos se command para copiar y pegar en
        el caso de usar windows, reemplacen command por control
        */

        Logs.info("Doy focus, escribo, Selecciono y copio el contenido");
        new Actions(driver)
                .click(currentAddressInput) //doy focus
                .sendKeys(address) //escribp
                .keyDown(Keys.CONTROL) //presiono command
                .sendKeys("a") //CMD + A selecciona el contenido
                .sendKeys("c") //CMD + C copia el contenido
                .keyUp(Keys.CONTROL) //suelto command
                .perform(); //realizo las acciones

        final var permanentAddressInput = driver.findElement(By.id("permanentAddress"));

        Logs.info("Pegando el contenido");
        new Actions(driver)
                .click(permanentAddressInput) //doy focus en el input
                .keyDown(Keys.CONTROL) //presiono command
                .sendKeys("v") //CMD + V pega el contenido
                .keyUp(Keys.CONTROL) //suelto command
                .perform(); //realizo las acciones

        Logs.info("Verificando que ambos input tengan el mismo texto");
        Assert.assertEquals(
                permanentAddressInput.getAttribute("value"),
                currentAddressInput.getAttribute("value")
        );
    }

    @Test
    public void mouse1Test(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/droppable");

        final var figuraOrigen = driver.findElement(By.id("draggable"));
        final var figuraDestino = driver.findElement(By.id("droppable"));

        Logs.info("Arrastrando la figura origen a la de destino");
        new Actions(driver)
                .dragAndDrop(figuraOrigen, figuraDestino) //arrastro origen a destino
                .perform(); //realizo las acciones

        Logs.info("Verificando que el label dropped sea visible");
        Assert.assertTrue(
                driver.findElement(By.xpath("//p[text()='Dropped!']"))
                        .isDisplayed()
        );
    }

    @Test
    public void mouse2Test(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/tool-tips");

        final var botonVerde = driver.findElement(By.id("toolTipButton"));

        Logs.info("Poniendo el puntero del mouse encima del boton verde");
        new Actions(driver)
                .moveToElement(botonVerde) //puntero encima del boton verde
                .pause(1500) //pauso por 1500ms = 1,5s
                .perform(); //realizo las acciones

        Logs.info("Verificando el texto del hover");
        Assert.assertEquals(
                botonVerde.getAttribute("aria-describedby"),
                "buttonToolTip"
        );
    }

    @Test
    public void alert1Test(){
        Logs.info("Navengando a la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        Logs.info("Esperando que cargue la pagina");
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[text()='Alerts']")));

        Logs.info("Haciendo click en el boton para que aparezca el alert");
        driver.findElement(By.id("alertButton")).click();

        Logs.debug("Obteniendo el alert");
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        Logs.debug("Verificando que el texto del alert sea correcto");
        Assert.assertEquals(alert.getText(), "You clicked a button");

        Logs.info("Presionando el boton del alert");
        alert.accept();

    }

    @Test
    public void alert2Test(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        Logs.info("Esperando que cargue la pagina");
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[text()='Alerts']")));

        Logs.info("Haciendo click en el boton para que aparezca el confirm");
        driver.findElement(By.id("confirmButton")).click();

        Logs.debug("Obteniendo el alert");
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        Logs.info("Haciendo click en cancel en el alert/confirm");
        alert.dismiss();

        Logs.info("Verificando que el div cancel sea visible");
        Assert.assertTrue(driver
                .findElement(By.xpath("//span[text()='Cancel']")).isDisplayed());

    }

    @Test
    public void alert3Test(){
        Logs.info("Navegando a la pagina");
        driver.get("https://demoqa.com/alerts");

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        Logs.info("Esperando que cargue la pagina");
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h1[text()='Alerts']")));

        Logs.info("Haciendo click en el boton para que aparezca el prompt");
        driver.findElement(By.id("promtButton")).click();

        Logs.debug("Obteniendo el alert");
        final var alert = (Alert) wait.until(ExpectedConditions.alertIsPresent());

        final var faker = new Faker();
        final var randomName = faker.name().firstName();

        Logs.info("Escribiendo el nombre en el prompt");
        alert.sendKeys(randomName);

        Logs.info("Presionando accept en el alert");
        alert.accept();

        final var dynamiclocator = String.format("//span[text()='%s']", randomName);

        Logs.info("Verificando que el nombre sea visible");
        Assert.assertTrue(driver.findElement(By.xpath(dynamiclocator)).isDisplayed());
    }



}
