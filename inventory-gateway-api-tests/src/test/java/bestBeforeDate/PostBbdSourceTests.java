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

public class PostBbdSourceTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void postBbdSource(){

        Response response =
        bestBeforeDateSteps.postBbdSource("RO", "MCC",
                "36","MSTORE" ,idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postBbdSourceWrongCountryCode(){

        Response response =
                bestBeforeDateSteps.postBbdSource("NL", "MCC",
                        "36","MSTORE", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void postBbdSourceWrongSalesLine(){

        Response response =
                bestBeforeDateSteps.postBbdSource("RO", "TTT",
                        "36","MSTORE", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postBbdSourceWrongStoreNumber(){

        Response response =
                bestBeforeDateSteps.postBbdSource("RO", "MCC",
                        "1","MSTORE" , idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void postBbdSourceWrongToken(){

        Response response =
                bestBeforeDateSteps.postBbdSource("RO", "MCC",
                        "36","MSTORE", null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}
