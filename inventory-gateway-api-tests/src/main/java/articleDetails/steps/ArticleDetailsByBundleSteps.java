package articleDetails.steps;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class ArticleDetailsByBundleSteps {
  private static final String search_details =
      "/v0/countryCode/{countryCode}/salesLine/{salesLine}/storeNumber/{storeNumber}" +
              "/article-details/articleNumber/{articleNumber}/variantNumber/{variantNumber}" +
              "/bundleNumber/{bundleNumber}/languageCode/{languageCode}";

    @Steps
    private ArticleDetailsByCriteriaApiSteps searchCriteriaApiSteps;


    @Step
    public Response getArticleDetailsByBundle (String countryCode, String salesLine, String storeNumber,
                                                          String articleNumber, String variantNumber,
                                                          String bundleNumber, String languageCode, String token) {
        return searchCriteriaApiSteps.authorizedGiven(token)
                .when()
                .get(search_details, countryCode, salesLine, storeNumber,articleNumber,variantNumber,bundleNumber,languageCode);
    }

}
