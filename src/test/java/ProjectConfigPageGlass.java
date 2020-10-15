import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectConfigPageGlass extends PageObject{

    @FindBy(xpath = "//*[@id='content']//nav/div/div[2]/ul/li[3]/a")
    private WebElement sideBarShipIcon;
    @FindBy(xpath = "//*[@id='aui-uid-2']")
    private WebElement versionsTab;
    @FindBy(linkText = "Test PP1")
    private WebElement versionName;

    public ProjectConfigPageGlass(WebDriver driver) {
        super(driver);
    }

    public void clickOnsideBarShipIcon() {
        clickOn(sideBarShipIcon);
    }

    public void clickOnVersions() {
        clickOn(versionsTab);
    }

    public void verifyNewVersionName(String name) {
        waitForClickable(versionName);
        Assert.assertEquals(name, versionName.getText());
    }
}
