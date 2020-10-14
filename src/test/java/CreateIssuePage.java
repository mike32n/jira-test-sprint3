import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuePage extends PageObject {

    @FindBy(id = "project-field")
    private WebElement projectField;

    @FindBy(id = "issuetype-field")
    private WebElement issueField;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "create-issue-submit")
    private WebElement create;

    @FindBy(css = ".error")
    private WebElement errorMessage;

    @FindBy(linkText = "Cancel")
    private WebElement cancel;

    @FindBy(xpath = "//a[@id='header-details-user-fullname']/span/span/img")
    private WebElement userIcon;

    @FindBy(id = "log_out")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@id='aui-flag-container']/div/div/a")
    private WebElement popUpScreen;

    public CreateIssuePage(WebDriver driver) {
        super(driver);
    }

//    public void clickOnProjectField() {
//        clickOn(projectField);
//    }

    //    public void setProjectField(String projectName) {
//        waitForClickable(issueField);
//        projectField.sendKeys(Keys.DELETE);
//        waitForClickable(issueField);
//        projectField.sendKeys(projectName);
//        waitForClickable(projectField);
//        projectField.sendKeys(Keys.ENTER);
//        waitForClickable(issueField);
//    }
    public void setProjectField(String projectName) {
        clickOn(projectField);
        waitForClickable(projectField);
        projectField.sendKeys(Keys.DELETE);
        waitForClickable(projectField);
        projectField.sendKeys(projectName);
        waitForClickable(projectField);
        projectField.sendKeys(Keys.ENTER);
        clickOnSummaryField();
        waitForClickable(projectField);
    }

    public void setIssueField(String issueType) {
        clickOn(issueField);
        waitForClickable(issueField);
        issueField.sendKeys(Keys.DELETE);
        waitForClickable(issueField);
        issueField.sendKeys(issueType);
        waitForClickable(issueField);
        issueField.sendKeys(Keys.ENTER);
        clickOnSummaryField();
        waitForClickable(issueField);
    }

//    public void clickOnIssueField() {
//        clickOn(issueField);
//    }

    public void verifyProjectField(String projectName) {
        Assert.assertTrue(driver.getPageSource().contains(projectName));
    }

    public void verifyIssueType(String issueType) {
        Assert.assertTrue(driver.getPageSource().contains(issueType));
    }

    public void clickOnSummaryField() {
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
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void logout() {
        clickOn(userIcon);
        clickOn(logoutButton);
    }

    public void setSummaryField(String summaryText) {
        clickOnSummaryField();
        summaryField.sendKeys(summaryText);
    }

    public void clickLinkOnPopUpScreen() {
        clickOn(popUpScreen);
    }
}
