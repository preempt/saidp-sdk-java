package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.FactorsResponse;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class usersEndpoint {

    public static String getMFAOptions(String host, String port, boolean ssl,boolean selfSigned, String realm, String appId, String appKey, String userId){
        SAAccess saAccess =  new SAAccess(host, port, ssl,selfSigned, realm, appId, appKey);
        FactorsResponse factors = saAccess.factorsByUser(userId);
        if (factors != null){
            String output = XMLUtil.convertObjectToXML(factors);
            return output;
        }
        else
        {
            String output = "<apiError>FactorsByUser returned null.</apiError>";
            return output;
        }
    }
}
