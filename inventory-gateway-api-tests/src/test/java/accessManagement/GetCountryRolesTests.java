package accessManagement;

import accessManagement.steps.AccessManagementSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

public class GetCountryRolesTests extends BaseTestClass {


    @Steps
    private AccessManagementSteps accessManagementSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getCountryRoles() {

        Response response =
        accessManagementSteps.getCountryRoles(idamAuthorizationSteps.getToken().getAccess_token(),"RO");

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getCountryRolesWrongAuthToken() {

        Response response =
                accessManagementSteps.getCountryRoles("12314325-343596-45646","RO");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getCountryRolesNullAuthToken() {

        Response response =
                accessManagementSteps.getCountryRoles(null,"RO");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
