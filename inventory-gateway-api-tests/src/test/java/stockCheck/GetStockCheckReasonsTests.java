package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

import java.util.Collections;

import static org.hamcrest.Matchers.is;

public class GetStockCheckReasonsTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getStockCheckReasons() {

        //WHEN
        Response response =
        stockCheckSteps.getStockCheckReasons("RO", "MCC", "RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getStockCheckReasonsNullValueCountryCode() {

        //WHEN
        Response response =
                stockCheckSteps.getStockCheckReasons("null", "MCC","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getStockCheckReasonsWrongLanguage() {

        //WHEN
        Response response =
                stockCheckSteps.getStockCheckReasons("RO", "MCC", "POV", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }
}
