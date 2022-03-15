package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.model.SimilarArticlesStockMovement;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;
import static org.hamcrest.Matchers.*;
import java.util.Collections;

public class PostSimilarArticlesStockMovementTests extends BaseTestClass {

  @Steps private StockCheckSteps stockCheckSteps;

  @Steps private IdamAuthorizationSteps idamAuthorizationSteps;

  @Before
  public void getToken() {
    idamAuthorizationSteps.getAccessToken();
  }


  @Test
  public void postSimilarArticlesStockMovement() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
            new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setArticleNumber(25054);
    stockMovement.setStockCheckId("6fbebd25-8446-45b1-882c-880476ed11c0");
    stockMovement.setStockCorrectionTypeCode("MAINTENANCE");

    // WHEN
    Response response =
            stockCheckSteps.postSimilarArticlesStockMovement(
                    "RO",
                    "MCC",
                    "36",
                    idamAuthorizationSteps.getToken().getAccess_token(),
                    Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_OK);
  }

  @Test
  public void postSimilarArticlesStockMovementNoStockCorrectionType() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
        new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setArticleNumber(25054);
    stockMovement.setStockCheckId("6fbebd25-8446-45b1-882c-880476ed11c0");

    // WHEN
    Response response =
        stockCheckSteps.postSimilarArticlesStockMovement(
            "RO",
            "MCC",
            "36",
            idamAuthorizationSteps.getToken().getAccess_token(),
            Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    String body =  response.jsonPath().get("debugMessage");
    assert body.contains("No Stock Correction type Found for CountryRO, Store number: 36");
  }

  @Test
  public void postSimilarArticlesStockMovementWrongCountryCode() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
            new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setArticleNumber(25054);
    stockMovement.setStockCheckId("6fbebd25-8446-45b1-882c-880476ed11c0");
    stockMovement.setStockCorrectionTypeCode("MAINTENANCE");

    // WHEN
    Response response =
            stockCheckSteps.postSimilarArticlesStockMovement(
                    "NL",
                    "MCC",
                    "36",
                    idamAuthorizationSteps.getToken().getAccess_token(),
                    Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
  }

  @Test
  public void postSimilarArticlesStockMovementBadArticleId() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
            new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setArticleNumber(90989);
    stockMovement.setStockCheckId("6fbebd25-8446-45b1-882c-880476ed11c0");
    stockMovement.setStockCorrectionTypeCode("MAINTENANCE");

    // WHEN
    Response response =
            stockCheckSteps.postSimilarArticlesStockMovement(
                    "RO",
                    "MCC",
                    "36",
                    idamAuthorizationSteps.getToken().getAccess_token(),
                    Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_OK);
    response.then().body("$", is(Collections.EMPTY_LIST));
  }

  @Test
  public void postSimilarArticlesStockMovementWrongStoreNumber() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
            new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setArticleNumber(25054);
    stockMovement.setStockCheckId("6fbebd25-8446-45b1-882c-880476ed11c0");
    stockMovement.setStockCorrectionTypeCode("MAINTENANCE");
    // WHEN
    Response response =
            stockCheckSteps.postSimilarArticlesStockMovement(
                    "RO",
                    "MCC",
                    "9999",
                    idamAuthorizationSteps.getToken().getAccess_token(),
                    Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
  }

  @Test
  public void postSimilarArticlesStockMovementNoArticleNumber() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
            new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setStockCheckId("6fbebd25-8446-45b1-882c-880476ed11c0");
    stockMovement.setStockCorrectionTypeCode("MAINTENANCE");

    // WHEN
    Response response =
            stockCheckSteps.postSimilarArticlesStockMovement(
                    "RO",
                    "MCC",
                    "36",
                    idamAuthorizationSteps.getToken().getAccess_token(),
                    Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_OK);
    response.then().body("$", is(Collections.EMPTY_LIST));
  }

  @Test
  public void postSimilarArticlesStockMovementNoStockCheckid() {

    // GIVEN
    SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest stockMovement =
            new SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest();
    stockMovement.setArticleNumber(25054);
    stockMovement.setStockCorrectionTypeCode("MAINTENANCE");

    // WHEN
    Response response =
            stockCheckSteps.postSimilarArticlesStockMovement(
                    "RO",
                    "MCC",
                    "36",
                    idamAuthorizationSteps.getToken().getAccess_token(),
                    Collections.singletonList(stockMovement));

    // THEN
    response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
  }
}
