package stockCheck;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import steps.authorization.IdamAuthorizationSteps;
import stockCheck.steps.StockCheckSteps;
import utils.BaseTestClass;

public class PatchStockCheckTests extends BaseTestClass {

    @Steps
    private StockCheckSteps stockCheckSteps;

    @Steps
    private IdamAuthorizationSteps idamAuthorizationSteps;


    @Before
    public void getToken() {
        idamAuthorizationSteps.getAccessToken();
    }

    @Test
    public void patchStockCheck() {
        Response response =
        stockCheckSteps.patchStockCheck("RO",
                "MCC",
                "36",
                idamAuthorizationSteps.getToken().getAccess_token(),
                25054, 1,1,
                "MAINTENANCE", "6fbebd25-8446-45b1-882c-880476ed11c0",
                3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void patchStockCheckWrongCountryCode() {
        Response response =
                stockCheckSteps.patchStockCheck("NL",
                        "MCC",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token(),
                        25054, 1,1,
                        "MAINTENANCE", "6fbebd25-8446-45b1-882c-880476ed11c0",
                        3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void patchStockCheckWrongSalesLine() {
        Response response =
                stockCheckSteps.patchStockCheck("RO",
                        "KUKU",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token(),
                        25054, 1,1,
                        "MAINTENANCE", "6fbebd25-8446-45b1-882c-880476ed11c0",
                        3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void patchStockCheckWrongStoreNumber() {
        Response response =
                stockCheckSteps.patchStockCheck("RO",
                        "MCC",
                        "98",
                        idamAuthorizationSteps.getToken().getAccess_token(),
                        25054, 1,1,
                        "MAINTENANCE", "6fbebd25-8446-45b1-882c-880476ed11c0",
                        3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void patchStockCheckWrongAuthorizationToken() {
        Response response =
                stockCheckSteps.patchStockCheck("RO",
                        "MCC",
                        "36",
                        "null",
                        25054, 1,1,
                        "MAINTENANCE", "6fbebd25-8446-45b1-882c-880476ed11c0",
                        3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test
    public void patchStockCheckWrongStockCheckId() {
        Response response =
                stockCheckSteps.patchStockCheck("RO",
                        "MCC",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token(),
                        25054, 1,1,
                        "MAINTENANCE", "1234-12314-2131-12310",
                        3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void patchStockCheckWrongArticleNumber() {
        Response response =
                stockCheckSteps.patchStockCheck("RO",
                        "MCC",
                        "36",
                        idamAuthorizationSteps.getToken().getAccess_token(),
                        90909090, 1,1,
                        "MAINTENANCE", "6fbebd25-8446-45b1-882c-880476ed11c0",
                        3, "StockCheck");
        response.then().assertThat().statusCode(HttpStatus.SC_BAD_REQUEST);
    }

}
