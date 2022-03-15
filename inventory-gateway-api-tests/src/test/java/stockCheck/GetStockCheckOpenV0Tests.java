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

public class GetStockCheckOpenV0Tests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Ignore
    @Test
    public void getStockCheckOpenV0() {
        //WHEN
        Response response =
        stockCheckSteps.getStockCheckOpenV0("RO", "MCC", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().statusCode(HttpStatus.SC_OK);
    }
}
