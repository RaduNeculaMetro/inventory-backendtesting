package adminControlller;

import adminResetCache.AdminResetCacheSteps;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import utils.BaseTestClass;

public class AdminResetCacheTests extends BaseTestClass {


    @Steps
    private AdminResetCacheSteps adminResetCacheSteps;

    @Test
    public void resetAdminCache () {
        adminResetCacheSteps.postAdminResetCache();
    }
}
