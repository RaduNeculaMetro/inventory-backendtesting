package inventoryGateway;

import utils.EnvProperties;
import utils.api.BaseApiCRUDSteps;

public abstract class InventoryGatewayApiSteps extends BaseApiCRUDSteps {
  protected InventoryGatewayApiSteps(String baseUrl) {
    super(EnvProperties::getInventoryGatewayUri, baseUrl );
  }
}
