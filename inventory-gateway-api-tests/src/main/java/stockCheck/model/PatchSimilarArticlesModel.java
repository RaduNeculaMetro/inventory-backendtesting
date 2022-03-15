package stockCheck.model;

import lombok.Data;

import java.util.List;

@Data
public class PatchSimilarArticlesModel {

    private int articleNumber;
    private int variantNumber;
    private int bundleNumber;
    private int similarArticleNumber;
    private int similarVariantNumber;
    private int similarBundleNumber;
    private String stockCheckType;
    private String changeUser;

}
