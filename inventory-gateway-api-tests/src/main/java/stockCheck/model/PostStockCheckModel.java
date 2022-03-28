package stockCheck.model;

public class PostStockCheckModel {
  ArticleId articleId;
  private Integer stockCheckCause;
  private Integer stock;
  private String stockCheckDate;
  private String creationUser;

  public PostStockCheckModel() {

  }

    public PostStockCheckModel(ArticleId articleId, String stockCheckDate, Integer stockCheckCause, Integer stock, String creationUser) {
      this.articleId = articleId;
      this.stockCheckCause = stockCheckCause;
      this.stockCheckDate = stockCheckDate;
      this.stock = stock;
      this.creationUser = creationUser;
    }

    public ArticleId getArticleId() {
    return articleId;
  }

  public Integer getStockCheckCause() {
    return stockCheckCause;
  }

  public Integer getStock() {
    return stock;
  }

  public String getStockCheckDate() {
    return stockCheckDate;
  }

  public String getCreationUser() {
    return creationUser;
  }

  public void setArticleId(ArticleId articleId) {
    this.articleId = articleId;
  }

  public void setStockCheckCause(Integer stockCheckCause) {
    this.stockCheckCause = stockCheckCause;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public void setStockCheckDate(String stockCheckDate) {
    this.stockCheckDate = stockCheckDate;
  }

  public void setCreationUser(String creationUser) {
    this.creationUser = creationUser;
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
