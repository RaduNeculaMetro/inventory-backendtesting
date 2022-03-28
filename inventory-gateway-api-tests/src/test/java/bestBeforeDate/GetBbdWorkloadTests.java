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

public class GetBbdWorkloadTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void getBbdWorkload() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
        bestBeforeDateSteps.getWorkload("RO","MCC","36",
                "RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdWorkloadWrongCountryCode() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getWorkload("NL","MCC","36",
                        "RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdWorkloadWrongSalesLine() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getWorkload("RO","TTT","36",
                        "RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getBbdWorkloadWrongStoreNumber() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getWorkload("RO","MCC","36",
                        "RO", idamAuthorizationSteps.getToken().getAccess_token(), query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getBbdWorkloadWrongToken() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getWorkload("RO","MCC","36",
                        "RO", "test", query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getBbdWorkloadNullToken() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getWorkload("RO","MCC","36",
                        "RO", null, query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
