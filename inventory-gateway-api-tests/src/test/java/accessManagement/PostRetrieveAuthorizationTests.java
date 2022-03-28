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
import java.util.HashMap;
import java.util.Map;

public class PostRetrieveAuthorizationTests extends BaseTestClass {

    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void postRetrieveAuthorization() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "mstore.inventory");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");

        Response response =
        accessManagementSteps.postRetrieveAuthorization(idamAuthorizationSteps.getToken().getAccess_token(), query,
                "MSTORE_EXTERNAL",
                "RO",
                "36",
                "TestAuto");

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("ArticleDetails", is(true));
    }

    @Test
    public void postRetrieveAuthorizationWrongVertical() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "testWrongVertical");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");

        Response response =
                accessManagementSteps.postRetrieveAuthorization(idamAuthorizationSteps.getToken().getAccess_token(), query,
                        "MSTORE_EXTERNAL",
                        "RO",
                        "36",
                        "TestAuto");

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postRetrieveAuthorizationNullAuthToken() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "testWrongVertical");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");

        Response response =
                accessManagementSteps.postRetrieveAuthorization(null, query,
                        "MSTORE_EXTERNAL",
                        "RO",
                        "36",
                        "TestAuto");

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void postRetrieveAuthorizationWrongAuthToken() {
        Map<String, String> query = new HashMap<>();
        query.put("vertical", "testWrongVertical");
        query.put("area", "backend");
        query.put("environment", "pp");
        query.put("userDate", "2022-02-14");
        query.put("context", "ArticleDetails");
        query.put("ruleForCountry", "RO");

        Response response =
                accessManagementSteps.postRetrieveAuthorization("asijdaoifhdssdfsdfs0fsdfsfdsfa", query,
                        "MSTORE_EXTERNAL",
                        "RO",
                        "36",
                        "TestAuto");

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
