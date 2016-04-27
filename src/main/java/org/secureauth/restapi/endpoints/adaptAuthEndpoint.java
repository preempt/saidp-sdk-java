package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.AdaptiveAuthResponse;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class adaptAuthEndpoint {

    public static String getAdaptEndpoint(String host, String port, Boolean ssl, String realm, String appId, String appKey, String userId, String endUserIp){
        SAAccess saAccess =  new SAAccess(host, port, ssl, realm, appId, appKey);
        AdaptiveAuthResponse adaptResp = saAccess.adaptiveAuthQuery(userId, endUserIp);
        if (adaptResp != null){
            String output = XMLUtil.convertObjectToXML(adaptResp);
            return output;
        }
        else
        {
            String output = "<apiError>AdaptiveAuthQuery returned null.</apiError>";
            return output;
        }
    }
}
