package articleDetails.steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;


public class ArticleDetailsSteps {

    private static final String SEARCH_CRITERIA_ENDPOINT
            = "/v0/countryCode/{countryCode}/storeNumber/{storeNumber}/article-details/searchCriteria/{searchCriteria}";
    private static final String BUNDLE_SEARCH_ENDPOINT =
            "/v0/countryCode/{countryCode}/salesLine/{salesLine}/storeNumber/{storeNumber}" +
                    "/article-details/articleNumber/{articleNumber}/variantNumber/{variantNumber}" +
                    "/bundleNumber/{bundleNumber}/languageCode/{languageCode}";

    @Steps
    private ArticleDetailsApiSteps detailsByCriteriaApiSteps;

    @Step
    public Response getArticleDetailsByBundle (String countryCode, String salesLine, String storeNumber,
                                               String articleNumber, String variantNumber,
                                               String bundleNumber, String languageCode, String token) {
        return detailsByCriteriaApiSteps.authorizedGiven(token)
                .when()
                .get(BUNDLE_SEARCH_ENDPOINT, countryCode,
                        salesLine, storeNumber,
                        articleNumber, variantNumber,
                        bundleNumber,languageCode);
    }

    @Step
    public Response getArticleDetailsByCriteria(String countryCode, String storeNumber,
                                                String searchCriteria, String token) {
       return detailsByCriteriaApiSteps.authorizedGiven(token)
                .when()
                .get(SEARCH_CRITERIA_ENDPOINT, countryCode, storeNumber,searchCriteria);
    }

}
