package bestBeforeDate;

import bestBeforeDate.steps.BestBeforeDateSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

public class PostProcessBbdChangesTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void postProcessBbdChanges() {

        Response response =
        bestBeforeDateSteps.postProcessBddChanges("RO","MCC",
                "36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postProcessBbdChangesWrongCountryCode() {

        Response response =
                bestBeforeDateSteps.postProcessBddChanges("NL","MCC",
                        "36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void postProcessBbdChangesWrongSalesLine() {

        Response response =
                bestBeforeDateSteps.postProcessBddChanges("RO","TTT",
                        "36", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postProcessBbdChangesWrongStoreNumber() {

        Response response =
                bestBeforeDateSteps.postProcessBddChanges("NL","MCC",
                        "1", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }
}
