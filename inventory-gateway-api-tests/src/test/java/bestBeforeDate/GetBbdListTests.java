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

public class GetBbdListTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void getBbdList() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
        bestBeforeDateSteps.getBddList("RO","MCC",
                "36","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdListWrongCountryCode() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddList("NL","MCC",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdListWrongSalesLine() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddList("RO","TTT",
                        "36","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

    @Test
    public void getBbdListWrongStoreNumber() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBddList("RO","MCC",
                        "1","RO", idamAuthorizationSteps.getToken().getAccess_token(),query);

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("$", is(Collections.EMPTY_LIST));
    }

}
