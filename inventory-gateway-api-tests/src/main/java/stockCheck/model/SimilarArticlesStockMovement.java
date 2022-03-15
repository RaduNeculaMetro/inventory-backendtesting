package stockCheck.model;


public class SimilarArticlesStockMovement {

    public SimilarArticlesStockMovement () {
    }

    public static class SimilarArticlesStockMovementRequest {
        private String stockCheckId;
        private int articleNumber;
        private int variantNumber;
        private int bundleNumber;
        private int quantityCorrection;
        private int similarArticleNumber;
        private int similarVariantNumber;
        private int similarBundleNumber;
        private int similarQuantityCorrection;
        private String stockCheckType;
        private String creationUser;
        private String stockCorrectionTypeCode;
        private String reason;

        public SimilarArticlesStockMovementRequest () {
            this.stockCheckId = "empty";
            this.articleNumber = 1;
            this.variantNumber = 1;
            this.bundleNumber = 1;
            this.quantityCorrection = 1;
            this.similarArticleNumber = 1;
            this.similarVariantNumber = 1;
            this.similarBundleNumber = 1;
            this.similarQuantityCorrection = 1;
            this.stockCheckType = "empty";
            this.creationUser = "empty";
            this.stockCorrectionTypeCode = "empty";
            this.reason = "empty";
        }

        public void setStockCheckId(String stockCheckId){
            this.stockCheckId = stockCheckId;
        }
        public String getStockCheckId(){
            return this.stockCheckId;
        }
        public void setArticleNumber(int articleNumber){
            this.articleNumber = articleNumber;
        }
        public int getArticleNumber(){
            return this.articleNumber;
        }
        public void setVariantNumber(int variantNumber){
            this.variantNumber = variantNumber;
        }
        public int getVariantNumber(){
            return this.variantNumber;
        }
        public void setBundleNumber(int bundleNumber){
            this.bundleNumber = bundleNumber;
        }
        public int getBundleNumber(){
            return this.bundleNumber;
        }
        public void setQuantityCorrection(int quantityCorrection){
            this.quantityCorrection = quantityCorrection;
        }
        public int getQuantityCorrection(){
            return this.quantityCorrection;
        }
        public void setSimilarArticleNumber(int similarArticleNumber){
            this.similarArticleNumber = similarArticleNumber;
        }
        public int getSimilarArticleNumber(){
            return this.similarArticleNumber;
        }
        public void setSimilarVariantNumber(int similarVariantNumber){
            this.similarVariantNumber = similarVariantNumber;
        }
        public int getSimilarVariantNumber(){
            return this.similarVariantNumber;
        }
        public void setSimilarBundleNumber(int similarBundleNumber){
            this.similarBundleNumber = similarBundleNumber;
        }
        public int getSimilarBundleNumber(){
            return this.similarBundleNumber;
        }
        public void setSimilarQuantityCorrection(int similarQuantityCorrection){
            this.similarQuantityCorrection = similarQuantityCorrection;
        }
        public int getSimilarQuantityCorrection(){
            return this.similarQuantityCorrection;
        }
        public void setStockCheckType(String stockCheckType){
            this.stockCheckType = stockCheckType;
        }
        public String getStockCheckType(){
            return this.stockCheckType;
        }
        public void setCreationUser(String creationUser){
            this.creationUser = creationUser;
        }
        public String getCreationUser(){
            return this.creationUser;
        }
        public void setStockCorrectionTypeCode(String stockCorrectionTypeCode){
            this.stockCorrectionTypeCode = stockCorrectionTypeCode;
        }
        public String getStockCorrectionTypeCode(){
            return this.stockCorrectionTypeCode;
        }
        public void setReason(String reason){
            this.reason = reason;
        }
        public String getReason(){
            return this.reason;
        }
    }
}
