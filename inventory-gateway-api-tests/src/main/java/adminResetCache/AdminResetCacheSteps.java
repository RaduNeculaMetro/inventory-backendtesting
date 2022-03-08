package adminResetCache;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class AdminResetCacheSteps {

    @Steps
    private AdminResetCacheApiSteps adminResetCacheApiSteps;


    @Step
    public Response postAdminResetCache() {
        return adminResetCacheApiSteps
                .post();
    }
}
