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
    private static EditIssuePage editIssuePage = new EditIssuePage(driver);

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
    @DisplayName("Login Without Password")
    public void loginWithoutPassword() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.clickLoginButton();

        loginPage.verifyErrorMessage("Sorry, your username and password are incorrect - please try again.");

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();
        mainPage.logout();
    }

    @Test
    @DisplayName("Login Without Credentials")
    public void loginWithoutCredentials() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.clickLoginButton();

        loginPage.verifyErrorMessage("Sorry, your username and password are incorrect - please try again.");

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();
        mainPage.logout();
    }

    @Test
    @DisplayName("Successful Logout")
    public void successfulLogout() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.logout();

        mainPage.verifySuccessfulLogout();
    }

    @Test
    @DisplayName("EMPTY Project Without Summary")
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
    @DisplayName("Create Issue With Required Fields Are Filled")
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
    @DisplayName("Issue Types For Projects")
    @CsvFileSource(resources = "/CreateIssueData.csv", numLinesToSkip = 1)
    public void issueTypesForProjectsTest(String projectName, String issueType,
                                          String assertProjectName, String assertIssueType) {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.clickCreateButton();

        createIssuePage.setProjectField(projectName);
        createIssuePage.setIssueField(issueType);

        createIssuePage.verifyProjectField(assertProjectName);
        createIssuePage.verifyIssueType(assertIssueType);

        createIssuePage.clickOnCancel();
        mainPage.logout();
    }
  
    @ParameterizedTest
    @DisplayName("Browse Projects")
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

    @ParameterizedTest
    @CsvFileSource(resources = "/BrowseIssueData.csv", numLinesToSkip = 1)
    public void browseIssuesTest(String URL, String issueKey){
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        issueDetailPage.navigate(URL);
        issueDetailPage.verifyIssueKey(issueKey);

        mainPage.logout();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/EditIssueData.csv", numLinesToSkip = 1)
    public void editIssuesTest(String URL) {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        projectSummaryPage.navigate(URL);

        issueDetailPage.clickOnEdit();
        editIssuePage.setSummaryField("This is a test for editing issues");
        editIssuePage.clickOnUpdate();

        issueDetailPage.verifyEditedSummary("This is a test for editing issues");

        issueDetailPage.clickOnEdit();
        editIssuePage.setSummaryField("Test issue");
        editIssuePage.clickOnUpdate();

        issueDetailPage.verifyEditedSummary("Test issue");

        mainPage.logout();
    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}