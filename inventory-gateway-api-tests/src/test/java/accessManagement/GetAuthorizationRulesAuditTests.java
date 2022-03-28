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

public class GetAuthorizationRulesAuditTests extends BaseTestClass {

    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getAuthorizationRulesAudit() {

        Map<String, String> query = new HashMap<>();
        query.put("vertical", "mstore.inventory");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");
        query.put("accessManagementId", "1220");

        Response response =
                accessManagementSteps.getAuthorizationRulesAudit(idamAuthorizationSteps.getToken().getAccess_token(), query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAuthorizationRulesAuditWrongVertical() {

        Map<String, String> query = new HashMap<>();
        query.put("vertical", "TestWrongVertical");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");
        query.put("accessManagementId", "1220");

        Response response =
                accessManagementSteps.getAuthorizationRulesAudit(idamAuthorizationSteps.getToken().getAccess_token(), query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getAuthorizationRulesAuditNullAuthToken() {

        Map<String, String> query = new HashMap<>();
        query.put("vertical", "TestWrongVertical");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");
        query.put("accessManagementId", "1220");

        Response response =
                accessManagementSteps.getAuthorizationRulesAudit(null, query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getAuthorizationRulesAuditWrongAuthToken() {

        Map<String, String> query = new HashMap<>();
        query.put("vertical", "TestWrongVertical");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");
        query.put("accessManagementId", "1220");

        Response response =
                accessManagementSteps.getAuthorizationRulesAudit("asudhq3rt-fsdfsdf-sfdsf4325q35eg-sdfdsy", query);
        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);

    }
}
