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
import java.util.HashMap;
import java.util.Map;

public class GetBbdRemovedTests extends BaseTestClass {
    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void getBbdRemoved() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddRemoved("RO","MCC",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdRemovedWrongCountryCode() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddRemoved("NL","MCC",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdRemovedWrongSalesLine() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddRemoved("RO","TTT",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getBbdRemovedWrongStoreNumber() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddRemoved("RO","MCC",
                        "1","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

}
