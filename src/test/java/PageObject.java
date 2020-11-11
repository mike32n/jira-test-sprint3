import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;

public class PageObject {
    protected WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

        //ajax element locator
    }

    protected void clickOn(WebElement webElement) {
        waitForClickable(webElement);
        Actions builder = new Actions(driver);
        Actions seriesOfActions = builder
                .moveToElement(webElement)
                .click();
//        webElement.click();
        seriesOfActions.perform() ;
    }

    protected void waitForClickable(WebElement webElement) {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitForVisibility(WebElement webElement) throws Error {
        new WebDriverWait(driver, 2)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void navigate(String URL) {
        driver.get(URL);
    }

    public void moveMouseToCorner() throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(0,0);
    }

    public void acceptAlert() {
        new WebDriverWait(driver, 1).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }
}

