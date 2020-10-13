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
        projectField.sendKeys(Keys.DELETE);
        projectField.sendKeys(projectName);
        projectField.sendKeys(Keys.ENTER);
    }

    public void setIssueField(String issueType){
        issueField.sendKeys(Keys.DELETE);
        issueField.sendKeys(issueType);
        issueField.sendKeys(Keys.ENTER);
    }
/*
    @ParameterizedTest()
    @CsvFileSource(resources = "/CreateIssueData.csv", numLinesToSkip = 1)
    public void setValues(String projectName, String issueType){
        clickOnProjectField();
        setProjectField(projectName);
        clickOnIssueField();
        setIssueField(issueType);
    }
*/

    public void clickOnIssueField() {
        clickOn(issueField);
    }
}
