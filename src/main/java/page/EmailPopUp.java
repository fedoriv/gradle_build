package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import proxy.Button;
import proxy.TextInput;

public class EmailPopUp extends BasePage {

    @FindBy(xpath = "//form[@class='bAs']//textarea[@name='to']")
    private TextInput inputEmailReciver;

    @FindBy(xpath = "//form/div/input[@name='subjectbox']")
    private TextInput inputEmailTitle;

    @FindBy(xpath = "//div[@class='AD']//td/div/div[@role='textbox']")
    private WebElement inputMessage;

    @FindBy(xpath = "//div[@class='AD']//tr[@class='btC']/td/div/div[@role='button']")
    private Button sendMessage;

    @FindBy(xpath = "//td[@class='Hm']/img[last()]")
    private Button closePopUp;

    private WebDriver driver;

    public EmailPopUp(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void typeReceiver(String receiver) {
        inputEmailReciver.sendKeys(receiver);
    }

    public void typeTitle(String title) {
        inputEmailTitle.sendKeys(title);
    }

    public void typeMessage(String message) {
        inputMessage.sendKeys(message);
    }

    public void close() {
        closePopUp.click();
    }

    public void sendMessage() {
        sendMessage.click();
    }
}
