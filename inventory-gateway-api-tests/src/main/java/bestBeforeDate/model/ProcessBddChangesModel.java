package bestBeforeDate.model;


import lombok.Data;

import java.util.List;

@Data
public class ProcessBddChangesModel {
    private int articleNumber;
    private int variantNumber;
    private int bundleNumber;
    private int subsystemArticleNumber;
    private String creationUser;
    private List<RemovedBestBeforeDateList> removedBestBeforeDateList;
    private List<NewBestBeforeDatesList> newBestBeforeDatesList;
    private String source;

    @Data
  public static class RemovedBestBeforeDateList {
    private String bestBeforeDate;
    private int quantity;
    private String id;
    private String lotNumber;
    }

    @Data
  public static class NewBestBeforeDatesList {
    private String bestBeforeDate;
    private int quantity;
    private String lotNumber;
  }

}
