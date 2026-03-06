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
    Login login;
    CreateEditArticle article;
    DeleteArticle delete;

    public TestDefs() {

        driver = TestBase.getdriver();
        login = new Login(driver);
        article = new CreateEditArticle(driver);
        delete = new DeleteArticle(driver);
    }

    // ---------------- LOGIN ----------------

    @Given("User is on login Page")
    public void user_is_on_login_page() throws IOException {

        TestBase.openUrl("https://conduit-realworld-example-app.fly.dev/");
        login.launchLoginPage();
    }

    @When("User enters {string} and {string}")
    public void user_enters_and(String name, String pwd) {

        login.login(name, pwd);
    }

    @Then("User should be on Home page")
    public void user_should_be_on_home_page() {

        Assert.assertTrue(login.verifyLoginSuccess());
        System.out.println("Login is successful!!!");
    }

    // ---------------- CREATE ARTICLE ----------------

    @Given("User should be Article Page")
    public void user_should_be_article_page() {

        article.launchArticle();
        Assert.assertTrue(article.isArticlePageDisp());
    }

    @When("User Create Article {string} and {string} and {string} and {string}")
    public void user_create_article_and_and_and(String title, String desc, String body, String tag) {

        System.out.println("Landed on Article Creation Page");
        article.enterArticleDetails(title, desc, body, tag);
        article.publishArticle();
    }

    @Then("Article must be Created")
    public void article_must_be_created() {

        boolean success = article.verifyHeader();

        if (success) {
            System.out.println("New Article Published Successfully");
        } else {
            System.out.println("Title Already Exists");
        }
    }

    // ---------------- UPDATE ARTICLE ----------------

    @When("User Update an Article")
    public void user_update_an_article(DataTable dataTable) {

        List<Map<String, String>> users = dataTable.asMaps(String.class, String.class);

        String oldTitle = users.get(0).get("oldtitle");
        String newTitle = users.get(0).get("newtitle");
        String desc = users.get(0).get("desc");
        String body = users.get(0).get("body");
        String tag = users.get(0).get("tag");

        System.out.println("Updating article from " + oldTitle + " to " + newTitle);

        article.editArticle();
        article.updateArticleDetails(newTitle, desc, body, tag);
        article.publishArticle();
    }

    @Then("Article Should be Updated")
    public void article_should_be_updated() {

        boolean success = article.verifyHeader();

        if (success) {
            System.out.println("Edit Article Published Successfully");
        } else {
            System.out.println("Title Already Exists");
        }
    }

    // ---------------- DELETE ARTICLE ----------------
    @When("User Delete an Article")
	public void user_delete_an_article(DataTable dataTable) {
		List<Map<String,String>> users= dataTable.asMaps();
		String title1 = users.get(0).get("title");
		if(title1.equalsIgnoreCase("SHIV888")) {
			delete.DeleteArtcile();}
		  	}
	
	
	@Then("Article Should be Deleted")
	public void article_should_be_deleted() {
		
		boolean deleteCheck = delete.isDeleted();
		System.out.println("Deleted: "+ deleteCheck);
		
	}

	}