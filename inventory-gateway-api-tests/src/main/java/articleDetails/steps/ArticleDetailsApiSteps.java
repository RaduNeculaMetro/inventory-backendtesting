package articleDetails.steps;

import utils.EnvProperties;
import utils.api.BaseSteps;


public class ArticleDetailsApiSteps extends BaseSteps {

    protected ArticleDetailsApiSteps() {
        super(EnvProperties::getInventoryGatewayUri);
    }

}
