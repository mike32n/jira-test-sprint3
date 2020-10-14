import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectSummaryPage extends PageObject {

    @FindBy(xpath = "//div[@id='summary-body']/div/div[2]/dl/dd[2]")
    WebElement keyName;

    public ProjectSummaryPage(WebDriver driver) {
        super(driver);
    }


    @Override
    protected void navigate(String URL) {
        super.navigate(URL);
    }

    public void verifyKey(String key){
        Assert.assertEquals(key, keyName.getText());
    }
}
