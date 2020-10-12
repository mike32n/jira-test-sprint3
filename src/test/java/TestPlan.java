import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main(String[] args) {
        // ChromeDriver location set up in Utils class
        System.setProperty(Utils.WEBDRIVER, Utils.CHROME_DRIVER_LOCATION);
    }

    private static LoginPage loginPage = new LoginPage(driver);
    private static MainPage mainPage = new MainPage(driver);

    @Test(testName = "Empty Project Without Summary")
    public static void emptyProjectWithoutSummary() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.clickCreateButton();
    }

    @AfterSuite
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
