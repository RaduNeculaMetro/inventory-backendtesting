package articleDetails.steps;

import utils.EnvProperties;
import utils.api.BaseSteps;


public class InventoryGatewayApiSteps extends BaseSteps {
    protected  InventoryGatewayApiSteps () {
        super(EnvProperties::getInventoryGatewayUri);
    }

}
