package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;
import static org.hamcrest.Matchers.*;

public class PatchStockCheckSimilarArticlesTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void patchStockCheckSimilarArticles() {
        Response response =
        stockCheckSteps.patchStockCheckSimilarArticles("RO","MCC","36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        String body = response.then().extract().asString();
        assert body.equals("0 record(s) updated");
    }

    @Test
    public void patchStockCheckSimilarArticlesBadCountryCode() {
        Response response =
                stockCheckSteps.patchStockCheckSimilarArticles("JO","MCC","36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    //TODO salesLine is not a checked value
    @Test
    public void patchStockCheckSimilarArticlesBadSalesLine() {
        Response response =
                stockCheckSteps.patchStockCheckSimilarArticles("RO","123456","36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void patchStockCheckSimilarArticlesBadStoreNumber() {
        Response response =
                stockCheckSteps.patchStockCheckSimilarArticles("RO","MCC","999", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        String body = response.asString();
        assert body.equals("0 record(s) updated");
    }

    @Test
    public void patchStockCheckSimilarArticlesNullToken() {
        Response response =
                stockCheckSteps.patchStockCheckSimilarArticles("RO","MCC","36", null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
