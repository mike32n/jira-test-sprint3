import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class IssueDetailPage extends PageObject {

    @FindBy(xpath = "//*[@id='summary-val']")
    private WebElement summary;

    @FindBy(xpath = "//a[@id='opsbar-operations_more']/span")
    private WebElement more;

    @FindBy(xpath = "//aui-item-link[@id='delete-issue']/a/span")
    private WebElement delete;

    @FindBy(id = "delete-issue-submit")
    private WebElement confirmDelete;

    @FindBy(xpath = "//*[@id='key-val']")
    private WebElement issueKeycode;

    @FindBy(xpath = "//a[@id='header-details-user-fullname']/span/span/img")
    private WebElement userIcon;

    @FindBy(xpath = "//*[@id='edit-issue']")
    private WebElement editButton;

    @FindBy(xpath = "//*[@id='aui-flag-container']/div/div")
    private WebElement popUpScreen;

    @FindBy(xpath = "//*[@id='project-avatar']")
    private WebElement projectIcon;

    public IssueDetailPage(WebDriver driver) {
        super(driver);
    }

    public void verifySummary(String summaryText) throws InterruptedException {
        waitForVisibility(summary);
        waitForVisibility(projectIcon);
        waitForVisibility(editButton);
        waitForLoad(driver);
        String issueSummary = summary.getText();
        waitForVisibility(summary);
        waitForVisibility(projectIcon);
        waitForVisibility(editButton);
        waitForLoad(driver);
        Assert.assertEquals(summaryText, issueSummary);
        waitForVisibility(summary);
        waitForVisibility(projectIcon);
        waitForVisibility(editButton);
        waitForLoad(driver);
    }

    public void deleteIssue() {
        clickOn(more);
        clickOn(delete);
        clickOn(confirmDelete);
    }

    @Override
    protected void navigate(String URL) {
        waitForVisibility(userIcon);
        super.navigate(URL);
    }

    public void verifyIssueKey(String issueKey){
        String issue = issueKeycode.getText();
        Assert.assertEquals(issueKey, issue);
    }

    public void clickOnEdit() {
        clickOn(editButton);
    }
}
