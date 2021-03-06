import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ComponentsPage extends PageObject {

    @FindBy(xpath = "//*[@id='components-add__component']/div[1]/input")
    private WebElement componentNameField;

    @FindBy(xpath = "//*[@id='components-add__component']/div[3]/input")
    private WebElement descriptionField;

    @FindBy(xpath = "//*[@id='assigneeType-field']")
    private WebElement defaultAssigneeField;

    @FindBy(xpath = "//*[@id='components-add__component']/div[5]/button")
    private WebElement addButton;

    @FindBy(xpath = "//input[@id='component-filter-text']")
    private WebElement componentFilter;

    @FindBy(xpath = "//*[@id='components-table']/tbody[2]/tr/td[6]/div/a/span")
    private WebElement dots;

    @FindBy(linkText = "Delete")
    private WebElement deleteButton;

    @FindBy(xpath = "//input[@id='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='components-add__component']/div[1]/div")
    private WebElement errorMessage;

    public ComponentsPage(WebDriver driver) {
        super(driver);
    }

    public void setComponentNameField(String componentName) {
        waitForClickable(componentNameField);
        componentNameField.sendKeys(Keys.DELETE);
        componentNameField.sendKeys(componentName);
    }

    public void setDescriptionField(String descriptionName) {
        waitForClickable(descriptionField);
        descriptionField.sendKeys(descriptionName);
    }

    public void setDefaultAssigneeField(String defaultAssignee) {
        waitForClickable(defaultAssigneeField);
        defaultAssigneeField.sendKeys(defaultAssignee);
        defaultAssigneeField.sendKeys(Keys.TAB);
    }

    public void clickAddButton() {
        waitForClickable(addButton);
        clickOn(addButton);
    }

    public void deleteComponent() {
        componentFilter.sendKeys("glass test");
        waitForClickable(dots);
        dots.click();
        deleteButton.click();
        waitForClickable(submitButton);
        submitButton.click();
    }

    public boolean isError() {
        return errorMessage.isDisplayed();
    }

    public void emptySearchField() {
        componentFilter.clear();
    }

    public void emptyComponentField() {
        componentNameField.clear();
    }
}
