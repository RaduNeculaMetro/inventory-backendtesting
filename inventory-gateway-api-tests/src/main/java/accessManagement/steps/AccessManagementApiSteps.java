package accessManagement.steps;

import utils.EnvProperties;
import utils.api.BaseSteps;

public class AccessManagementApiSteps extends BaseSteps {

    protected AccessManagementApiSteps() {
        super(EnvProperties::getInventoryGatewayUri);
    }
}
