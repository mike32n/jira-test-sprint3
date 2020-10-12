import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        //ajax element locator
    }
    protected void clickOn(WebElement webElement) {
        waitForVisibility(webElement);
        waitForClickable(webElement);
        webElement.click();
    }

    private void waitForClickable(WebElement webElement) {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    private void waitForVisibility(WebElement webElement) throws Error {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOf(webElement));
    }
}
