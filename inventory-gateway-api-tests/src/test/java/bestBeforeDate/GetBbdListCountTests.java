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
import java.util.HashMap;
import java.util.Map;

public class GetBbdListCountTests extends BaseTestClass {

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
                bestBeforeDateSteps.getBbdListCount("RO","MCC",
                        "36",query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("recordsCount", is(0));
    }

    @Test
    public void getBbdListWrongCountryCode() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBbdListCount("NL","MCC",
                        "36",query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdListWrongSalesLine() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBbdListCount("RO","TTT",
                        "36",query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("recordsCount", is(0));
    }

    @Test
    public void getBbdListWrongStoreNumber() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBbdListCount("RO","MCC",
                        "1",query, idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        response.then().body("recordsCount", is(0));
    }

    @Test
    public void getBbdListWrongToken() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBbdListCount("RO","MCC",
                        "36",query, "test");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void getBbdListNullToken() {
        Map<String,String> query = new HashMap<>();
        query.put("article-number","673319");

        Response response =
                bestBeforeDateSteps.getBbdListCount("RO","MCC",
                        "36",query, null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
