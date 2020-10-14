import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends PageObject {

    @FindBy(id = "login-form-username")
    private WebElement fieldUsername;
    @FindBy(id = "login-form-password")
    private WebElement fieldPassword;
    @FindBy(id = "login")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void maximizeWindow() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public void openLoginPage() {
        driver.get(Utils.BASE_URL);
    }

    public void setUsername() {
        waitForClickable(fieldUsername);
        fieldUsername.sendKeys(Utils.username);
    }

    public void setPassword() {
        waitForClickable(fieldPassword);
        fieldPassword.sendKeys(Utils.password);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }
}
