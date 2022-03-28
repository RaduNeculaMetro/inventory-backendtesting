package accessManagement;

import accessManagement.steps.AccessManagementSteps;
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

public class GetAuthorizationRulesTests extends BaseTestClass {

    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getAuthorizationRules() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "mstore.inventory");
        query.put("area", "frontend");
        query.put("environment", "pp");
        query.put("userDate", "2022-01-01");
        query.put("context", "GapCheck");
        query.put("ruleForCountry", "RO");

        Response response =
        accessManagementSteps.getAuthorizationRules(idamAuthorizationSteps.getToken().getAccess_token(), query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAuthorizationRulesWrongVertical() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "TestWrongVertical");
        query.put("area", "frontend");
        query.put("environment", "pp");
        query.put("userDate", "2022-01-01");
        query.put("context", "GapCheck");
        query.put("ruleForCountry", "RO");

        Response response =
                accessManagementSteps.getAuthorizationRules(idamAuthorizationSteps.getToken().getAccess_token(), query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getAuthorizationRulesWrongAuthToken() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "TestWrongVertical");
        query.put("area", "frontend");
        query.put("environment", "pp");
        query.put("userDate", "2022-01-01");
        query.put("context", "GapCheck");
        query.put("ruleForCountry", "RO");

        Response response =
                accessManagementSteps.getAuthorizationRules("231-asdasd-2314-fdgdf-sdau8", query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getAuthorizationRulesNullAuthToken() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "TestWrongVertical");
        query.put("area", "frontend");
        query.put("environment", "pp");
        query.put("userDate", "2022-01-01");
        query.put("context", "GapCheck");
        query.put("ruleForCountry", "RO");

        Response response =
                accessManagementSteps.getAuthorizationRules(null, query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
