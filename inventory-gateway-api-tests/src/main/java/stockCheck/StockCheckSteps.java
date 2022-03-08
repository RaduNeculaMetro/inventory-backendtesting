package stockCheck;

import net.thucydides.core.annotations.Steps;

public class StockCheckSteps {

    private static final String STOCK_CHECK_DEPARTMENT_URI = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stockchecks-departments";
    private static final String STOCK_CHECK_TYPE_URI = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/stock-check/type";
    private static final String STOCK_CHECK_CHECKED_V0_URI = "/v0/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/languagecode/{languageCode}/stock-check/checked";
    private static final String STOCK_CHECK_CHECKED_V1_URI = "/v1/countrycode/{countryCode}/salesline/{salesLine}/storenumber/{storeNumber}/languagecode/{languageCode}/stock-check/checked";
    private static final String STOCK_CHECK_REASONID_URI = "/inventorygateway/api/v0/countrycode/{countryCode}/languagecode/{languageCode}/stock-check-reasons/{stockCheckReasonId}";


    @Steps
    private StockCheckApiSteps stockCheckApiSteps;



}
