import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeAll
    public static void setup() {
        // ChromeDriver location set up in Utils class
        System.setProperty(Utils.WEBDRIVER, Utils.CHROME_DRIVER_LOCATION);
        driver.manage().deleteAllCookies();
    }

    private static LoginPage loginPage = new LoginPage(driver);
    private static ProjectSummaryPage projectSummaryPage = new ProjectSummaryPage(driver);
    private static CreateIssuePage createIssuePage = new CreateIssuePage(driver);

    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProjectsData.csv", numLinesToSkip = 1)
    public void browseProjectsTest(String URL, String projectKey){
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();
        //driver.switchTo().alert().dismiss();
        projectSummaryPage.navigate(URL);
        projectSummaryPage.verifyKey(projectKey);
        createIssuePage.logout();
    }

    @AfterAll
    public static void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}