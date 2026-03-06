package pages;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateEditArticle {

    // Link to open "New Article" page
    @FindBy(xpath = "//a[contains(.,'New Article')]")
    WebElement newArt;

    // Article input fields
    @FindBy(xpath = "//input[@name='title']")
    WebElement titleEle;

    @FindBy(xpath = "//input[@name='description']")
    WebElement descEle;

    @FindBy(xpath = "//textarea[@name='body']")
    WebElement bodyEle;

    @FindBy(xpath = "//input[@name='tags']")
    WebElement tagEle;

    // Publish button
    @FindBy(xpath = "//button[@type='submit']")
    WebElement publish;

    // Error message container
    @FindBy(xpath = "//span[@class='error-messages']")
    List<WebElement> errormsg;

    // Article title/header after publishing
    @FindBy(xpath = "//h1")
    WebElement header;

    // Edit and update buttons
    @FindBy(xpath = "//a[contains(.,'Edit Article')]")
    WebElement editArticle;

    @FindBy(xpath = "//button[contains(.,'Update Article')]")
    WebElement updatetArticle;

    // Constructor for PageFactory initialization
    public CreateEditArticle(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Opens the new article creation page
    public void launchArticle() {
        newArt.click();
    }

    // Checks whether article page is visible
    public boolean isArticlePageDisp() {
        return newArt.isDisplayed();
    }

    // Enter article details for publishing
    public void enterArticleDetails(String title, String desc, String body, String tag) {

        titleEle.clear();
        titleEle.sendKeys(title);

        descEle.clear();
        descEle.sendKeys(desc);

        bodyEle.clear();
        bodyEle.sendKeys(body);

        tagEle.clear();
        tagEle.sendKeys(tag);
    }

    // Update article details during edit
    public void updateArticleDetails(String title1, String desc1, String body1, String tag1) {

        titleEle.clear();
        titleEle.sendKeys(title1);

        descEle.clear();
        descEle.sendKeys(desc1);

        bodyEle.clear();
        bodyEle.sendKeys(body1);

        tagEle.clear();
        tagEle.sendKeys(tag1);
    }

    // Submit the article
    public void publishArticle() {
        publish.submit();
    }

    // Verify if article header is displayed after publish/update
    public boolean verifyHeader() {
        try {
            return header.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // Check if any error message appears
    public boolean checkErr() {

        List<WebElement> errors = errormsg;

        if (!errors.isEmpty()) {
            System.out.println("Error message: " + errors.get(0).getText());
            return true;
        } else {
            System.out.println("No error message displayed.");
            return false;
        }
    }

    // Click edit article button
    public void editArticle() {
        editArticle.click();
    }

    // Click update article button
    public void updatetArticle() {
        updatetArticle.click();
    }
}