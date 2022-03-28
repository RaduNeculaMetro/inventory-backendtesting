package stockCheck.steps;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasKey;

public class StockCheckAsserter {

    public void assertStockCheckOpen(Response response) {
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckId")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckType")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("articleId")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("subsystemArticleNumber")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("articleDescription")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("variantDescription")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("departmentNumber")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("mainMerchandiseGroup")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("weightArticle")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("systemStock")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("countedStock")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockLocations")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastSalesDate")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastGoodsReceivingDate")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastStockCorrectionDate")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckTimestamp")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("imageUrl")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("sellingBundleContent")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("openGoodReceiving")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("orderingStatus")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastGRQuantity")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastSalesQuantity")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastSCQuantity")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckReason")));
    }

    public void assertStockCheckChecked(Response response) {
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckId")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckType")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("articleId")));
        response.then().body("stockCheckListViews[0].articleId", allOf(hasKey("articleNumber")));
        response.then().body("stockCheckListViews[0].articleId", allOf(hasKey("variantNumber")));
        response.then().body("stockCheckListViews[0].articleId", allOf(hasKey("bundleNumber")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("subsystemArticleNumber")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("articleDescription")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("variantDescription")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("departmentNumber")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("mainMerchandiseGroup")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("weightArticle")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("systemStock")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("countedStock")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockLocations")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastSalesDate")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastGoodsReceivingDate")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastStockCorrectionDate")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckTimestamp")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("imageUrl")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("sellingBundleContent")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("openGoodReceiving")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("orderingStatus")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastGRQuantity")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastSalesQuantity")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("lastSCQuantity")));
        response.then().body("stockCheckListViews[0]", allOf(hasKey("stockCheckReason")));
    }

    public void assertStockCheckReasons(Response response) {
        response.then().body("[0]", allOf(hasKey("id")));
        response.then().body("[0]", allOf(hasKey("countryCode")));
        response.then().body("[0]", allOf(hasKey("language")));
        response.then().body("[0]", allOf(hasKey("reasonText")));
        response.then().body("[0]", allOf(hasKey("creationDate")));
        response.then().body("[0]", allOf(hasKey("changeUser")));
        response.then().body("[0]", allOf(hasKey("changeDate")));
        response.then().body("[0]", allOf(hasKey("orderNo")));
        response.then().body("[0]", allOf(hasKey("defaults")));
        response.then().body("[0].defaults[0]", allOf(hasKey("stockCorrectionCode")));
        response.then().body("[0].defaults[0]", allOf(hasKey("applyFor")));
    }

}
