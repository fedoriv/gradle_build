package busnes_object;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page.LoginPage;
import page.PasswordPage;

public class LoginBO {

    private static final Logger LOG = LogManager.getLogger(LoginBO.class.getName());

    private LoginPage loginPage;
    private PasswordPage pwPage;

    public LoginBO(WebDriver driver) {
        loginPage = new LoginPage(driver);
        pwPage = new PasswordPage(driver);
    }

    public void login(String login, String password) {
        LOG.info("Typing login");
        loginPage.typeLogin(login);
        LOG.info("Pushing next button");
        loginPage.clickNext();
        LOG.info("Typing password");
        pwPage.typePassword(password);
        LOG.info("Pushing next button");
        pwPage.clickNext();
    }
}
