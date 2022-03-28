package accessManagement.model;

import lombok.Data;

import java.util.List;

@Data
public class PostRetrieveAuthorizationModel {

    private List<String> roles;
    private List<String> countries;
    private List<String> stores;
    private List<String> userType;
}
