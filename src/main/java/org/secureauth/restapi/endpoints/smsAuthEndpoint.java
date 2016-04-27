package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.ResponseObject;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class smsAuthEndpoint {

    public static String sendOtpBySMS(String host, String port, Boolean ssl, String realm, String appId, String appKey, String userId, String factorId){
        SAAccess saAccess =  new SAAccess(host, port, ssl, realm, appId, appKey);
        ResponseObject smsResp = saAccess.deliverOTPBySMS(userId, factorId);
        if (smsResp != null){
            String output = XMLUtil.convertObjectToXML(smsResp);
            return output;
        }
        else
        {
            String output = "<apiError>DeliverOTPBySMS returned null.</apiError>";
            return output;
        }
    }
}
