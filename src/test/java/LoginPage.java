import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class LoginPage extends PageObject {

    @FindBy(xpath = "//*[@id='login-form-username']")
    private WebElement fieldUsername;
    @FindBy(id = "login-form-password")
    private WebElement fieldPassword;
    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='usernameerror']/p")
    private WebElement errorText;

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

    public void openAlternativeLoginPage() {
        driver.get(Utils.ALTERNATIVE_LOGIN_PAGE);
    }

    public void verifyErrorMessage(String error) {
        waitForVisibility(errorText);
        Assert.assertEquals(error, errorText.getText());
    }
}
