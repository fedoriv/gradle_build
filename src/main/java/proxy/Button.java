package proxy;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Element;

public class Button extends Element implements WebElement {
    private WebElement element;

    public Button(WebElement webElement) {
        super(webElement);
        element = webElement;
    }

    @Override
    public void submit() {
        this.click();
    }

    /*
    * Method modified to simulate element hover and use JavaScript for click action.
    * */
    public void click(WebDriver driver) {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        Actions builder = new Actions(driver);
        builder.moveToElement(element);
        new WebDriverWait(driver, 10).until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(element)));
        executor.executeScript("arguments[0].click();", element);
    }
}
