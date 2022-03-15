package steps.authorization;

import io.restassured.response.ValidatableResponse;
import model.AccessTokenResponse;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;
import utils.EnvironmentProperties;
import utils.api.WaitUtils;


public class IdamAuthorizationSteps {

    private AccessTokenResponse token;

    private ValidatableResponse response;

    @Steps
    private EnvironmentProperties environmentProperties;

    @Steps
    private IdamAuthorizationApiSteps idamAuthorizationApiSteps;

    public AccessTokenResponse getToken () {
        return token;
    }

    public void getAccessToken () {
        WaitUtils.verifyAndRetry(()->
       this.response =  idamAuthorizationApiSteps.tokenPost().then().statusCode(HttpStatus.SC_OK)
        ,5,1000 );
        saveAccessToken(response);
    }

    public void saveAccessToken (ValidatableResponse response) {
         String access_token = response.extract().jsonPath().get("access_token");
         this.token = new AccessTokenResponse().setAccess_token(access_token);
    }

}
