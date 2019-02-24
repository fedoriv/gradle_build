package page;

import busnes_object.LoginBO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MailList extends BasePage {

    private static final Logger LOG = LogManager.getLogger(LoginBO.class.getName());

    @FindAll(@FindBy(xpath = "//div[@class='ae4 UI']/div[@class='Cp']//table[@class='F cf zt']/tbody/tr"))
    public List<WebElement> emailList;
    private WebDriver driver;

    public MailList(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    /*
    * Method gets first mail from list
    * */
    public void openFirst() {
        new WebDriverWait(driver, 15).until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState")
                .toString().equals("complete"));
        if (emailList.size() > 0) {
            WebElement element = emailList.get(0);
            element.click();
        } else {
            LOG.warn("Cant open message. Email list is empty");
        }
    }
}
