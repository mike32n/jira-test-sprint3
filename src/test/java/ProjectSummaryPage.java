import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class ProjectSummaryPage extends PageObject {

    @FindBy(xpath = "//div[@id='summary-body']/div/div[2]/dl/dd[2]")
    WebElement keyName;

    @FindBy(xpath = "//a[@id='header-details-user-fullname']/span/span/img")
    private WebElement userIcon;

    public ProjectSummaryPage(RemoteWebDriver driver) {
        super(driver);
    }


    @Override
    protected void navigate(String URL) {
        waitForVisibility(userIcon);
        super.navigate(URL);
    }

    public void verifyKey(String key) {
        Assert.assertEquals(key, keyName.getText());
    }
}
