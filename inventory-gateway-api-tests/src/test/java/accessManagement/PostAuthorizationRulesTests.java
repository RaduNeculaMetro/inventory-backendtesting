package accessManagement;

import accessManagement.steps.AccessManagementSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;


public class PostAuthorizationRulesTests extends BaseTestClass {

    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void postAuthorizationRules() {
        Response response =
        accessManagementSteps.postAuthorizationRules(
                "RO",
                "MCC",
                "36",
                idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postAuthorizationRulesWrongCountryCode() {
        Response response =
                accessManagementSteps.postAuthorizationRules(
                        "LOL",
                        "MCC",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void postAuthorizationRulesWrongSalesLine() {
        Response response =
                accessManagementSteps.postAuthorizationRules(
                        "RO",
                        "TEST",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postAuthorizationRulesWrongStoreNumber() {
        Response response =
                accessManagementSteps.postAuthorizationRules(
                        "RO",
                        "MCC",
                        "90",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void postAuthorizationRulesWrongAuthToken() {
        Response response =
                accessManagementSteps.postAuthorizationRules(
                        "RO",
                        "MCC",
                        "36",
                        "09348076-95498645-45326");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void postAuthorizationRulesNullAuthToken() {
        Response response =
                accessManagementSteps.postAuthorizationRules(
                        "RO",
                        "MCC",
                        "36",
                        null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
