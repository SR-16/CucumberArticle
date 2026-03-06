package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteArticle {

    WebDriver driver;
    WebDriverWait wait;

    // Delete article button (first article in list)
    @FindBy(xpath = "(//button[contains(.,'Delete Article')])[1]")
    WebElement deleteBtn;

    // List of article previews (used to confirm deletion)
    @FindBy(xpath = "//div[@class='article-preview']")
    List<WebElement> deleteSucc;

    // Constructor – initialize driver and PageFactory elements
    public DeleteArticle(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Performs article deletion and handles confirmation alert
    public void DeleteArticle1() {

        deleteBtn.click();

        // Wait until alert appears
        Alert confirmationAlert = wait.until(ExpectedConditions.alertIsPresent());

        String actualAlertText = confirmationAlert.getText();
        String expectedAlertText = "Want to delete the article?";

        // Simple alert validation
        if (actualAlertText.equals(expectedAlertText)) {
            System.out.println("Alert message verified: " + actualAlertText);
        } else {
            System.out.println("Alert message mismatch!");
        }

        // Accept alert to confirm deletion
        confirmationAlert.accept();
    }

    // Verify if article list still contains items
    public boolean isDeleted() {

        List<WebElement> elements = deleteSucc;

        if (elements.isEmpty()) {
            return false;
        }

        return true;
    }
}