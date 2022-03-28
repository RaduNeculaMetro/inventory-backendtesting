package stockCheck.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class PostSimilarArticlesModel {

    private String sourceSystem;
    private String creationUser;
    private ArticleId articleId;
    private List<SimilarArticles> similarArticles;

    public PostSimilarArticlesModel(ArticleId articleId, List<SimilarArticles> similarArticles) {
        this.articleId = articleId;
        this.similarArticles = similarArticles;

    }

@Getter
@Setter
    public static class ArticleId
    {
        private int articleNumber;
        private int variantNumber;
        private int bundleNumber;
    }

@Setter
@Getter
    public static class SimilarArticles
    {
        private String stockCheckType;
        private int similarArticleNumber;
        private int similarVariantNumber;
        private int similarBundleNumber;
        private int similarSubsystemArticleNumber;
        private String similarArticleDescription;
        private String similarVariantDescription;
        private int similarSellingBundleContent;
        private int similarOrderStatus;
        private int similarSystemStock;
        private String imageUrl;
        private String stockCorrectionTypeCode;

    }
}
