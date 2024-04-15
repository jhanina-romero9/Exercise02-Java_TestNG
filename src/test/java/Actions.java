import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;

public class Actions {
    public void SendText(WebElement element, String value)
    {
        element.sendKeys(value);
    }

    public void Click(WebElement element)
    {
        element.click();
    }

    public void ClickWhenEnabled(WebDriver driver, WebElement element)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
