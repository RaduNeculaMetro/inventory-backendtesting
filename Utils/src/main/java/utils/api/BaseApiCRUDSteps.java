package utils.api;

import io.restassured.response.Response;
import utils.EnvProperties;
import utils.LoggerUtils;

import java.util.function.Function;

public class BaseApiCRUDSteps extends BaseSteps{
    protected final String baseURL;
    protected final String baseURLwithId;

    protected BaseApiCRUDSteps (Function <EnvProperties, String> baseUriFunction, String baseURL) {
        super(baseUriFunction);
        this.baseURL=baseURL;
        this.baseURLwithId = baseURL + "{/id}";
    }

//    public Response post () {
//        return prepare()
//                .post(baseURL);
//        }

    public Response post () {
        return startRequest()
                .post(baseURL);
    }

    private Response logResponse (Response response) {
        LoggerUtils.doOnLogResponse( () -> response.then().log().all());
        return response;
    }
}
