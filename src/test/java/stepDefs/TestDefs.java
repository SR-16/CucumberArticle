package stepDefs;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import base.TestBase;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CreateEditArticle;
import pages.DeleteArticle;
import pages.Login;

/*
 * Step Definition class
 * This class contains all step implementations for the
 * Cucumber Feature file (Login, Create Article, Update Article, Delete Article)
 */

public class TestDefs {

    WebDriver driver;
    Login login;
    CreateEditArticle article;
    DeleteArticle delete;

    /*
     * Constructor initializes driver and page objects
     */
    public TestDefs() {

        driver = TestBase.getdriver();

        login = new Login(driver);
        article = new CreateEditArticle(driver);
        delete = new DeleteArticle(driver);
    }

    // ===================== LOGIN TEST =====================

    /*
     * Opens the Conduit website and launches login page
     */
    @Given("User is on login Page")
    public void user_is_on_login_page() throws IOException {

        TestBase.openUrl("https://conduit-realworld-example-app.fly.dev/");
        login.launchLoginPage();

        System.out.println("Login page opened successfully");
    }

    /*
     * Enter username and password
     */
    @When("User enters {string} and {string}")
    public void user_enters_and(String name, String pwd) {

        login.login(name, pwd);
        System.out.println("Credentials entered");
    }

    /*
     * Verify login success
     */
    @Then("User should be on Home page")
    public void user_should_be_on_home_page() {

        Assert.assertTrue(login.verifyLoginSuccess(), "Login Failed!");

        System.out.println("Login is successful!!!");
    }


    // ===================== CREATE ARTICLE =====================

    /*
     * Navigate to article creation page
     */
    @Given("User should be Article Page")
    public void user_should_be_article_page() {

        article.launchArticle();

        // Assertion to confirm user is on article page
        Assert.assertTrue(article.isArticlePageDisp(), "Article Page not displayed!");

        System.out.println("User is on Article Creation Page");
    }

    /*
     * Enter article details and publish article
     */
    @When("User Create Article {string} and {string} and {string} and {string}")
    public void user_create_article_and_and_and(String title, String desc, String body, String tag) {

        System.out.println("Entering Article Details");

        article.enterArticleDetails(title, desc, body, tag);
        article.publishArticle();
    }

    /*
     * Verify article creation success
     */
    @Then("Article must be Created")
    public void article_must_be_created() {

        boolean success = article.verifyHeader();

        // Assertion to verify article creation
        Assert.assertTrue(success, "Article Creation Failed!");

        System.out.println("New Article Published Successfully");
    }


    // ===================== UPDATE ARTICLE =====================

    /*
     * Update existing article using DataTable
     */
    @When("User Update an Article")
    public void user_update_an_article(DataTable dataTable) {

        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        String oldTitle = users.get(0).get("oldtitle");
        String newTitle = users.get(0).get("newtitle");
        String desc = users.get(0).get("desc");
        String body = users.get(0).get("body");
        String tag = users.get(0).get("tag");

        System.out.println("Updating Article from: " + oldTitle + " to: " + newTitle);

        article.editArticle();
        article.updateArticleDetails(newTitle, desc, body, tag);
        article.publishArticle();
    }

    /*
     * Verify article update
     */
    @Then("Article Should be Updated")
    public void article_should_be_updated() {

        boolean success = article.verifyHeader();

        // Assertion to confirm update success
        Assert.assertTrue(success, "Article Update Failed!");

        System.out.println("Article Updated Successfully");
    }


    // ===================== DELETE ARTICLE =====================

    /*
     * Delete article using DataTable input
     */
    @When("User Delete an Article")
    public void user_delete_an_article(DataTable dataTable) {

        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        String title1 = users.get(0).get("title");

        System.out.println("Attempting to delete article: " + title1);

        if (title1.equalsIgnoreCase("SHIV888")) {

            delete.DeleteArtcile();
        }
    }

    /*
     * Verify article deletion
     */
    @Then("Article Should be Deleted")
    public void article_should_be_deleted() {

        boolean deleteCheck = delete.isDeleted();

        // Assertion to confirm deletion
        Assert.assertTrue(deleteCheck, "Article Deletion Failed!");

        System.out.println("Article Deleted Successfully");
    }

}