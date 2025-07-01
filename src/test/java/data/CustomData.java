package data;

import org.testng.annotations.DataProvider;
import reader.ExcelReader;

public class CustomData {

    public static final String DP_NAME = "Data Monstruo";

    @DataProvider(name = DP_NAME)
    public Object[][] monstruoDataProvider(){
        final var listaMnstruo = ExcelReader.obtenerListaMonstruo();

        final var n = listaMnstruo.size();
        final var object = new Object[n][];

        for (var i = 0; i < n; i++) {
            object[i] = new Object[]{listaMnstruo.get(i)}; //Le paso el onstruo al test
        }

        return object;
    }
}
