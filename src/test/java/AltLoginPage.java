import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AltLoginPage extends PageObject {

    @FindBy(xpath = "//*[@id='login-form-username']")
    private WebElement fieldUsername;
    @FindBy(xpath = "//*[@id='login-form-password']")
    private WebElement fieldPassword;
    @FindBy(xpath = "//*[@id='login-form-submit']")
    private WebElement loginButton;

    public AltLoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername() {
        waitForClickable(fieldUsername);
        fieldUsername.sendKeys(Utils.username);
    }

    public void setUsername(String user) {
        waitForClickable(fieldUsername);
        fieldUsername.sendKeys(user);
    }

    public void setPassword() {
        waitForClickable(fieldPassword);
        fieldPassword.sendKeys(Utils.password);
    }

    public void setPassword(String pass) {
        waitForClickable(fieldPassword);
        fieldPassword.sendKeys(pass);
    }

    public void clickLoginButton() {
        clickOn(loginButton);
    }
}
