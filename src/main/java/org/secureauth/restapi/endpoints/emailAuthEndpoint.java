package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.ResponseObject;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class emailAuthEndpoint {

    public static String sendOtpByEmail(String host, String port, boolean ssl,boolean selfSigned, String realm, String appId, String appKey, String userId, String factorId){
        SAAccess saAccess =  new SAAccess(host, port, ssl,selfSigned, realm, appId, appKey);
        ResponseObject emailResp = saAccess.deliverOTPByEmail(userId, factorId);
        if (emailResp != null)
        {
            String output = XMLUtil.convertObjectToXML(emailResp);
            return output;
        }
        else
        {
            String output = "<apiError>DeliverOTPByEmail returned null.</apiError>";
            return output;
        }
    }
}
