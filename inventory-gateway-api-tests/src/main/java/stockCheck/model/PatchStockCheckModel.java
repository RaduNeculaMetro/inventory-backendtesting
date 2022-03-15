package stockCheck.model;

public class PatchStockCheckModel {

    private String stockCheckId;
    ArticleId articleId;
    private String stockCheckReason;
    private Integer countedStock;
    private String changeUser;

    public PatchStockCheckModel() {

    }

    public PatchStockCheckModel(ArticleId articleId,String stockCheckId, Integer countedStock, String stockCheckReason, String changeUser) {
        this.articleId = articleId;
        this.stockCheckId = stockCheckId;
        this.stockCheckReason = stockCheckReason;
        this.countedStock = countedStock;
        this.changeUser = changeUser;
    }

    public ArticleId getArticleId() {
        return articleId;
    }

    public void setChangeUser(String changeUser) {
        this.changeUser = changeUser;
    }

    public String getChangeUser() {
        return changeUser;
    }

    public Integer getCountedStock() {
        return countedStock;
    }

    public void setCountedStock(Integer countedStock) {
        this.countedStock = countedStock;
    }

    public String getStockCheckReason() {
        return stockCheckReason;
    }

    public void setStockCheckReason(String stockCheckReason) {
        this.stockCheckReason = stockCheckReason;
    }

    public String getStockCheckId() {
        return stockCheckId;
    }

    public void setStockCheckId(String stockCheckId) {
        this.stockCheckId = stockCheckId;
    }

    public static class ArticleId {

        public ArticleId() {}

        private Integer articleNumber;
        private Integer variantNumber;
        private Integer bundleNumber;

        public ArticleId(Integer articleNumber, Integer variantNumber, Integer bundleNumber) {
            this.variantNumber = variantNumber;
            this.articleNumber = articleNumber;
            this.bundleNumber = bundleNumber;
        }

        public Integer getArticleNumber() {
            return articleNumber;
        }

        public Integer getVariantNumber() {
            return variantNumber;
        }

        public Integer getBundleNumber() {
            return bundleNumber;
        }

        public void setArticleNumber(Integer articleNumber) {
            this.articleNumber = articleNumber;
        }

        public void setVariantNumber(Integer variantNumber) {
            this.variantNumber = variantNumber;
        }

        public void setBundleNumber(Integer bundleNumber) {
            this.bundleNumber = bundleNumber;
        }
    }
}
