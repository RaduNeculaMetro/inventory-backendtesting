package bestBeforeDate;

import bestBeforeDate.steps.BestBeforeDateSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

public class PostCheckResultsTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void postCheckResults() {

        Response response =
                bestBeforeDateSteps.postBbdCheckResults("RO", "MCC",
                        "36", "RO", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

    }

    @Test
    public void postCheckResultsWrongCountryCode() {

        Response response =
                bestBeforeDateSteps.postBbdCheckResults("NL", "MCC",
                        "36", "RO", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);

    }

    @Test
    public void postCheckResultsWrongSalesLine() {

        Response response =
                bestBeforeDateSteps.postBbdCheckResults("RO", "TTT",
                        "36", "RO", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        String body = response.asString();
        assert body.equals("{\"status\":\"Created!\"}");

    }

    @Test
    public void postCheckResultsWrongStoreNumber() {

        Response response =
                bestBeforeDateSteps.postBbdCheckResults("RO", "MCC",
                        "1", "RO", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        String body = response.asString();
        assert body.equals("{\"status\":\"Created!\"}");
    }

    @Test
    public void postCheckResultsWrongToken() {

        Response response =
                bestBeforeDateSteps.postBbdCheckResults("RO", "MCC",
                        "36", "RO", "test-token");

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);

    }

    @Test
    public void postCheckResultsNullToken() {

        Response response =
                bestBeforeDateSteps.postBbdCheckResults("RO", "MCC",
                        "36", "RO", null);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);

    }


}
