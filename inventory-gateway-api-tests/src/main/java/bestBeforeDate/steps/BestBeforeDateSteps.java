package bestBeforeDate.steps;

import bestBeforeDate.model.BbdCheckResultModel;
import bestBeforeDate.model.BbdSourceModel;
import bestBeforeDate.model.ProcessBddChangesModel;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.checkerframework.checker.units.qual.C;

import java.util.Collections;
import java.util.Map;

public class BestBeforeDateSteps {

    @Steps
    private BestBeforeDateApiSteps bestBeforeDateApiSteps;

    private static final String PROCESS_BDD_CHANGES_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/process-bbd-changes";
    private static final String BDD_LIST_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/language/{languageCode}/bbd-list";
    private static final String BDD_REMOVED_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/language/{languageCode}/bbd-removed";
    private static final String BDD_MISSING_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/language/{languageCode}/bbd-missing";
    private static final String BDD_CHECK_RESULTS_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/language/{languageCode}/best-before-date/bbd-check-results";
    private static final String SOURCE_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/source/{source}";
    private static final String WORKLOAD_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/language/{languageCode}/workload";
    private static final String REMOVED_COUNT_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/bbd-removed-count";
    private static final String SEARCH_CRITERIA_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/language/{languageCode}/best-before-date/article/searchCriteria/{searchCriteria}";
    private static final String BDD_LIST_COUNT_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/best-before-date/bbd-list-count";


    @Step
    public Response getBddRemoved(String countryCode, String salesLine, String storeNumber, String language, String token, Map<String,String> query) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .queryParams(query)
                .get(BDD_REMOVED_ENDPOINT, countryCode, salesLine, storeNumber, language);
    }

    @Step
    public Response getBddMissing(String countryCode, String salesLine, String storeNumber, String language, String token, Map<String,String> query) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .queryParams(query)
                .get(BDD_MISSING_ENDPOINT, countryCode, salesLine, storeNumber, language);
    }

    @Step
    public Response getBddList(String countryCode, String salesLine, String storeNumber, String language, String token, Map<String,String> query) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .queryParams(query)
                .get(BDD_LIST_ENDPOINT, countryCode, salesLine, storeNumber, language);
    }

    @Step
    public Response postProcessBddChanges(String countryCode, String salesLine, String storeNumber, String token) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .body(preparePostProcessBddChangesModel())
                .post(PROCESS_BDD_CHANGES_ENDPOINT, countryCode, salesLine, storeNumber);
    }

    @Step
    public Response getWorkload(String countryCode, String salesLine,
                                String storeNumber, String language, String token, Map<String,String> query) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .queryParams(query)
                .get(WORKLOAD_ENDPOINT, countryCode, salesLine, storeNumber, language);
    }

    @Step
    public Response postBbdSource(String countryCode, String salesLine, String storeNumber, String source, String token) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .body(Collections.singletonList(prepareBbdSourceModel()))
                .post(SOURCE_ENDPOINT,countryCode, salesLine, storeNumber, source);
    }

    @Step
    public Response postBbdCheckResults(String countryCode, String salesLine, String storeNumber, String language, String token) {
        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .body(prepareBbdCheckResultsModel())
                .post(BDD_CHECK_RESULTS_ENDPOINT,countryCode, salesLine, storeNumber, language);
    }

    @Step
    public Response getBbdArticleSearchCriteria(String countryCode, String salesLine,
                                                String storeNumber, String language, String searchCriteria, String token) {

        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .get(SEARCH_CRITERIA_ENDPOINT,countryCode, salesLine, storeNumber, language, searchCriteria);
    }

    @Step
    public Response getBbdListCount(String countryCode, String salesLine,
                                    String storeNumber, Map<String, String> query, String token) {
        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .queryParams(query)
                .get(BDD_LIST_COUNT_ENDPOINT, countryCode, salesLine, storeNumber);
    }

    @Step
    public Response getBbdRemovedCount(String countryCode, String salesLine,
                                       String storeNumber, String language, Map<String, String> query, String token) {
        return bestBeforeDateApiSteps.authorizedGiven(token)
                .when()
                .contentType(ContentType.JSON)
                .queryParams(query)
                .get(BDD_REMOVED_ENDPOINT, countryCode, salesLine, storeNumber, language);
    }






    private ProcessBddChangesModel preparePostProcessBddChangesModel () {

        ProcessBddChangesModel.NewBestBeforeDatesList newBestBefore = new ProcessBddChangesModel.NewBestBeforeDatesList();
        ProcessBddChangesModel.RemovedBestBeforeDateList removeBestBefore = new ProcessBddChangesModel.RemovedBestBeforeDateList();
        ProcessBddChangesModel model = new ProcessBddChangesModel();
        newBestBefore.setBestBeforeDate("2300-01-01");
        newBestBefore.setQuantity(0);
        newBestBefore.setLotNumber("string");

        removeBestBefore.setBestBeforeDate("2020-01-01");
        removeBestBefore.setId("1f50d399-5a82-4a28-addc-70629a17e264");
        removeBestBefore.setQuantity(0);
        removeBestBefore.setLotNumber("string");

        model.setBundleNumber(1);
        model.setArticleNumber(673320);
        model.setCreationUser("test.auto@metro.digital");
        model.setSource("JOB_BBD_ARTICLES");
        model.setVariantNumber(1);
        model.setSubsystemArticleNumber(334550);

        model.setNewBestBeforeDatesList(Collections.singletonList(newBestBefore));
        model.setRemovedBestBeforeDateList(Collections.singletonList(removeBestBefore));
        return model;
    }

    private BbdSourceModel prepareBbdSourceModel() {
        BbdSourceModel model = new BbdSourceModel();
        BbdSourceModel.BestBeforeDateList beforeDateList = new BbdSourceModel.BestBeforeDateList();
        beforeDateList.setBestBeforeDate("2300-01-01");
        beforeDateList.setQuantity(1);
        beforeDateList.setLotNumber("2");

        model.setSubsystemArticleNumber(26981);
        model.setBundleNumber(1);
        model.setCreationUser("test.auto@metro.digital");
        model.setArticleNumber(26981);
        model.setBestBeforeDateList(Collections.singletonList(beforeDateList));
        return model;
    }

    private BbdCheckResultModel prepareBbdCheckResultsModel() {
        BbdCheckResultModel model = new BbdCheckResultModel();
        BbdCheckResultModel.Actions bbdlist = new BbdCheckResultModel.Actions();
        bbdlist.setParameters(
        "{\"quantity\":4,\"markdownType\":1,\"validTo\":\"20200516\",\"reductionType\":1,\"reductionValue\":35}");
        bbdlist.setActionName("markdown");
        bbdlist.setQuantity(1);

        model.setBbdCheckId("7005c13b-66b3-4598-9910-90e250ddbf2f");
        model.setQuantityRemovedFromShelf(2);
        model.setCreationUser("test.auto@metro.digital");
        return model;
    }
}
