package getArticleDetails;

import articleDetails.steps.ArticleDetailsByBundleSteps;
import articleDetails.steps.ArticleDetailsByCriteriaSteps;
import io.restassured.response.Response;
import model.AccessTokenResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

@RunWith(SerenityRunner.class)
public class GetArticleDetailsBySearchCriteriaTests extends BaseTestClass {

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Steps
    private AccessTokenResponse accessTokenResponse;

    @Steps
    private ArticleDetailsByCriteriaSteps searchDetailsByCriteriaSteps;

    @Steps
    private ArticleDetailsByBundleSteps articleDetailsByBundleSteps;

    @Before
    public void getArticleDetailsByWrongSearchCriteria () {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void searchArticleDetails () {

    //WHEN
    searchDetailsByCriteriaSteps.getArticleDetailsByCriteria("RO", "36", "salam",idamAuthorizationSteps.getToken().getAccess_token())

    //THEN
            .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void searchArticleDetailsByBundle() {

        //WHEN
       Response response = articleDetailsByBundleSteps.getArticleDetailsByBundle("RO","MCC","36",
                "26981","1","1","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
             response
                     .then().log().all().and().
                     statusCode(HttpStatus.SC_FORBIDDEN);
        String header = response.getHeader("WWW-Authenticate");
        assert header.contains("The request requires higher privileges than provided by the access token.");
    }
}
