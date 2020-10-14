import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IssueDetailPage extends PageObject {

    @FindBy(id = "summary-val")
    private WebElement summary;

    @FindBy(xpath = "//a[@id='opsbar-operations_more']/span")
    private WebElement more;

    @FindBy(xpath = "//aui-item-link[@id='delete-issue']/a/span")
    private WebElement delete;

    @FindBy(id = "delete-issue-submit")
    private WebElement confirmDelete;

    public IssueDetailPage(WebDriver driver) {
        super(driver);
    }

    public void verifySummary(String summaryText) {
        waitForVisibility(summary);
        String issueSummary = summary.getText();
        Assert.assertEquals(issueSummary, summaryText);
    }

    public void deleteIssue() {
        clickOn(more);
        clickOn(delete);
        clickOn(confirmDelete);
    }
}
