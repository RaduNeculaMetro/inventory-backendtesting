package stockCheck.steps;

import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import stockCheck.model.*;
import utils.LoggerUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StockCheckSteps {

    private static final String STOCK_CHECK_SIMILAR_ARTICLES_MOVEMENT_ENDPOINT = "v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stock-check/similar-articles-stock-movement";
    private static final String STOCK_CHECKS_DEPARTMENTS_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stockchecks-departments";
    private static final String STOCK_CHECK_TYPE_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stock-check/type";
    private static final String STOCK_CHECK_CHECKED_V0_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/languagecode/{languageCode}/stock-check/checked";
    private static final String STOCK_CHECK_CHECKED_V1_ENDPOINT = "/v1/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/languagecode/{languageCode}/stock-check/checked";
    private static final String STOCK_CHECK_REASONID_ENDPOINT = "/v0/countrycode/{countryCode}/languagecode/{languageCode}/stock-check-reasons/{stockCheckReasonId}";
    private static final String STOCK_CHECK_SIMILAR_ARTICLES_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stock-check/similar-articles";
    private static final String STOCK_CHECK_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stock-check";
    private static final String STOCK_CHECK_REASONS_ENDPOINT = "/v0/countrycode/{countryCode}/languagecode/{languageCode}/stock-check-reasons";
    private static final String STOCK_CHECK_MANUAL_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/manual-stock-check";
    private static final String STOCK_CHECK_OPEN_V0_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/languagecode/{languageCode}/stock-check/open";
    private static final String STOCK_CHECK_OPEN_V1_ENDPOINT = "/v1/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/languagecode/{languageCode}/stock-check/open" ;
    private static final String STOCK_CHECK_SALESLINE_REASONS_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/languagecode/{languageCode}/stock-check/reasons";
    private static final String STOCK_CHECK_TYPE_SIMILAR_ARTICLES_ENDPOINT = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/articlenumber/{articleNumber}/variantnumber/{variantNumber}/bundlenumber/{bundleNumber}/stockchecktype/{stockCheckType}/stock-check/similar-articles";


    @Steps
    private StockCheckApiSteps stockCheckApiSteps;

    @Step
    public Response postSimilarArticlesStockMovement(String countryCode, String salesLine, String storeNumber,
                                                     String token, List<stockCheck.model.SimilarArticlesStockMovement.SimilarArticlesStockMovementRequest> payload) {
        return stockCheckApiSteps.authorizedGiven(token)
            .body(payload)
            .when()
            .post(STOCK_CHECK_SIMILAR_ARTICLES_MOVEMENT_ENDPOINT,
                    countryCode,salesLine,storeNumber);

    }

    @Step
    public Response getStockChecksByDepartments(String countryCode, String salesLine,
                                                String storeNumber, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECKS_DEPARTMENTS_ENDPOINT, countryCode, salesLine, storeNumber));
    }

    @Step
    public Response getStockCheckType(String countryCode, String salesLine,
                                                String storeNumber, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECK_TYPE_ENDPOINT, countryCode, salesLine, storeNumber));
    }

    @Step
    public Response getStockCheckV0Checked(String countryCode, String salesLine,
                                      String storeNumber, String language, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECK_CHECKED_V0_ENDPOINT, countryCode, salesLine, storeNumber, language));
    }

    @Step
    public Response getStockCheckV1Checked(String countryCode, String salesLine,
                                           String storeNumber, String language, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECK_CHECKED_V1_ENDPOINT, countryCode, salesLine, storeNumber, language));
    }

    @Step
    public Response getStockCheckReasons(String countryCode, String salesLine,
                                         String language, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECK_SALESLINE_REASONS_ENDPOINT, countryCode, salesLine, language));
    }

    @Step
    public Response getStockCheckOpenV0(String countryCode, String salesLine, String storeNumber,
                                         String language, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECK_OPEN_V0_ENDPOINT, countryCode, salesLine,storeNumber, language));
    }

    @Step
    public Response getStockCheckOpenV1(String countryCode, String salesLine, String storeNumber,
                                              String language, String token) {

        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .when()
                .get(STOCK_CHECK_OPEN_V1_ENDPOINT, countryCode, salesLine,storeNumber, language));
    }

    @Step
    public Response postStockCheck(String countryCode, String salesLine, String storeNumber,
                                   String token, Integer articleNumber, Integer variantNumber, Integer bundleNumber,
                                   String stockCheckDate, Integer stockCheckCause,
                                   Integer stock, String creationUser) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .body(Collections.singletonList(preparePostStockCheckModel(articleNumber,variantNumber, bundleNumber,
                         stockCheckDate, stockCheckCause,
                         stock, creationUser)))
                .when()
                .post(STOCK_CHECK_ENDPOINT, countryCode, salesLine,storeNumber));
    }

    @Step
    public Response patchStockCheck(String countryCode, String salesLine, String storeNumber,
                                   String token,Integer articleNumber, Integer variantNumber,
                                    Integer bundleNumber,String stockCheckReason,String stockCheckId,
                                    Integer countedStock, String changeUser) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .body(Collections.singletonList(preparePatchStockCheckModel(articleNumber,variantNumber, bundleNumber,
                        stockCheckReason, stockCheckId, countedStock,changeUser)))
                .when()
                .patch(STOCK_CHECK_ENDPOINT, countryCode, salesLine,storeNumber));
    }

    @Step
    public Response postManualStockCheck(String countryCode, String salesLine, String storeNumber,
                                    String token,Integer articleNumber, Integer variantNumber,
                                    Integer bundleNumber,String stockCheckDate,String source) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .body(preparePostManualStockCheckModel(articleNumber,variantNumber, bundleNumber,
                        stockCheckDate, source))
                .when()
                .post(STOCK_CHECK_MANUAL_ENDPOINT, countryCode, salesLine,storeNumber));
    }

    @Step
    public Response postStockCheckReasons(String countryCode, String language,String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .body(prepareStockCheckReasonsModel())
                .when()
                .post(STOCK_CHECK_REASONS_ENDPOINT, countryCode, language));
    }

    @Step
    public Response postStockCheckSimilarArticles(String countryCode, String salesLine,
                                                  String storeNumber, String token) {
        return logResponse(stockCheckApiSteps.authorizedGiven(token)
                .body(preparePostSimilarArticlesModel())
                .when()
                .post(STOCK_CHECK_SIMILAR_ARTICLES_ENDPOINT, countryCode, salesLine, storeNumber));
    }

  @Step
  public Response patchStockCheckSimilarArticles(
      String countryCode, String salesLine, String storeNumber, String token) {
    return logResponse(
        stockCheckApiSteps
            .authorizedGiven(token)
            .body(preparePatchSimilarArticlesModel())
            .when()
            .patch(STOCK_CHECK_SIMILAR_ARTICLES_ENDPOINT, countryCode, salesLine, storeNumber));
    }

    @Step
    public Response putStockCheckReasonId(
        String countryCode, String languageCode, String stockCheckReasonId, String token) {
            return logResponse(
                    stockCheckApiSteps
                            .authorizedGiven(token)
                            .body(preparePutReasonIdModel())
                            .when()
                            .put(STOCK_CHECK_REASONID_ENDPOINT, countryCode, languageCode, stockCheckReasonId));
    }

    @Step
    public Response getStockCheckTypeSimilarArticles
            (String countryCode, String salesLine, String storeNumber,
             Integer articleNumber, Integer variantNumber,
             Integer bundleNumber, String stockCheckType, String token) {

        return logResponse(
                stockCheckApiSteps
                        .authorizedGiven(token)
                        .body(preparePutReasonIdModel())
                        .when()
                        .get(STOCK_CHECK_TYPE_SIMILAR_ARTICLES_ENDPOINT,countryCode,salesLine,storeNumber,
                                articleNumber,variantNumber,bundleNumber,stockCheckType ));
    }










    private Response logResponse (Response response) {
        LoggerUtils.doOnLogResponse(() -> response.then().log().all());
        return response;
    }

    private StockCheckReasonsModel.StockCheckReasonsModelRequest prepareStockCheckReasonsModel() {
    StockCheckReasonsModel.StockCheckReasonsModelRequest request =
            new StockCheckReasonsModel.StockCheckReasonsModelRequest();
    request .setChangeDate("2021-01-11")
            .setChangeUser("admin")
            .setCountryCode("RO")
            .setReasonText("MAINTENANCE")
            .setCreationUser("altAdmin")
            .setChangeDate("2022-01-10")
            .setCreationDate("2022-01-01")
            .setId("7")
            .setLanguage("RO")
            .setOrderNo(1)
            .addDefaults(createDefaults());
    return request;
    }

    private StockCheckReasonsModel.StockCheckReasonsModelRequest.Defaults createDefaults() {
        StockCheckReasonsModel.StockCheckReasonsModelRequest.Defaults defaults= new StockCheckReasonsModel.StockCheckReasonsModelRequest.Defaults();
        defaults.applyFor = "P";
        defaults.stockCorrectionCode = "MAINTENANCE";

        return defaults;
    }

    private PostStockCheckModel preparePostStockCheckModel(Integer articleNumber, Integer variantNumber, Integer bundleNumber,
                                                           String stockCheckDate, Integer stockCheckCause,
                                                           Integer stock, String creationUser){
        PostStockCheckModel.ArticleId articleId = new PostStockCheckModel.ArticleId(articleNumber,variantNumber,bundleNumber);
        PostStockCheckModel model = new PostStockCheckModel(articleId, stockCheckDate, stockCheckCause,stock,creationUser);

        return model;
    }

    private PatchStockCheckModel preparePatchStockCheckModel(Integer articleNumber, Integer variantNumber,
                                                            Integer bundleNumber,String stockCheckReason,String stockCheckId,
                                                            Integer countedStock, String changeUser){
        PatchStockCheckModel.ArticleId articleId = new PatchStockCheckModel.ArticleId(articleNumber,variantNumber,bundleNumber);
        PatchStockCheckModel model = new PatchStockCheckModel(articleId,stockCheckId, countedStock, stockCheckReason ,changeUser);

        return model;
    }

    private ManualStockCheckModel preparePostManualStockCheckModel(Integer articleNumber, Integer variantNumber,
                                                                   Integer bundleNumber, String stockCheckDate, String source){

        ManualStockCheckModel.Articles.ArticleId articleId = new ManualStockCheckModel.Articles.ArticleId(articleNumber,variantNumber,bundleNumber);
        ManualStockCheckModel.Articles arcticles = new ManualStockCheckModel.Articles(stockCheckDate,articleId);
        List<ManualStockCheckModel.Articles> dataArray = new ArrayList<>();
        dataArray.add(arcticles);

        return new ManualStockCheckModel(dataArray, source);
    }

  private PostSimilarArticlesModel preparePostSimilarArticlesModel() {

        PostSimilarArticlesModel.SimilarArticles similarArticles = new PostSimilarArticlesModel.SimilarArticles() ;
        PostSimilarArticlesModel.ArticleId articleId = new PostSimilarArticlesModel.ArticleId();
        PostSimilarArticlesModel model = new PostSimilarArticlesModel(articleId, Collections.singletonList(similarArticles));

        similarArticles.setStockCheckType("MinuStock");
        similarArticles.setSimilarArticleNumber(358545);
        similarArticles.setSimilarOrderStatus(1);
        similarArticles.setSimilarBundleNumber(1);
        similarArticles.setImageUrl("https://cdn.metro-group.com/bg/bg_pim_170108001001_01.png");
        similarArticles.setSimilarVariantDescription("description");
        similarArticles.setStockCorrectionTypeCode("MAINTENANCE");
        similarArticles.setSimilarSystemStock(1);
        similarArticles.setSimilarSubsystemArticleNumber(358545);
        similarArticles.setSimilarSellingBundleContent(1);
        similarArticles.setSimilarArticleDescription("description");

        articleId.setArticleNumber(426899);
        articleId.setVariantNumber(1);
        articleId.setBundleNumber(3);
        model.setCreationUser("admin");
        model.setSourceSystem("Mstore");

        return model;
  }

  private PatchSimilarArticlesModel preparePatchSimilarArticlesModel () {
        PatchSimilarArticlesModel model = new PatchSimilarArticlesModel();
        model.setSimilarArticleNumber(1);
        model.setStockCheckType("MinusStock");
        model.setArticleNumber(25054);
        model.setSimilarBundleNumber(1);
        model.setSimilarVariantNumber(1);
        model.setBundleNumber(2);
        model.setChangeUser("admin");
        model.setVariantNumber(2);

        return model;
  }

    private PutReasonIdModel preparePutReasonIdModel () {

        PutReasonIdModel model = new PutReasonIdModel();
        PutReasonIdModel.Defaults defaults = new PutReasonIdModel.Defaults();
        defaults.setStockCorrectionCode("MAINTENANCE");
        defaults.setApplyFor("P");

        model.setDefaults(Collections.singletonList(defaults));
        model.setReasonText("MAINTENANCE");
        model.setOrderNo(1);
        model.setChangeUser("admin");
        return model;
    }

}
