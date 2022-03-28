package accessManagement;

import accessManagement.steps.AccessManagementSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

public class PatchAuthorizationRulesTests extends BaseTestClass {

    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void patchAuthorizationRules() {
        Response response =
                accessManagementSteps.patchAuthorizationRules(
                        "RO",
                        "MCC",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void patchAuthorizationRulesWrongCountryCode() {
        Response response =
                accessManagementSteps.patchAuthorizationRules(
                        "LOL",
                        "MCC",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void patchAuthorizationRulesWrongSalesLine() {
        Response response =
                accessManagementSteps.patchAuthorizationRules(
                        "RO",
                        "TEST",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void patchAuthorizationRulesWrongStoreNumber() {
        Response response =
                accessManagementSteps.patchAuthorizationRules(
                        "RO",
                        "MCC",
                        "90",
                        idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
