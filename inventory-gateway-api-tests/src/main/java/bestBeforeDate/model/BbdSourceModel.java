package bestBeforeDate.model;

import lombok.Data;

import java.util.List;

@Data
public class BbdSourceModel {
    private int articleNumber;
    private int variantNumber;
    private int bundleNumber;
    private int subsystemArticleNumber;
    private List<BestBeforeDateList> bestBeforeDateList;
    private String creationUser;

@Data
public static class BestBeforeDateList {
        private String bestBeforeDate;
        private int quantity;
        private String lotNumber;

    }
}
