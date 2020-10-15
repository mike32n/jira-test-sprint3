import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ComponentsPage extends PageObject{

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

    public ComponentsPage(WebDriver driver) {
        super(driver);
    }

    public void setComponentNameField(String componentName){
        waitForClickable(componentNameField);
        componentNameField.sendKeys(componentName);
    }

    public void setDescriptionField(String descriptionName){
        waitForClickable(descriptionField);
        descriptionField.sendKeys(descriptionName);
    }

    public void setDefaultAssigneeField(String defaultAssignee){
        waitForClickable(defaultAssigneeField);
        defaultAssigneeField.sendKeys(defaultAssignee);
        defaultAssigneeField.sendKeys(Keys.TAB);
    }

    public void clickAddButton(){
        waitForClickable(addButton);
        clickOn(addButton);
    }

    public void deleteComponent() {
        componentFilter.sendKeys("glass test");
        waitForClickable(dots);
        dots.click();
        deleteButton.click();
        waitForClickable(submitButton);
        submitButton.click();/*
        driver.findElement(By.xpath("//input[@id='component-filter-text']")).sendKeys("glass test");
        driver.findElement(By.cssSelector(".item-state-ready:nth-child(1) .aui-button")).click();
        driver.findElement(By.linkText("Delete")).click();
        driver.findElement(By.xpath("//input[@id='submit']")).click();*/
    }
}
