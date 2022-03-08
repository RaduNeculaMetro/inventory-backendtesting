package articleDetails.steps;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import utils.EnvProperties;
import utils.api.BaseSteps;


public class ArticleDetailsApiSteps extends BaseSteps {

    protected ArticleDetailsApiSteps() {
        super(EnvProperties::getInventoryGatewayUri);
    }

    @Step
    public RequestSpecification authorizedGiven (String accessToken) {
        return startRequest()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .header("Authorization", "Bearer " + accessToken );
    }
}
