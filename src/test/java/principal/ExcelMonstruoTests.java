package principal;

import modelos.Monstruo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import reader.ExcelReader;
import utilities.BaseTest;

import java.util.List;

public class ExcelMonstruoTests extends BaseTest {
    private List<Monstruo> listaMonstruo;

    @BeforeMethod
    public void setUp() {
        listaMonstruo = ExcelReader.obtenerListaMonstruo();
    }

    @Test
    public void primerTest() {
        final var primerMonstruo = listaMonstruo.get(0);
        Assert.assertEquals(primerMonstruo.getNombre(), "TOLOSA",
                "1er nombre no hace match");
    }

    @Test
    public void segundoTest() {
        final var n = listaMonstruo.size();
        Assert.assertEquals(n, 14,
                "longitud incorrecta");
    }

    @Test
    public void tercerTest() {
        final var tercerMonstruo = listaMonstruo.get(2);
        Assert.assertEquals(tercerMonstruo.getNivel(), 22,
                "3er nivel no hace match");
    }
}
