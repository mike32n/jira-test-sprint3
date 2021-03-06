import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssuePage extends PageObject {

    @FindBy(xpath = "//*[@id='project-field']")
    private WebElement projectField;

    @FindBy(xpath = "//*[@id='issuetype-field']")
    private WebElement issueField;

    @FindBy(xpath = "//*[@id='summary']")
    private WebElement summaryField;

    @FindBy(xpath = "//*[@id='create-issue-submit']")
    private WebElement create;

    @FindBy(css = ".error")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[@id='create-issue-dialog']//a[contains(text(), 'Cancel')]")
    private WebElement cancel;

    @FindBy(xpath = "//div[@id='aui-flag-container']/div/div/a")
    private WebElement popUpScreen;

    @FindBy(xpath = "//*[@id='description']")
    private WebElement description;

    public CreateIssuePage(WebDriver driver) {
        super(driver);
    }

    public void setProjectField(String projectName) {
        clickOn(projectField);
        projectField.sendKeys(Keys.DELETE);
        waitForClickable(projectField);
        projectField.sendKeys(projectName);
        waitForClickable(projectField);
        projectField.sendKeys(Keys.ENTER);
        clickOnSummaryField();
    }

    public void setIssueField(String issueType) {
        clickOn(issueField);
        issueField.sendKeys(Keys.DELETE);
        waitForClickable(issueField);
        issueField.sendKeys(issueType);
        waitForClickable(issueField);
        issueField.sendKeys(Keys.ENTER);
        clickOnSummaryField();
    }

    public void verifyProjectField(String projectName) {
        String projectNameValue = projectField.getAttribute("value");
        Assert.assertEquals(projectName, projectNameValue);
    }

    public void verifyIssueType(String issueType) {
        String issueTypeValue = issueField.getAttribute("value");
        Assert.assertEquals(issueType, issueTypeValue);
    }

    public void clickOnSummaryField() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
        clickOn(summaryField);
    }

    public void clickOnCreate() {
        clickOn(create);
    }

    public void verifyErrorMessage() {
        waitForVisibility(errorMessage);
        String errorMessageText = errorMessage.getText();
        Assert.assertEquals(errorMessageText, "You must specify a summary of the issue.");
    }

    public void clickOnCancel() {
        clickOn(cancel);
        try {
            acceptAlert();
        } catch (Exception ignore) {
        }
    }

    public void setSummaryField(String summaryText) {
        clickOnSummaryField();
        summaryField.sendKeys(summaryText);
    }

    public void clickLinkOnPopUpScreen() {
        clickOn(popUpScreen);
    }
}

