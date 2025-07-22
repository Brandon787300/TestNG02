package automation01;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

public class SeleniumDevTests extends BaseTest {
    @Test
    public void scroll1Test(){
        Logs.info("Navegando a la pagina");
        driver.get("https://www.selenium.dev/selenium/web/scroll.html");

        Logs.debug("Generandoun numero aleatorio entre 5 y 9");
        final var faker = new Faker();
        final var n = faker.number().numberBetween(5,9);
        Logs.debug("n: %d", n);

        final var dynamiId = String.format("line%d", n); //Line5, line 6, etc
        final var lineN = driver.findElement(By.id(dynamiId));

        Logs.info("Haciendo scroll hacia el id: %s", dynamiId);
        new Actions(driver)
                .scrollToElement(lineN) //hago scroll hacia el elemento
                .pause(1000) //espero 1000ms =  1s
                .perform(); //realizo las acciones

        Logs.info("Haciendo click en lineN");
        lineN.click();

        Logs.info("Verificando que el texto sea: %s", dynamiId);
        Assert.assertEquals(
                driver.findElement(By.id("clicked")).getText(),
                dynamiId
        );
    }

    @Test
    public void scroll2Test(){
        Logs.info("Navegando a la pgina");
        driver.get("https://www.selenium.dev/selenium/web/scrolling_tests/" +
                "page_with_frame_out_of_view.html");

        final var iframe = driver.findElement(By.name("frame"));

        Logs.info("Haciendo scroll hacia el iframe");
        new Actions(driver)
                .scrollToElement(iframe) //hago scroll havia el ifrmae
                .pause(1000) //espero 1000ms = 1s
                .perform();

        Logs.debug("Cambiamos el contexto al iframe");
        driver.switchTo().frame(iframe);

        final var checkBox =  driver.findElement(By.name("checkbox"));

        Logs.info("Haciendo click en el checkbox");
        checkBox.click();

        Logs.info("Verificando que el checkbox este seleccionado");
        Assert.assertTrue(checkBox.isSelected());

    }



}
