package utilities;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    @BeforeMethod
    public void masterSetUp() {
        Logs.info("Setup del padre");
    }

    @AfterMethod
    public void masterTearDown() {
        Logs.info("Teardown del padre");
    }
}
