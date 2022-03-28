package utils.api;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import utils.EnvProperties;
import utils.LoggerUtils;

import java.util.function.Function;

import static io.restassured.RestAssured.config;

public abstract class BaseSteps {

    @Steps
    private EnvProperties envProperties;

    private final Function <EnvProperties, String> baseUriFunction ;

    protected BaseSteps (Function<EnvProperties, String> baseUriFunction) {
        this.baseUriFunction = baseUriFunction;
    }
    public EnvProperties getEnvProperties () {
        return envProperties;
    }

    @Step
    protected RequestSpecification startRequest () {
        RestAssured.config = config()
                .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RequestSpecification given = RestAssured.given();
        LoggerUtils.doOnLogRequest(() -> given.log().all());
        String baseUri = baseUriFunction.apply(envProperties);
        given.baseUri(baseUri);
        return given;
    }

    @Step
    public RequestSpecification authorizedGiven (String accessToken) {
        return startRequest()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + accessToken );
    }
}
