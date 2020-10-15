import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditIssuePage extends PageObject{


    @FindBy(xpath = "//*[@id='edit-issue-submit']")
    private WebElement updateButton;

    @FindBy(xpath = "//*[@id='summary']")
    private WebElement summaryField;

    public EditIssuePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnSummaryField() {
        waitForVisibility(updateButton);
        clickOn(summaryField);
        waitForVisibility(updateButton);
    }


    public void setSummaryField(String summaryText) {
        waitForClickable(summaryField);
        summaryField.clear();
        summaryField.sendKeys(summaryText);

    }

    public void clickOnUpdate() {
        clickOn(updateButton);
    }
}