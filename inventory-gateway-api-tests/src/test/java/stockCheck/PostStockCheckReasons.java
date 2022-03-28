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

    @Test
    public void postStockCheckReasons() {
        Response respone =
        stockCheckSteps.postStockCheckReasons
                ("RO", "RO",idamAuthorizationSteps.getToken().getAccess_token());

        respone.then().log().all();
        respone.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postStockCheckReasonsWrongCountryCode() {
        Response respone =
                stockCheckSteps.postStockCheckReasons
                        ("NL", "RO",idamAuthorizationSteps.getToken().getAccess_token());

        respone.then().log().all();
        respone.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void postStockCheckReasonsWrongToken() {
        Response respone =
                stockCheckSteps.postStockCheckReasons
                        ("RO", "RO","test-bad-token");

        respone.then().log().all();
        respone.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void postStockCheckReasonsNullToken() {
        Response respone =
                stockCheckSteps.postStockCheckReasons
                        ("RO", "RO",null);

        respone.then().log().all();
        respone.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
    @Test
    public void postStockCheckReasonsWrongLanguage() {
        Response respone =
                stockCheckSteps.postStockCheckReasons
                        ("RO", "test",idamAuthorizationSteps.getToken().getAccess_token());

        respone.then().log().all();
        respone.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
