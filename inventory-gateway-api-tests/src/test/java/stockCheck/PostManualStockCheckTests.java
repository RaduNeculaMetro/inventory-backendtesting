package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

public class PostManualStockCheckTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    //TODO enable manual stock check in test environment to properly create automated tests
    //create
    @Test
    public void postStockCheck() {
        Response response =
        stockCheckSteps.postManualStockCheck("RO",
                "MCC",
                "36",
                idamAuthorizationSteps.getToken().getAccess_token(),
                25054, 1,1,
                "2022-03-11", "test");
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
        String body = response.jsonPath().getString("debugMessage");
        assert body.contains("Manual stock check is disabled for requested country");
    }
}
