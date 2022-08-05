import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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


    private static MainPage mainPage = new MainPage(driver);


    @ParameterizedTest()
    @DisplayName("Successful Login")
    @CsvFileSource(resources = "/Employees.csv", numLinesToSkip = 1)
    public void enterData(String name, String birthday) {
        mainPage.maximizeWindow();

        mainPage.openMainPage();

        mainPage.setName(name);
        mainPage.setDate(birthday);
        mainPage.clickTestButton();

        mainPage.verifySuccess();

    }

    @AfterAll
    public static void cleanUp() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}