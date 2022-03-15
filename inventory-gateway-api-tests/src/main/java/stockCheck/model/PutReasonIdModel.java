package stockCheck.model;

import lombok.Data;

import java.util.List;

@Data
public class PutReasonIdModel {

    private String reasonText;
    private String changeUser;
    private int orderNo;
    private List<Defaults> defaults;

@Data
  public static class Defaults {
    private String stockCorrectionCode;
    private String applyFor;
  }
}
