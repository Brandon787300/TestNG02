package automation01;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.BaseTest;
import utilities.Logs;

import java.io.File;

public class TestPagesTests extends BaseTest {
    @Test
    public void subirFileTest(){
        Logs.info("Navegando a la pagina");
        driver.get("https://testpages.eviltester.com/styled/file-upload-test.html");

        final var file = new File("src/test/resources/imagen/picture.jpg");


        Logs.info("Subiendo la imagen");
        driver.findElement(By.id("fileinput")).sendKeys(file.getAbsolutePath());

        Logs.info("Seleccionando la imagen");
        driver.findElement(By.id("itsanimage")).click();

        Logs.info("Haciendo click boton verde");
        driver.findElement(By.name("upload")).click();

        Logs.info("Verificacion que se subio la imagen");
        Assert.assertTrue(
                driver.findElement(By.xpath("//h2[text()='You uploaded this image:']"))
                        .isDisplayed()
        );
    }




}
