package accessManagement;

import accessManagement.steps.AccessManagementSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

public class GetContextMappingTests extends BaseTestClass {

    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getContextMapping() {
        Response response =
        accessManagementSteps.getContextMappingVertical("mstore.inventory", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getContextMappingWrongVertical() {
        Response response =
                accessManagementSteps.getContextMappingVertical("TestWrongVertical", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getContextMappingWrongAuthToken() {
        Response response =
                accessManagementSteps.getContextMappingVertical("mstore.inventory", "oejra89y-erwafiuslkgr-aeihieougre");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getContextMappingNullAuthToken() {
        Response response =
                accessManagementSteps.getContextMappingVertical("mstore.inventory", null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
