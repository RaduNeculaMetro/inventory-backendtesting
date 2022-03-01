package articleDetails.steps;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import utils.EnvProperties;
import utils.api.BaseSteps;


public class ArticleDetailsByCriteriaApiSteps extends BaseSteps {

    protected ArticleDetailsByCriteriaApiSteps() {
        super(EnvProperties::getInventoryGatewayUri);
    }

    @Step
    public RequestSpecification authorizedGiven (String accessToken) {
        return startRequest()
                .header("Authorization", "Bearer " + accessToken );
    }
}
