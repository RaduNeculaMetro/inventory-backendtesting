package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;


public class PostStockCheckSimilarArticlesTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void postStockCheckSimilarArticles() {
        Response response =
    stockCheckSteps.postStockCheckSimilarArticles("RO","MCC","36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postStockCheckSimilarArticlesWrongCountryCode() {
        Response response =
                stockCheckSteps.postStockCheckSimilarArticles("NL","MCC","36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void postStockCheckSimilarArticlesWrongSalesLine() {
        Response response =
                stockCheckSteps.postStockCheckSimilarArticles("RO","KUKU","36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postStockCheckSimilarArticlesWrongStoreNumber() {
        Response response =
                stockCheckSteps.postStockCheckSimilarArticles("RO","MCC","90", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

}
