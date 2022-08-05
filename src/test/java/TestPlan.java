import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class TestPlan {
    private static final WebDriver driver = new ChromeDriver();

    @BeforeAll
    public static void setup() {
        // ChromeDriver location set up in Utils class
        System.setProperty(Utils.WEBDRIVER, Utils.CHROME_DRIVER_LOCATION);
    }


    private static MainPage mainPage = new MainPage(driver);


    @ParameterizedTest()
    @DisplayName("Successful Login")
    @CsvFileSource(resources = "/Employees.csv", numLinesToSkip = 1)
    public void enterData(String name, String birthday) {

        mainPage.setName(name);
        mainPage.setDate(birthday);
        mainPage.clickTestButton();

        mainPage.verifySuccess();

    }

//    @ParameterizedTest()
//    @DisplayName("Alternative Login")
//    @CsvFileSource(resources = "/LoginCredentials.csv", numLinesToSkip = 1)
//    public void alternativeLogin(String user, String pass) {
//        loginPage.maximizeWindow();
//        loginPage.openAlternativeLoginPage();
//
//        altLoginPage.setUsername(user);
//        altLoginPage.setPassword(pass);
//        altLoginPage.clickLoginButton();
//
//        mainPage.navigate(Utils.PROFILE_PAGE);
//
//        profilePage.verifyUsername(user);
//
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("Login Without Password")
//    public void loginWithoutPassword() {
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.clickLoginButton();
//
//        loginPage.verifyErrorMessage("Sorry, your username and password are incorrect - please try again.");
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("Login Without Credentials")
//    public void loginWithoutCredentials() {
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.clickLoginButton();
//
//        loginPage.verifyErrorMessage("Sorry, your username and password are incorrect - please try again.");
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("Successful Logout")
//    public void successfulLogout() {
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.logout();
//
//        mainPage.verifySuccessfulLogout();
//    }
//
//    @Test
//    @DisplayName("EMPTY Project Without Summary")
//    public void emptyProjectWithoutSummary() {
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.clickCreateButton();
//
//        createIssuePage.setProjectField("EMPTY");
//        createIssuePage.clickOnCreate();
//        createIssuePage.verifyErrorMessage();
//
//        createIssuePage.clickOnCancel();
//        createIssuePage.acceptAlert();
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("Create Issue With Required Fields Are Filled")
//    public void createIssueWithRequiredFieldsFilled() {
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.clickCreateButton();
//
//        createIssuePage.setProjectField("MTP");
//        createIssuePage.setIssueField("Task");
//        createIssuePage.setSummaryField("Testing \"Create Issue\" with all required fields are filled");
//        createIssuePage.clickOnCreate();
//        createIssuePage.clickLinkOnPopUpScreen();
//
//        issueDetailPage.verifySummary("Testing \"Create Issue\" with all required fields are filled");
//        issueDetailPage.deleteIssue();
//
//        mainPage.logout();
//    }
//
//    @ParameterizedTest()
//    @DisplayName("Issue Types For Projects")
//    @CsvFileSource(resources = "/CreateIssueData.csv", numLinesToSkip = 1)
//    public void issueTypesForProjectsTest(String projectName, String issueType,
//                                          String assertProjectName, String assertIssueType) {
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.clickCreateButton();
//
//        createIssuePage.setProjectField(projectName);
//        createIssuePage.setIssueField(issueType);
//
//        createIssuePage.verifyProjectField(assertProjectName);
//        createIssuePage.verifyIssueType(assertIssueType);
//
//        createIssuePage.clickOnCancel();
//        mainPage.logout();
//    }
//
//    @ParameterizedTest
//    @DisplayName("Browse Projects")
//    @CsvFileSource(resources = "/BrowseProjectsData.csv", numLinesToSkip = 1)
//    public void browseProjectsTest(String URL, String projectKey){
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//        projectSummaryPage.navigate(URL);
//        projectSummaryPage.verifyKey(projectKey);
//        mainPage.logout();
//    }
//
//    @ParameterizedTest
//    @CsvFileSource(resources = "/BrowseIssueData.csv", numLinesToSkip = 1)
//    public void browseIssuesTest(String URL, String issueKey){
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        issueDetailPage.navigate(URL);
//        issueDetailPage.verifyIssueKey(issueKey);
//
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("New Project Version In Glass (Empty Optional Fields)")
//    public void newProjectVersionInGlassEmptyOptionalFields() {
//
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.navigate(Utils.GLASS_URL);
//
//        projectConfigPageGlass.clickOnsideBarShipIcon();
//        releasesPage.setVersionName("Test PP1");
//        releasesPage.clickOnAdd();
//        releasesPage.clickOnNewVersionName();
//
//        mainPage.navigate(Utils.GLASS_URL);
//
//        projectConfigPageGlass.clickOnVersions();
//        projectConfigPageGlass.verifyNewVersionName("Test PP1");
//        projectConfigPageGlass.clickOnsideBarShipIcon();
//
//        releasesPage.deleteNewTestVersion();
//
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("New Project Version In Glass")
//    public void newProjectVersionInGlass() {
//
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.navigate(Utils.GLASS_URL);
//
//        projectConfigPageGlass.clickOnsideBarShipIcon();
//        releasesPage.setVersionName("Test PP1");
//        releasesPage.setStartDate("1/oct/20");
//        releasesPage.setReleaseDate("31/oct/20");
//        releasesPage.setDescription("Test Description");
//        releasesPage.clickOnAdd();
//        releasesPage.clickOnNewVersionName();
//
//        mainPage.navigate(Utils.GLASS_URL);
//
//        projectConfigPageGlass.clickOnVersions();
//        projectConfigPageGlass.verifyNewVersionName("Test PP1");
//        projectConfigPageGlass.clickOnsideBarShipIcon();
//
//        releasesPage.deleteNewTestVersion();
//
//        mainPage.logout();
//    }
//
//    @Test
//    @DisplayName("Glass Components")
//    public void glassComponentsTest(){
//        loginPage.maximizeWindow();
//        loginPage.openLoginPage();
//
//        loginPage.setUsername();
//        loginPage.setPassword();
//        loginPage.clickLoginButton();
//
//        mainPage.navigate(Utils.GLASS_URL);
//        projectConfigPageGlass.clickOnSideBarComponentIcon();
//        componentsPage.setComponentNameField("glass test");
//        componentsPage.setDescriptionField("this is a test");
//        componentsPage.setDefaultAssigneeField("Project default (Project lead)");
//        componentsPage.clickAddButton();
//        mainPage.navigate(Utils.GLASS_URL);
//        projectConfigPageGlass.verifyNewGlassComponent();
//        projectConfigPageGlass.clickOnSideBarComponentIcon();
//        componentsPage.deleteComponent();
//    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}