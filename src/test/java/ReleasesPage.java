import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReleasesPage extends PageObject {
    @FindBy(xpath = "//form[@id='releases-add__version']/div/input")
    private WebElement addVersionName;

    @FindBy(xpath = "//form[@id='releases-add__version']//button")
    private WebElement addButton;

    @FindBy(linkText = "Test PP1")
    private WebElement versionName;
    @FindBy(linkText = "Operations")
    private WebElement dots;
    @FindBy(linkText = "Delete")
    private WebElement delete;
    @FindBy(id = "submit")
    private WebElement submit;

    public ReleasesPage(WebDriver driver) {
        super(driver);
    }

    public void setVersionName(String name) {
        clickOn(addVersionName);
        addVersionName.sendKeys(name);
    }

    public void clickOnAdd() {
        clickOn(addButton);
    }

    public void clickOnNewVersionName() {
        clickOn(versionName);
    }

    public void deleteNewTestVersion() {
        clickOn(dots);
        clickOn(delete);
        clickOn(submit);
    }
}
