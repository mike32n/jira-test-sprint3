import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class TestPlan {

    private static final ChromeOptions capabilities = new ChromeOptions();
    private static RemoteWebDriver driver;

    static {
        try {
            driver = new RemoteWebDriver(new URL("https://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @BeforeAll
    public static void setup() {
        // ChromeDriver location set up in Utils class
//        ChromeOptions capabilities = new ChromeOptions();
        capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.IGNORE);
        System.setProperty(Utils.WEBDRIVER, Utils.CHROME_DRIVER_LOCATION);
//        driver = new RemoteWebDriver(new URL("https://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu/wd/hub"), capabilities);
    }


    private static LoginPage loginPage = new LoginPage(driver);
    private static AltLoginPage altLoginPage = new AltLoginPage(driver);
    private static MainPage mainPage = new MainPage(driver);
    private static ProjectSummaryPage projectSummaryPage = new ProjectSummaryPage(driver);
    private static ProjectSettingsPage projectSettingsPage = new ProjectSettingsPage(driver);
    private static CreateIssuePage createIssuePage = new CreateIssuePage(driver);
    private static IssueDetailPage issueDetailPage = new IssueDetailPage(driver);
    private static EditIssuePage editIssuePage = new EditIssuePage(driver);
    private static ProfilePage profilePage = new ProfilePage(driver);
    private static ReleasesPage releasesPage = new ReleasesPage(driver);
    private static ComponentsPage componentsPage = new ComponentsPage(driver);
    private static ProjectConfigPageGlass projectConfigPageGlass = new ProjectConfigPageGlass(driver);


    @Test
    public void successfulLogin() {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n", envName, env.get(envName));
        }
        System.out.println("AÉSKFNLKSDNGFLKSFDNGLKFNDLKNGLKDFNGNDFLKNGLKFDLJKGNFDGL");
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.navigate(Utils.PROFILE_PAGE);

        profilePage.verifyUsername("user5");

        mainPage.logout();
    }

    @ParameterizedTest()
    @DisplayName("Alternative Login")
    @CsvFileSource(resources = "/LoginCredentials.csv", numLinesToSkip = 1)
    public void alternativeLogin(String user, String pass) {
        loginPage.maximizeWindow();
        loginPage.openAlternativeLoginPage();

        altLoginPage.setUsername();
        altLoginPage.setPassword();
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
    public void browseProjectsTest(String URL, String projectKey) {
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
    @DisplayName("Browse Issues")
    @CsvFileSource(resources = "/BrowseIssueData.csv", numLinesToSkip = 1)
    public void browseIssuesTest(String URL, String issueKey) {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        issueDetailPage.navigate(URL);
        issueDetailPage.verifyIssueKey(issueKey);

        mainPage.logout();
    }

    @Test
    @DisplayName("New Project Version In Glass (Empty Optional Fields)")
    public void newProjectVersionInGlassEmptyOptionalFields() {

        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.navigate(Utils.GLASS_URL);

        projectConfigPageGlass.clickOnsideBarShipIcon();
        releasesPage.setVersionName("Test PP1");
        releasesPage.clickOnAdd();
        releasesPage.clickOnNewVersionName();

        mainPage.navigate(Utils.GLASS_URL);

        projectConfigPageGlass.clickOnVersions();
        projectConfigPageGlass.verifyNewVersionName("Test PP1");
        projectConfigPageGlass.clickOnsideBarShipIcon();

        releasesPage.deleteNewTestVersion();

        mainPage.logout();
    }

    @Test
    @DisplayName("New Project Version In Glass")
    public void newProjectVersionInGlass() {

        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.navigate(Utils.GLASS_URL);

        projectConfigPageGlass.clickOnsideBarShipIcon();
        releasesPage.setVersionName("Test PP1");
        releasesPage.setStartDate("1/oct/20");
        releasesPage.setReleaseDate("31/oct/20");
        releasesPage.setDescription("Test Description");
        releasesPage.clickOnAdd();
        releasesPage.clickOnNewVersionName();

        mainPage.navigate(Utils.GLASS_URL);

        projectConfigPageGlass.clickOnVersions();
        projectConfigPageGlass.verifyNewVersionName("Test PP1");
        projectConfigPageGlass.clickOnsideBarShipIcon();

        releasesPage.deleteNewTestVersion();

        mainPage.logout();
    }

    @ParameterizedTest
    @DisplayName("Edit Issues")
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

    @Test
    @DisplayName("Glass Components")
    public void glassComponentsTest() {
        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.navigate(Utils.GLASS_URL);
        projectConfigPageGlass.clickOnSideBarComponentIcon();
        componentsPage.setComponentNameField("glass test");
        componentsPage.setDescriptionField("this is a test");
        componentsPage.setDefaultAssigneeField("Project default (Project lead)");
        componentsPage.clickAddButton();
        mainPage.navigate(Utils.GLASS_URL);
        projectConfigPageGlass.verifyNewGlassComponent();
        projectConfigPageGlass.clickOnSideBarComponentIcon();
        componentsPage.deleteComponent();
    }

    @Test
    @DisplayName("Glass Issue Type Scheme")
    public void glassIssueTypeSchemeTest() {

        loginPage.maximizeWindow();
        loginPage.openLoginPage();

        loginPage.setUsername();
        loginPage.setPassword();
        loginPage.clickLoginButton();

        mainPage.navigate(Utils.GLASS_URL);

        projectConfigPageGlass.clickOnProjectSettingButton();

        projectSettingsPage.verifyIssueTypes();

        mainPage.navigate(Utils.GLASS_URL);

        projectConfigPageGlass.clickOnSchemeTab();
        projectConfigPageGlass.verifyScheme();

        projectConfigPageGlass.clickOnIssueTypeDropdown();
        projectConfigPageGlass.verifyIssueTypes();

        mainPage.logout();
    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
        driver.quit();
    }
}