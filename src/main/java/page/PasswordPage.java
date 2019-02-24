package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import proxy.Button;
import proxy.TextInput;


public class PasswordPage extends BasePage {

    private WebDriver driver;

    @FindBy(css = "input[name='password']")
    private TextInput passwordInput;

    @FindBy(xpath = "//*[@id='passwordNext']//span")
    private Button nextButton;

    public PasswordPage (WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void typePassword(String password) {
        passwordInput.sendKeys(password);
    }
    public void clickNext() {
        nextButton.click(driver);
    }
}
