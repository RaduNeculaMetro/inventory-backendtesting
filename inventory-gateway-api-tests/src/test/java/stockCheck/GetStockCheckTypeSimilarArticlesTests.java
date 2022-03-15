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

public class GetStockCheckTypeSimilarArticlesTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void getStockCheckTypeSimilarArticles() {
        Response response =
        stockCheckSteps.getStockCheckTypeSimilarArticles("RO", "MCC", "36",
                26981,1, 1,"OneTwoStock",
                idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("storeId.storeNumber", hasItem(36));
        response.then().body("storeId.salesLine", hasItem("MCC"));
        response.then().body("storeId.countryCode", hasItem("RO"));
        response.then().body("articleId.articleNumber", hasItem(26981));
        response.then().body("articleId.variantNumber", hasItem(1));
        response.then().body("articleId.bundleNumber", hasItem(1));
    }
}
