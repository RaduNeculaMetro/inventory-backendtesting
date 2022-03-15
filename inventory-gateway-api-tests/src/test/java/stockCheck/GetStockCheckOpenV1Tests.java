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

import static org.hamcrest.Matchers.*;

public class GetStockCheckOpenV1Tests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Steps
    private StockCheckAsserter stockCheckOpenAsserter;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getStockCheckOpenV1() {
        //WHEN
        Response response =
        stockCheckSteps.getStockCheckOpenV1("RO", "MCC", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
         stockCheckOpenAsserter.assertStockCheckOpen(response);
    }

    @Test
    public void getStockCheckOpenV1WrongCountryCode() {
        //WHEN
        Response response =
                stockCheckSteps.getStockCheckOpenV1("NL", "MCC", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getStockCheckOpenV1WrongSalesLine() {
        //WHEN
        Response response =
                stockCheckSteps.getStockCheckOpenV1("RO", "PPP", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("numberOfArticles", is(0));
        response.then().body("stockCheckListViews", is(Collections.EMPTY_LIST));
    }
    @Test
    public void getStockCheckOpenV1WrongStoreNumber() {
        //WHEN
        Response response =
                stockCheckSteps.getStockCheckOpenV1("RO", "MCC", "99","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }
    @Test
    public void getStockCheckOpenV1WrongLanguageCode() {
        //WHEN
        Response response =
                stockCheckSteps.getStockCheckOpenV1("RO", "MCC", "36","RO", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        stockCheckOpenAsserter.assertStockCheckOpen(response);
    }
}
