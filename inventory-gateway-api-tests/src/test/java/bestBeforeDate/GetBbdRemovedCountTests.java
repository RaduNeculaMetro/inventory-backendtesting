package bestBeforeDate;

import bestBeforeDate.steps.BestBeforeDateSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class GetBbdRemovedCountTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getBbdRemovedCount() {
        Map<String,String> query = new HashMap<>();
        query.put("department-number","1");

        Response response =
        bestBeforeDateSteps.getBbdRemovedCount("RO", "MCC",
                "36","RO", query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdRemovedCountWrongCountryCode() {
        Map<String,String> query = new HashMap<>();
        query.put("department-number","1");

        Response response =
                bestBeforeDateSteps.getBbdRemovedCount("NL", "MCC",
                        "36","RO", query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdRemovedCountWrongSalesLine() {
        Map<String,String> query = new HashMap<>();
        query.put("department-number","1");

        Response response =
                bestBeforeDateSteps.getBbdRemovedCount("RO", "MCC",
                        "36","RO", query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getBbdRemovedCountWrongStoreNumber() {
        Map<String,String> query = new HashMap<>();
        query.put("department-number","1");

        Response response =
                bestBeforeDateSteps.getBbdRemovedCount("RO", "MCC",
                        "1","RO", query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getBbdRemovedCountWrongToken() {
        Map<String,String> query = new HashMap<>();
        query.put("department-number","1");

        Response response =
                bestBeforeDateSteps.getBbdRemovedCount("RO", "MCC",
                        "36","RO", query, "test-token");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getBbdRemovedCountNullToken() {
        Map<String,String> query = new HashMap<>();
        query.put("department-number","1");

        Response response =
                bestBeforeDateSteps.getBbdRemovedCount("RO", "MCC",
                        "36","RO", query, null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
