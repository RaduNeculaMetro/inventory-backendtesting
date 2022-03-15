package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PutStockCheckReasonIdTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void putStockCheckReasonId() {

        Response response =
        stockCheckSteps.putStockCheckReasonId("RO","RO","7",
                idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("id", equalTo(7));
        response.then().body("changeUser", is("admin"));
        response.then().body("orderNo", is(1));
        response.then().body("defaults[0].stockCorrectionCode", equalTo("MAINTENANCE"));
        response.then().body("defaults[0].applyFor", equalTo("P"));
    }

    @Test
    public void putStockCheckReasonIdWrongCountryCode() {

        Response response =
                stockCheckSteps.putStockCheckReasonId("NL","RO","7",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void putStockCheckReasonIdWrongLanguageCode() {

        Response response =
                stockCheckSteps.putStockCheckReasonId("RO","test","7",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("id", equalTo(7));
        response.then().body("changeUser", is("admin"));
        response.then().body("orderNo", is(1));
        response.then().body("defaults[0].stockCorrectionCode", equalTo("MAINTENANCE"));
        response.then().body("defaults[0].applyFor", equalTo("P"));
    }

    @Test
    public void putStockCheckReasonIdWrongReasonId() {

        Response response =
                stockCheckSteps.putStockCheckReasonId("NL","RO","test",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }
}
