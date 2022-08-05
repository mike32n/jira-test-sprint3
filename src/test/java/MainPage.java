import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    @FindBy(id = "name")
    private WebElement fieldName;


    @FindBy(id = "date")
    private WebElement fieldDate;

    @FindBy(xpath = "//form//button")
    private WebElement testButton;

    @FindBy(xpath = "//h3")
    private WebElement feedbackText;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickTestButton() {
        clickOn(testButton);
    }

    @Override
    protected void navigate(String URL) {
        super.navigate(URL);
    }

    public void setName(String name) {
        waitForClickable(fieldName);
        fieldName.sendKeys(name);
    }

    public void setDate(String date) {
        waitForClickable(fieldDate);
        fieldDate.sendKeys(date);
    }

    public void verifySuccess() {
        waitForVisibility(feedbackText);
        Assert.assertTrue(feedbackText.getText().startsWith("Siker!"));
    }
}
