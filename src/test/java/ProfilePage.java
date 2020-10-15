import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends PageObject {

    @FindBy(id = "up-d-username")
    private WebElement username;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void verifyUsername(String user) {
        String usernameText = username.getText();
        Assert.assertEquals(user, usernameText);
    }
}
