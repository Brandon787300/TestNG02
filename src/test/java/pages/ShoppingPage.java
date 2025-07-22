package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.BasePage;
import utilities.Logs;

public class ShoppingPage extends BasePage {
    private final By inventoryList = By.className("inventory_list");
    private final By productTitle = By.xpath("//span[text()='Products']");
    private final By selecItem = By.cssSelector("select[data-test='product-sort-container']");

    private By getItemName(String itemName) {
        final var xpath = String.format("//div[text()='%s']", itemName);
        return By.xpath(xpath);
    }

    @Override
    @Step("Esperando que cargue la pagina de shopping")
    public void waitPageToLoad() {
        waitPage(inventoryList, this.getClass().getSimpleName());
    }

    @Override
    @Step("Verificando la pagina de shopping")
    public void verifyPage() {
        Logs.info("Verificando la pagina de shopping");
        softAssert.assertTrue(find(inventoryList).isDisplayed());
        softAssert.assertTrue(find(productTitle).isDisplayed());
        softAssert.assertTrue(find(selecItem).isDisplayed());
        softAssert.assertAll();
    }

    @Step("Navegando al detalle del item")
    public void goToItemDetail(String itemName) {
        Logs.info("Navegandi al detalle del item: %s", itemName);
        find(getItemName(itemName)).click();
    }
}
