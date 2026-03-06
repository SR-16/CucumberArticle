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

public class TestDefs {

    WebDriver driver;

    Login loginPage;
    CreateEditArticle articlePage;
    DeleteArticle deletePage;

    public TestDefs() {

        driver = TestBase.getdriver();

        loginPage = new Login(driver);
        articlePage = new CreateEditArticle(driver);
        deletePage = new DeleteArticle(driver);
    }

    // ---------------- LOGIN SCENARIO ----------------

    @Given("User is on login Page")
    public void user_is_on_login_page() throws IOException {

        TestBase.openUrl("https://conduit-realworld-example-app.fly.dev/");

        System.out.println("Opening Login Page");

        loginPage.launchLoginPage();
    }

    @When("User enters {string} and {string}")
    public void user_enters_and(String username, String password) {

        System.out.println("Entering login credentials");

        loginPage.login(username, password);
    }

    @Then("User should be on Home page")
    public void user_should_be_on_home_page() {

        boolean loginStatus = loginPage.verifyLoginSuccess();

        Assert.assertTrue(loginStatus, "Login Failed!");

        System.out.println("Login successful. User is on Home Page.");
    }

    // ---------------- CREATE ARTICLE ----------------

    @Given("User should be Article Page")
    public void user_should_be_article_page() {

        articlePage.launchArticle();

        boolean pageOpened = articlePage.isArticlePageDisp();

        Assert.assertTrue(pageOpened, "Article page did not open!");

        System.out.println("Navigated to Article Creation Page");
    }

    @When("User Create Article {string} and {string} and {string} and {string}")
    public void user_create_article_and_and_and(String title, String description, String body, String tag) {

        System.out.println("Entering article details");

        articlePage.enterArticleDetails(title, description, body, tag);

        articlePage.publishArticle();
    }

    @Then("Article must be Created")
    public void article_must_be_created() {

        boolean articleCreated = articlePage.verifyHeader();

        Assert.assertTrue(articleCreated, "Article creation failed!");

        System.out.println("New Article Published Successfully");
    }

    // ---------------- UPDATE ARTICLE ----------------

    @When("User Update an Article")
    public void user_update_an_article(DataTable dataTable) {

        List<Map<String, String>> articleData = dataTable.asMaps(String.class, String.class);

        String oldTitle = articleData.get(0).get("oldtitle");
        String newTitle = articleData.get(0).get("newtitle");
        String description = articleData.get(0).get("desc");
        String body = articleData.get(0).get("body");
        String tag = articleData.get(0).get("tag");

        System.out.println("Updating Article from: " + oldTitle + " to: " + newTitle);

        articlePage.editArticle();

        articlePage.updateArticleDetails(newTitle, description, body, tag);

        articlePage.publishArticle();
    }

    @Then("Article Should be Updated")
    public void article_should_be_updated() {

        boolean articleUpdated = articlePage.verifyHeader();

        Assert.assertTrue(articleUpdated, "Article update failed!");

        System.out.println("Article Updated Successfully");
    }

    // ---------------- DELETE ARTICLE ----------------

    @When("User Delete an Article")
    public void user_delete_an_article(DataTable dataTable) {

        List<Map<String, String>> articleData = dataTable.asMaps(String.class, String.class);

        String articleTitle = articleData.get(0).get("title");

        System.out.println("Deleting article: " + articleTitle);

        if (articleTitle.equalsIgnoreCase("SHIV888")) {

            deletePage.DeleteArticle1();
        }
    }

    @Then("Article Should be Deleted")
    public void article_should_be_deleted() {

        boolean deletedStatus = deletePage.isDeleted();

        Assert.assertTrue(deletedStatus, "Article deletion failed!");

        System.out.println("Article Deleted Successfully");
    }

}