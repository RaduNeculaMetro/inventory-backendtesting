package utils.api;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
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

    protected RequestSpecification startRequest () {
        RestAssured.config = config()
                .encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RequestSpecification given = SerenityRest.given();
        LoggerUtils.doOnLogRequest(() -> given.log().all());
        String baseUri = baseUriFunction.apply(envProperties);
        given.baseUri(baseUri);
        return given;
    }

    @Step
    protected  RequestSpecification prepare () {
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
                .when();
    }
}
