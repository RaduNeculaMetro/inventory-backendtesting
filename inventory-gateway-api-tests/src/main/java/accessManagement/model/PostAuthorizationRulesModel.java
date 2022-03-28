package accessManagement.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class PostAuthorizationRulesModel {

    private String vertical;
    private String area;
    private String context;
//    AccessFor accessFor;
    private String accessFor;
    private String permission;
    private String description;
    private String startDate;
    private String endDate;
    private String mandatory;
    private String environment;
    private String ruleForCountry;
    private String creationUser;
    private int changeUserMetroId;

    public PostAuthorizationRulesModel() {
                this.vertical = "mstore.inventory";
                this.area = "backend";
                this.context = "GapCheck";
                this.permission = "Y";
                this.accessFor = "STORE_INVENTORY";
                this.description = "Automated test description";
                this.startDate = "2022-01-01";
                this.endDate = "3000-01-01";
                this.mandatory = "N";
                this.environment = "pp";
                this.ruleForCountry = "RO";
                this.creationUser = "TestUser";
                this.changeUserMetroId = 10131399;
    }

//    @Data
//    public static class AccessFor {
//        private List<String> roles;
//
//    }
}
