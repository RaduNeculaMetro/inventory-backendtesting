package bestBeforeDate;

import bestBeforeDate.steps.BestBeforeDateSteps;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import utils.BaseTestClass;

public class GetBbdSearchCriteriaTests extends BaseTestClass {

    @Steps
    private BestBeforeDateSteps bestBeforeDateSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;

    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }


    @Test
    public void getBbdSearchCriteria() {

        Response response =
        bestBeforeDateSteps.getBbdArticleSearchCriteria("RO","MCC",
                "36","RO", "26981", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getBbdSearchCriteriaWrongCountryCode() {

        Response response =
                bestBeforeDateSteps.getBbdArticleSearchCriteria("NL","MCC",
                        "36","RO", "salam", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void getBbdSearchCriteriaWrongSalesLine() {

        Response response =
                bestBeforeDateSteps.getBbdArticleSearchCriteria("RO","TTT",
                        "36","RO", "26981", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getBbdSearchCriteriaWrongStoreNumber() {

        Response response =
                bestBeforeDateSteps.getBbdArticleSearchCriteria("RO","MCC",
                        "1","RO", "26981", idamAuthorizationSteps.getToken().getAccess_token());

        response.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getBbdSearchCriteriaWrongToken() {

        Response response =
                bestBeforeDateSteps.getBbdArticleSearchCriteria("RO","MCC",
                        "36","RO", "salam", "testToken");

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
    @Test
    public void getBbdSearchCriteriaNullToken() {

        Response response =
                bestBeforeDateSteps.getBbdArticleSearchCriteria("RO","MCC",
                        "36","RO", "salam", null);

        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
