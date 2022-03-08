package articleDetails;

import articleDetails.steps.ArticleDetailsSteps;
import io.restassured.response.Response;
import model.AccessTokenResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

import java.util.Collections;

import static org.hamcrest.Matchers.is;


@RunWith(SerenityRunner.class)
public class GetArticleDetailsBySearchCriteriaTests extends BaseTestClass {

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Steps
    private AccessTokenResponse accessTokenResponse;

    @Steps
    private ArticleDetailsSteps searchDetailsByCriteriaSteps;


    @Before
    public void getArticleDetailsByWrongSearchCriteria() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void searchArticleDetails () {

    //WHEN
    searchDetailsByCriteriaSteps.getArticleDetailsByCriteria("RO", "36", "salam", idamAuthorizationSteps.getToken().getAccess_token())

    //THEN
    .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void searchArticleDetailsWrongToken () {

        //WHEN
        searchDetailsByCriteriaSteps.getArticleDetailsByCriteria("RO", "36", "salam", RandomStringUtils.randomAlphanumeric(12))

        //THEN
        .then().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void searchArticleDetailsWrongCountryCode () {
        //WHEN
        searchDetailsByCriteriaSteps.getArticleDetailsByCriteria("1234", "36", "salam",idamAuthorizationSteps.getToken().getAccess_token())

        //THEN
        .then().log().all()
                .statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void searchArticleDetailsUnexistentSearchCriteria () {
        String searchCriteria = "" ;

        //WHEN
        searchDetailsByCriteriaSteps.getArticleDetailsByCriteria("RO", "36", searchCriteria,idamAuthorizationSteps.getToken().getAccess_token())

        //THEN
        .then().log().all()
        .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void searchArticleDetailsNoResultSearchCriteria () {
        String searchCriteria = "aksjdp9    rq97hfdavdf" ;

        //WHEN
        searchDetailsByCriteriaSteps.getArticleDetailsByCriteria("RO", "36", searchCriteria,idamAuthorizationSteps.getToken().getAccess_token())

                //THEN
                .then().log().all()
                .statusCode(HttpStatus.SC_OK)
                .body("results[0]", is(Collections.emptyList()));
    }

  @Test
  public void searchArticleDetailsByBundle() {
    // WHEN
      Response response =
          searchDetailsByCriteriaSteps.getArticleDetailsByBundle(
              "RO",
              "MCC",
              "36",
              "26981",
              "1",
              "1",
              "RO",
              idamAuthorizationSteps.getToken().getAccess_token());

      // THEN
      response.then().statusCode(HttpStatus.SC_OK);
        }
}
