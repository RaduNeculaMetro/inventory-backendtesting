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

import static org.hamcrest.Matchers.*;

public class GetStockCheckTypeTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getStockCheckType() {
        //WHEN
       Response response =  stockCheckSteps.getStockCheckType("RO", "MCC", "36", idamAuthorizationSteps.getToken().getAccess_token());

       //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", containsInAnyOrder("MinusStock", "OneTwoStock", "ManualStockCheck"));
    }

    @Test
    public void getStockCheckTypeWrongCountryCode() {

        //WHEN
        Response response = stockCheckSteps.getStockCheckType("NL", "MCC", "36", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getStockCheckTypeWrongSalesLine() {

        //WHEN
        Response response = stockCheckSteps.getStockCheckType("RO", "KOL", "36", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.emptyList()));
    }

    @Test
    public void getStockCheckTypeWrongStoreNumber() {

        //WHEN
        Response response = stockCheckSteps.getStockCheckType("RO", "MCC", "999", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", containsInAnyOrder("MinusStock", "OneTwoStock", "ManualStockCheck"));
    }
}
