package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.DFPValidateResponse;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class dfpValidateEndpoint {

    public static String validateDfp(String host, String port, Boolean ssl, String realm, String appId, String appKey, String userId, String host_addr, String jsonFp, String accept, String accept_charset, String accept_encoding, String accept_lang){
        SAAccess saAccess =  new SAAccess(host, port, ssl, realm, appId, appKey);
        DFPValidateResponse dfpVal = saAccess.DFPValidateNewFingerprint(userId,host_addr, jsonFp, accept, accept_charset, accept_encoding, accept_lang);
        if (dfpVal != null) {
            String output = XMLUtil.convertObjectToXML(dfpVal);
            return output;
        }
        else
        {
            String output = "<apiError>DFPValidate returned null.</apiError>";
            return output;
        }
    }
}
