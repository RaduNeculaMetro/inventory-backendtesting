package adminResetCache;

import inventoryGateway.InventoryGatewayApiSteps;

public class AdminResetCacheApiSteps extends InventoryGatewayApiSteps {
  protected AdminResetCacheApiSteps() {
    super("/admin/v0/caches/reset");
  }
}
