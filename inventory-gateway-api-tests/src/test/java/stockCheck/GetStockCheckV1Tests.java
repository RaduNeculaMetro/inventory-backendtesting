package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckAsserter;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

import java.util.Collections;

import static org.hamcrest.Matchers.is;

public class GetStockCheckV1Tests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Steps
    private StockCheckAsserter stockCheckAsserter;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getStockCheckV1Checked() {

        //WHEN
        Response response =
        stockCheckSteps.getStockCheckV1Checked("RO", "MCC", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        stockCheckAsserter.assertStockCheckChecked(response);
    }

    @Test
    public void getStockCheckV1CheckedNullValueCountryCode() {

        //WHEN
        Response response =
                stockCheckSteps.getStockCheckV1Checked("null", "MCC", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getStockCheckV1CheckedWrongLanguage() {

        //WHEN
        Response response =
                stockCheckSteps.getStockCheckV1Checked("RO", "MCC", "36","POV", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        stockCheckAsserter.assertStockCheckChecked(response);
    }
}
