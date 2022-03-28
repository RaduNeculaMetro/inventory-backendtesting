package bestBeforeDate.model;

import lombok.Data;

import java.util.List;

@Data
public class BbdCheckResultModel {

    private String bbdCheckId;
    private String creationUser;
    private double quantityRemovedFromShelf;
    private List<Actions> actions;

@Data
  public static class Actions {
    private String actionName;
    private double quantity;
    private String parameters;
  }

}
