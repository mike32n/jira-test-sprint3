import org.junit.Assert;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//*[@id='content']/div[1]/div/div[1]/nav/div/div[2]/ul/li[6]/a/span[1]")
    private WebElement sideBarComponentIcon;

    public ProjectConfigPageGlass(WebDriver driver) {
        super(driver);
    }

    public void clickOnsideBarShipIcon() {
        clickOn(sideBarShipIcon);
    }

    public void clickOnVersions() {
        clickOn(versionsTab);
    }

    public void clickOnSideBarComponentIcon(){
        clickOn(sideBarComponentIcon);
    }

    public void verifyNewVersionName(String name) {
        waitForClickable(versionName);
        Assert.assertEquals(name, versionName.getText());
    }

    public void verifyNewGlassComponent() {
        Assert.assertEquals(driver.findElement(By.xpath("//td[contains(.,'glass test')]")).getText(),"glass test");
    }
}
