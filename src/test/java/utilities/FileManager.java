package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class FileManager {
    private static final String screenshotPath = "src/test/resources/screenshots";

    public static void getScreenshot(String screenshotPath){
        Logs.debug("Tomando screenshots");

        final var screenshots = ((TakesScreenshot) new WebDriverProvider().get())
                .getScreenshotAs(OutputType.FILE);

        //final var path = String.format("%s/%s.png", screenshotPath, screenshotName);


    }
}
