package utils;

import net.thucydides.core.annotations.Steps;

public class EnvProperties {

    @Steps
    private EnvironmentProperties environmentProperties;
    public EnvironmentProperties getEnvironmentProperties() {
        return environmentProperties;
    }

    public String getPPUsername () {
        return environmentProperties.getProperty("users.username");
    }
    public String getPPUserPassword () {
        return environmentProperties.getProperty("users.userPassword");
    }

    public String getbasicUsername () {
        return environmentProperties.getProperty("basicUsername");
    }
    public String getbasicPassword () {
        return environmentProperties.getProperty("basicPassword");
    }

    public String getIdamUri ()
    {
        return environmentProperties.getProperty("idam.path.uri");
    }

    public String getInventoryGatewayUri () {
        return environmentProperties.getProperty("inventoryGateway.path.uri");
    }
}
