package stockCheck.model;

import java.util.List;

public class ManualStockCheckModel {
    private String source;
    private List<Articles> articles;

    public ManualStockCheckModel(List <Articles> articles, String source) {
        this.articles = articles;
        this.source= source;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticleId(List<Articles> articleId) {
        this.articles = articles;
    }

  public static class Articles {
    ArticleId articleId;
    String stockCheckDate;

      public Articles(String stockCheckDate, ArticleId articleId) {
          this.articleId = articleId;
          this.stockCheckDate = stockCheckDate;
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
    }
}
}
