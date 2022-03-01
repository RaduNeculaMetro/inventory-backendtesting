package articleDetails.steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;


public class ArticleDetailsByCriteriaSteps {

    private static final String search_details
            = "/v0/countryCode/{countryCode}/storeNumber/{storeNumber}/article-details/searchCriteria/{searchCriteria}";

    @Steps
    private ArticleDetailsByCriteriaApiSteps articleDetailsByCriteriaApiSteps;


    @Step
    public Response getArticleDetailsByCriteria(String countryCode, String storeNumber,
                                                String searchCriteria, String token) {
       return articleDetailsByCriteriaApiSteps.authorizedGiven(token)
                .when()
                .get(search_details, countryCode, storeNumber,searchCriteria);
    }

}
