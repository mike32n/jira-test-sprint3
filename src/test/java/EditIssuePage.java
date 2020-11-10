import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditIssuePage extends PageObject {


    @FindBy(xpath = "//*[@id='edit-issue-submit']")
    private WebElement updateButton;

    @FindBy(xpath = "//*[@id='summary']")
    private WebElement summaryField;

    @FindBy(xpath = "//*[@id='stalker']/div/header/div/div[3]")
    private WebElement updateModal;

    public EditIssuePage(WebDriver driver) {
        super(driver);
    }

    public void setSummaryField(String summaryText) {
        waitForClickable(summaryField);
        summaryField.clear();
        summaryField.sendKeys(summaryText);

    }

    public void clickOnUpdate() {
        clickOn(updateButton);
    }

    public void waitForModal() {
        waitForVisibility(updateModal);
    }
}