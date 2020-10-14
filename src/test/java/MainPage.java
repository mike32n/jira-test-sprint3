import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends PageObject {

    @FindBy(id = "create_link")
    private WebElement createButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickCreateButton() {
        clickOn(createButton);
    }
}
