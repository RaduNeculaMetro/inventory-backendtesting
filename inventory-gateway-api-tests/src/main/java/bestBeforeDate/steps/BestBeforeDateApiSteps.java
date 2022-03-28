package bestBeforeDate.steps;

import utils.EnvProperties;
import utils.api.BaseSteps;

public class BestBeforeDateApiSteps extends BaseSteps {
    protected BestBeforeDateApiSteps() {
        super(EnvProperties::getInventoryGatewayUri);
    }

}
