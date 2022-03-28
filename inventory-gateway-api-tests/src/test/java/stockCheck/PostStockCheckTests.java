package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

import java.time.LocalDate;
import java.time.Month;

public class PostStockCheckTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    //TODO get more info why we receive bad request but no details
 //   @Ignore
    @Test
    public void postStockCheck() {
        Response response =
        stockCheckSteps.postStockCheck("RO",
                "MCC",
                "36",
                idamAuthorizationSteps.getToken().getAccess_token(),
                25054, 1,1,
                 "2022-03-15", 1,
                3, "Admin");

        response.then().log().all();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }
    //TODO finish Tests after gathering more info
}
