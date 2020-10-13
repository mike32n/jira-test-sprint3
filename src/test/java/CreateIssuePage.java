import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuePage extends PageObject {

    @FindBy(id = "project-field")
    private WebElement projectField;

    @FindBy(id = "issuetype-field")
    private WebElement issueField;

    public CreateIssuePage(WebDriver driver) {
        super(driver);
    }

    public void clickOnProjectField(){
        clickOn(projectField);
    }

    public void setProjectField(String projectName){
        waitForClickable(issueField);
        projectField.sendKeys(Keys.DELETE);
        waitForClickable(issueField);
        projectField.sendKeys(projectName);
        waitForClickable(projectField);
        projectField.sendKeys(Keys.ENTER);
        waitForClickable(issueField);
    }

    public void setIssueField(String issueType){
        waitForClickable(issueField);
        issueField.sendKeys(Keys.DELETE);
        waitForClickable(issueField);
        issueField.sendKeys(issueType);
        waitForClickable(issueField);
        issueField.sendKeys(Keys.ENTER);
        waitForClickable(issueField);
    }

    public void clickOnIssueField() {
        clickOn(issueField);
    }

    public void verifyProjectField(String projectName){
        Assert.assertTrue(driver.getPageSource().contains(projectName));
    }

    public void verifyIssueType(String issueType){
        Assert.assertTrue(driver.getPageSource().contains(issueType));
    }

}
