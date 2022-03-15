package stockCheck;

import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONCompareMode;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;
import org.skyscreamer.jsonassert.JSONAssert;
import utils.FileReaderUtils;

import java.util.Collections;

import static org.hamcrest.Matchers.is;

public class GetStockCheckDepartmentsTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void getStockCheckDepartments() throws JSONException {
    System.out.println(Serenity.sessionVariableCalled("idamToken") + " !!!!!!!!!!!!!!!!!1" );
        //WHEN
        Response response = stockCheckSteps.getStockChecksByDepartments("RO", "MCC", "36", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        JSONAssert.assertEquals(FileReaderUtils.read("stockDepartments.json"), response.getBody().asString(), JSONCompareMode.STRICT);
    }

    @Test
    public void getStockCheckDepartmentsWrongCountryCode() {

        //WHEN
        Response response = stockCheckSteps.getStockChecksByDepartments("NL", "MCC", "36", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getStockCheckDepartmentsWrongSalesLine() {

        //WHEN
        Response response = stockCheckSteps.getStockChecksByDepartments("RO", "KOL", "36", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.emptyList()));
    }

    @Test
    public void getStockCheckDepartmentsWrongStoreNumber() {

        //WHEN
        Response response = stockCheckSteps.getStockChecksByDepartments("RO", "MCC", "10", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
