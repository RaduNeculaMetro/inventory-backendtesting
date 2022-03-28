package accessManagement.steps;

import accessManagement.model.PatchAuthorizationRulesModel;
import accessManagement.model.PostAuthorizationRulesModel;
import accessManagement.model.PostRetrieveAuthorizationModel;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AccessManagementSteps {

    private static final String VERTICAL_ENDPOINT = "/v0/context-mapping/vertical/{vertical}";
    private static final String COUNTRY_RULES_ENDPOINT = "/v0/country-roles";
    private static final String RETRIEVE_AUTHORIZATION_RULES_ENDPOINT = "/v0/retrieve-authorization-rules";
    private static final String AUTHORIZATION_RULES_AUDIT_ENDPOINT = "/v0/retrieve-authorization-rules-audit";
    private static final String AUTHORIZATION_RULES_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/authorization-rules";
    private static final String RETRIEVE_AUTHORIZATION = "/v0/retrieve-authorization";


    @Steps
    private AccessManagementApiSteps accessManagementApiSteps;


    @Step
    public Response getContextMappingVertical(String vertical, String token) {

       return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .get(VERTICAL_ENDPOINT, vertical);
    }

    @Step
    public Response getCountryRoles(String token, String countryCode) {
        return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .formParam("countryCode",countryCode)
                .get(COUNTRY_RULES_ENDPOINT);
    }

    @Step
    public Response getAuthorizationRules(String token, Map<String,String> query) {
        return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .formParams(query)
                .get(RETRIEVE_AUTHORIZATION_RULES_ENDPOINT);
    }

    @Step
    public Response getAuthorizationRulesAudit(String token, Map<String,String> query) {
        return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .formParams(query)
                .get(AUTHORIZATION_RULES_AUDIT_ENDPOINT);
    }

    @Step
    public Response postAuthorizationRules(String countryCode, String salesLine, String storeNumber, String token) {
        return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .body(Collections.singletonList(preparePostAuthorizationRuleModel()))
                .post(AUTHORIZATION_RULES_ENDPOINT, countryCode, salesLine, storeNumber);
    }

    @Step
    public Response patchAuthorizationRules(String countryCode, String salesLine, String storeNumber, String token) {
        return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .body(Collections.singletonList(preparePatchAuthorizationRuleModel()))
                .patch(AUTHORIZATION_RULES_ENDPOINT, countryCode, salesLine, storeNumber);
    }

    @Step
    public Response postRetrieveAuthorization(String token, Map<String, String> query,
                                              String roles, String countries, String stores, String userType) {
        return accessManagementApiSteps.authorizedGiven(token)
                .when()
                .body(preparePostRetrieveAuthorizationModel(roles, countries, stores, userType))
                .queryParams(query)
                .post(RETRIEVE_AUTHORIZATION);
    }


    private PostAuthorizationRulesModel preparePostAuthorizationRuleModel () {
        PostAuthorizationRulesModel model = new PostAuthorizationRulesModel();
        return model;
    }

    private PatchAuthorizationRulesModel preparePatchAuthorizationRuleModel () {
        PatchAuthorizationRulesModel model = new PatchAuthorizationRulesModel();
        return model;
    }

    private PostRetrieveAuthorizationModel preparePostRetrieveAuthorizationModel (String roles, String countries, String stores,String userType  ) {
        PostRetrieveAuthorizationModel model = new PostRetrieveAuthorizationModel();
        model.setCountries(Collections.singletonList(countries));
        model.setRoles(Collections.singletonList(roles));
        model.setStores(Collections.singletonList(stores));
        model.setUserType(Collections.singletonList(userType));
        return model;
    }
}
