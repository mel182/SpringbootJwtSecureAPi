package com.melchior.vrolijk.secure_api.Secure.Web.API.integrationTests.StepDefs;

import com.melchior.vrolijk.secure_api.Secure.Web.API.SecureWebApiApplication;
import com.melchior.vrolijk.secure_api.Secure.Web.API.demoObject.LocalServer;
import com.melchior.vrolijk.secure_api.Secure.Web.API.integrationTests.CucumberTests;
import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.apache.coyote.Response;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assume.assumeTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecureWebApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerStepDefs extends CucumberTests
{
    private Response response;

    @Given("^check if server is up and running for \"([^\"]*)\"$")
    public void checkIfServerIsUpAndRunningFor(String server_url) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        assumeTrue(LocalServer.isRunning());
        //response = given(null).when().get(server_url);
        //throw new PendingException();
    }

//    //the client calls /admin/all
//    @When("^the client calls /admin/all$")
//    public void the_client_issues_GET_version() throws Throwable{
//        //executeGet("http://localhost:8080/version");
//    }
//
//
//    @Then("^the client receives status code of (\\d+)$")
//    public void theClientReceivesStatusCodeOf(int arg0) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }


}
