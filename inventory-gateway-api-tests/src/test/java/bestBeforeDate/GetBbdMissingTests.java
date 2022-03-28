package bestBeforeDate;

import bestBeforeDate.steps.BestBeforeDateSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;
import static org.hamcrest.Matchers.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GetBbdMissingTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void getBbdMissing() {
        Map<String,String> query = new HashMap<>();
        query.put("has-bbd","NO");

        Response response =
        bestBeforeDateSteps.getBddMissing("RO","MCC",
                "36","RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdMissingWongCountryCode() {
        Map<String,String> query = new HashMap<>();
        query.put("has-bbd","NO");

        Response response =
                bestBeforeDateSteps.getBddMissing("NL","MCC",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdMissingWrongSalesLine() {
        Map<String,String> query = new HashMap<>();
        query.put("has-bbd","NO");

        Response response =
                bestBeforeDateSteps.getBddMissing("RO","TTT",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));

    }

    @Test
    public void getBbdMissingWrongStoreNumber() {
        Map<String,String> query = new HashMap<>();
        query.put("has-bbd","NO");

        Response response =
                bestBeforeDateSteps.getBddMissing("RO","MCC",
                        "1","RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdMissingWrongToken() {
        Map<String,String> query = new HashMap<>();
        query.put("has-bbd","NO");

        Response response =
                bestBeforeDateSteps.getBddMissing("RO","MCC",
                        "36","RO", "test-token", query);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getBbdMissingNullToken() {
        Map<String,String> query = new HashMap<>();
        query.put("has-bbd","NO");

        Response response =
                bestBeforeDateSteps.getBddMissing("RO","MCC",
                        "36","RO", null, query);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
