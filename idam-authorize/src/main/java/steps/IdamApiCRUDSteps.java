package steps;

import utils.EnvProperties;
import utils.api.BaseApiCRUDSteps;

public abstract class IdamApiCRUDSteps extends BaseApiCRUDSteps {

    protected IdamApiCRUDSteps(String baseURL) {
        super(EnvProperties::getIdamUri, baseURL);
    }

}
