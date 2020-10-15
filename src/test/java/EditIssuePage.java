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
        clickOn(summaryField);
    }


    public void setSummaryField(String summaryText) {
        clickOnSummaryField();
        summaryField.clear();
        summaryField.sendKeys(summaryText);
        waitForVisibility(summaryField);
    }

    public void clickOnUpdate() {
        clickOn(updateButton);
    }
}