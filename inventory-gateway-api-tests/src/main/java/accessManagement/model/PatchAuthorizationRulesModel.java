package accessManagement.model;

import lombok.Data;

@Data
public class PatchAuthorizationRulesModel {

    private int id;
    private String accessFor;
    private String startDate;
    private String endDate;
    private String environment;
    private String changeUser;
    private int changeUserMetroId;

    public PatchAuthorizationRulesModel() {
        this.id = 1369;
        this.accessFor = "MSTORE_EXTERNAL";
        this.startDate = "2022-01-01";
        this.endDate = "2023-01-01";
        this.environment = "pp";
        this.changeUser = "testUser";
        this.changeUserMetroId = 10620872;
    }
}
