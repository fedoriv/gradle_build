import busnes_object.LoginBO;
import busnes_object.MessageBO;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestGmail {

    /*
     * In this case, using of ThreadLocal class is not necessary and WebDriver can be created in method.
     * But one of Homework tasks was implement ThreadLocal to store WebDriver objects
     * */
    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger(LoginBO.class.getName());
    private static final String IS_SENT = "//span[@class='bAq']";


    @BeforeClass
    public void setDriverPath() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver_win32/chromedriver.exe");
        if (System.getProperty("to") == null) {
            //set default receiver email.
            System.setProperty("to", "evilzluj@mail.ru");
        }
    }

    @BeforeMethod
    public void setDriver() {
        WebDriver driver = new ChromeDriver();
        LOG.info("Set new WebDriver");
        threadLocal.set(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get("https://www.gmail.com/");
    }

    @Test(dataProvider = "GmailLoginData")
    public void testGmail(String login, String pw) {
        LOG.info("TEST STARTED");
        WebDriver driver = threadLocal.get();
        LoginBO loginBO = new LoginBO(driver);
        MessageBO messageBO = new MessageBO(driver);

        loginBO.login(login, pw);
        messageBO.writeAndDraft();
        messageBO.openDraftAndSend();

        Assert.assertTrue(new WebDriverWait(driver, 10).until(d -> d.findElement(By.xpath(IS_SENT)).isDisplayed()));
        LOG.info("TEST FINISHED");
    }

    @AfterMethod
    public void closeDriver() {
        threadLocal.get().close();
    }

    @DataProvider(name = "GmailLoginData", parallel = true)
    public Object[][] loginData() {
        String csvFile = "src/main/resources/credentials.csv";
        List<Object[]> list = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] data = new Object[list.size()][];

        return list.toArray(data);
    }
}
