package stockCheck.steps;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import utils.EnvProperties;
import utils.api.BaseSteps;

public class StockCheckApiSteps extends BaseSteps {
    protected StockCheckApiSteps() {
        super(EnvProperties::getInventoryGatewayUri);
    }

    @Step
    public RequestSpecification authorizedGiven (String accessToken) {
        return startRequest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken );
    }
}
