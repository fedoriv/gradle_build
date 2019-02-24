package proxy;

import org.openqa.selenium.WebElement;
import utils.Element;

public class TextInput extends Element implements WebElement {

    public TextInput(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void sendKeys(CharSequence... charSequences) {
        webElement.clear();
        super.webElement.sendKeys(charSequences);
    }
}
