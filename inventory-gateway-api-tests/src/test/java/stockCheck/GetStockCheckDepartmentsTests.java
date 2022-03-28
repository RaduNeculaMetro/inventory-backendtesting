package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

import java.util.Collections;

import static org.hamcrest.Matchers.*;

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
        //WHEN
        Response response = stockCheckSteps.getStockChecksByDepartments("RO", "MCC", "36", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("[0]", allOf(hasKey("departmentNumber")));
        response.then().body("[0]", allOf(hasKey("departmentDescription")));
        response.then().body("[0]", allOf(hasKey("departmentNumberDepartmentDescription")));
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
        Response response = stockCheckSteps.getStockChecksByDepartments("RO", "MCC", "99999", idamAuthorizationSteps.getToken().getAccess_token());

        //THEN
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("[0]", allOf(hasKey("departmentNumber")));
        response.then().body("[0]", allOf(hasKey("departmentDescription")));
        response.then().body("[0]", allOf(hasKey("departmentNumberDepartmentDescription")));
    }
}
