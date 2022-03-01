package steps.authorization;

import io.restassured.response.Response;
import model.AccessTokenResponse;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import utils.EnvironmentProperties;



public class IdamAuthorizationSteps {

    private AccessTokenResponse token;

    @Steps
    private EnvironmentProperties environmentProperties;

    @Steps
    private IdamAuthorizationApiSteps idamAuthorizationApiSteps;

    public AccessTokenResponse getToken () {
        return token;
    }

    @Step
    public void getAccessToken () {
        Response response = idamAuthorizationApiSteps.post();
        response.then().assertThat().statusCode(HttpStatus.SC_OK);
        String access_token = response.jsonPath().get("access_token");
        this.token = new AccessTokenResponse().setAccess_token(access_token);
    }

}
