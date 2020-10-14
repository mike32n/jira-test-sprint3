import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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
    private static AltLoginPage altLoginPage = new AltLoginPage(driver);
    private static MainPage mainPage = new MainPage(driver);
    private static ProjectSummaryPage projectSummaryPage = new ProjectSummaryPage(driver);
    private static CreateIssuePage createIssuePage = new CreateIssuePage(driver);
    private static IssueDetailPage issueDetailPage = new IssueDetailPage(driver);
    private static ProfilePage profilePage = new ProfilePage(driver);

    @ParameterizedTest()
    @DisplayName("Successful Login")
    @CsvFileSource(resources = "/LoginCredentials.csv", numLinesToSkip = 1)
    public void successfulLogin(String user, String pass) {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername(user);
        loginPage.setPassword(pass);
        loginPage.clickLoginButton();

        mainPage.navigate(Utils.PROFILE_PAGE);

        profilePage.verifyUsername(user);

        mainPage.logout();
    }

    @ParameterizedTest()
    @DisplayName("Alternative Login")
    @CsvFileSource(resources = "/LoginCredentials.csv", numLinesToSkip = 1)
    public void alternativeLogin(String user, String pass) {
        loginPage.maximizeWindow();
        loginPage.openAlternativeLoginPage();

        altLoginPage.setUsername(user);
        altLoginPage.setPassword(pass);
        altLoginPage.clickLoginButton();

        mainPage.navigate(Utils.PROFILE_PAGE);

        profilePage.verifyUsername(user);

        mainPage.logout();
    }

    @Test
    public void emptyProjectWithoutSummary() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.clickCreateButton();

        createIssuePage.setProjectField("EMPTY");
        createIssuePage.clickOnCreate();
        createIssuePage.verifyErrorMessage();

        createIssuePage.clickOnCancel();
        createIssuePage.acceptAlert();
        mainPage.logout();
    }

    @Test
    public void createIssueWithRequiredFieldsFilled() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.clickCreateButton();

        createIssuePage.setProjectField("MTP");
        createIssuePage.setIssueField("Task");
        createIssuePage.setSummaryField("Testing \"Create Issue\" with all required fields are filled");
        createIssuePage.clickOnCreate();
        createIssuePage.clickLinkOnPopUpScreen();

        issueDetailPage.verifySummary("Testing \"Create Issue\" with all required fields are filled");
        issueDetailPage.deleteIssue();

        mainPage.logout();
    }

    @ParameterizedTest()
    @CsvFileSource(resources = "/CreateIssueData.csv", numLinesToSkip = 1)
    public void issueTypesForProjectsTest(String projectName, String issueType,
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
        mainPage.logout();
    }
  
    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseProjectsData.csv", numLinesToSkip = 1)
    public void browseProjectsTest(String URL, String projectKey){
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();
        projectSummaryPage.navigate(URL);
        projectSummaryPage.verifyKey(projectKey);
        mainPage.logout();
    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}