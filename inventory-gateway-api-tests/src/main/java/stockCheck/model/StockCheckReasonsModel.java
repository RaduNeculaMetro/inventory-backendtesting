package stockCheck.model;

import java.util.ArrayList;
import java.util.List;

public class StockCheckReasonsModel {

    public StockCheckReasonsModel () {
    }

  public static class StockCheckReasonsModelRequest {
    private String id;
    private String countryCode;
    private String language;
    private String reasonText;
    private String creationDate;
    private String creationUser;
    private String changeUser;
    private String changeDate;
    private Integer orderNo;
    private List<Defaults> defaults = new ArrayList<>();


    public StockCheckReasonsModelRequest() {

    }

      public String getId() {
          return id;
      }

      public StockCheckReasonsModelRequest setId(String id) {
          this.id = id;
          return this;
      }

      public String getCountryCode() {
          return countryCode;
      }

      public StockCheckReasonsModelRequest setCountryCode(String countryCode) {
          this.countryCode = countryCode;
          return this;
      }

      public String getLanguage() {
          return language;
      }

      public StockCheckReasonsModelRequest setLanguage(String language) {
          this.language = language;
          return this;
      }

      public String getReasonText() {
          return reasonText;
      }

      public StockCheckReasonsModelRequest setReasonText(String reasonText) {
          this.reasonText = reasonText;
          return this;
      }

      public String getCreationDate() {
          return creationDate;
      }

      public StockCheckReasonsModelRequest setCreationDate(String creationDate) {
          this.creationDate = creationDate;
          return this;
      }

      public String getCreationUser() {
          return creationUser;
      }

      public StockCheckReasonsModelRequest setCreationUser(String creationUser) {
          this.creationUser = creationUser;
          return this;
      }

      public String getChangeUser() {
          return changeUser;
      }

      public StockCheckReasonsModelRequest setChangeUser(String changeUser) {
          this.changeUser = changeUser;
          return this;
      }

      public String getChangeDate() {
          return changeDate;
      }

      public StockCheckReasonsModelRequest setChangeDate(String changeDate) {
          this.changeDate = changeDate;
          return this;
      }

      public Integer getOrderNo() {
          return orderNo;
      }

      public StockCheckReasonsModelRequest setOrderNo(Integer orderNo) {
          this.orderNo = orderNo;
          return this;
      }

      public List<Defaults> getDefaults() {
          return defaults;
      }

      public StockCheckReasonsModelRequest addDefaults(Defaults defaults) {
          this.defaults.add(defaults);
          return this;
      }

    public static class Defaults {
      public String stockCorrectionCode;
      public String applyFor;

      public Defaults() {
          this.stockCorrectionCode = "correctionCode";
          this.applyFor = "forWhat";
      }
    }
}

}
