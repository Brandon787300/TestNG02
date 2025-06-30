package principal;

import modelos.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reader.JsonReader;
import utilities.BaseTest;

public class JsonUserTests extends BaseTest {
    private User user;

    @BeforeMethod
    public void setUp() {
        user = JsonReader.leerUsuarioJson();
    }

    @Test
    public void primerTest() {
        System.out.printf("El id es: %d%n", user.getId());
    }

    @Test
    public void segundoTest() {
        System.out.printf("El address.geo.lng es: %.2f%n", user.getAddress().getGeo().getLng());
    }


    //@Test
    // public void tercerTest() {
    //System.out.printf("El company.bs es: %s%n", user.getCompany().getBsss());
    //
}
