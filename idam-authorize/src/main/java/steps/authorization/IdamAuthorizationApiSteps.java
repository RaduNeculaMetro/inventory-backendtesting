package steps.authorization;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import steps.IdamApiCRUDSteps;
import utils.EnvProperties;

public class IdamAuthorizationApiSteps extends IdamApiCRUDSteps {
    private static final String ACCESS_TOKEN = "/access_token";

    public IdamAuthorizationApiSteps() {
    super (ACCESS_TOKEN);
}

    @Steps
    private EnvProperties envProperties;

    @Step
    protected Response tokenPost () {
        return startRequest()
                .with()
                .auth()
                .preemptive()
                .basic(envProperties.getbasicUsername(),envProperties.getbasicPassword())
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("grant_type", "password")
                .formParam("username", envProperties.getPPUsername())
                .formParam("password", envProperties.getPPUserPassword())
                .formParam("client_id", "MSTORE")
                .formParam("realm_id", "MSTORE_RL")
                .formParam("user_type", "EMP")
                .when()
                .post(ACCESS_TOKEN);
    }
}
