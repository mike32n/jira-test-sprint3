import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    @FindBy(id = "name")
    private WebElement name;


    @FindBy(id = "date")
    private WebElement date;

    @FindBy(xpath = "//form//button")
    private WebElement button;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateButton() {
        clickOn(createButton);
    }

    @Override
    protected void navigate(String URL) {
        waitForVisibility(userIcon);
        super.navigate(URL);
    }

    public void logout() {
        clickOn(userIcon);
        clickOn(logoutButton);
    }

    public void verifySuccessfulLogout() {
        waitForVisibility(logoutMessage);
        Assert.assertTrue(logoutMessage.getText().startsWith("You are now logged out."));
    }
}
