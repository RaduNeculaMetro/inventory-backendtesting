package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

public class PostStockCheckReasons extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    //TODO get more info why we receive bad request but no details
    @Ignore
    @Test
    public void postStockCheckReasons() {
        Response respone =
        stockCheckSteps.postStockCheckReasons
                ("RO", "RO",idamAuthorizationSteps.getToken().getAccess_token());

        respone.then().log().all();
        respone.then().assertThat().statusCode(HttpStatus.SC_OK);
    }
}
