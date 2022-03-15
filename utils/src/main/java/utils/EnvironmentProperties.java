package utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

import java.util.Optional;

public class EnvironmentProperties {

    private EnvironmentVariables environmentVariables;

    public String getProperty(String property) {
        return EnvironmentSpecificConfiguration
                .from(environmentVariables).getProperty(property);
    }

    public Optional<String> getOptionalProperty (String optionalProperty) {
        return EnvironmentSpecificConfiguration
                .from(environmentVariables).getOptionalProperty(optionalProperty);
    }
}
