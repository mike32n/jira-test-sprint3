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
    }

    private static LoginPage loginPage = new LoginPage(driver);
    private static MainPage mainPage = new MainPage(driver);
    private static CreateIssuePage createIssuePage = new CreateIssuePage(driver);

    @Test
    public void emptyProjectWithoutSummary() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.clickCreateButton();

        createIssuePage.setProjectField("EMPTY");
        createIssuePage.clickOnSummaryField();
        createIssuePage.clickOnSubmit();
        createIssuePage.verifyErrorMessage();

        createIssuePage.clickOnCancel();
        createIssuePage.acceptAlert();
        createIssuePage.logout();
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/CreateIssueData.csv", numLinesToSkip = 1)
    public void createIssueForProjectTest(String projectName, String issueType,
                                          String assertProjectName, String assertIssueType) {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.clickCreateButton();

//        createIssuePage.clickOnProjectField();
        createIssuePage.setProjectField(projectName);
//        createIssuePage.clickOnIssueField();
        createIssuePage.setIssueField(issueType);

        createIssuePage.verifyProjectField(assertProjectName);
        createIssuePage.verifyIssueType(assertIssueType);

        createIssuePage.clickOnCancel();
        createIssuePage.logout();
    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}
