package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.ResponseObject;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class voiceAuthEndpoint {

    public static String sendOtpByVoice(String host, String port, Boolean ssl, String realm, String appId, String appKey, String userId, String factorId){
        SAAccess saAccess =  new SAAccess(host, port, ssl, realm, appId, appKey);
        ResponseObject voiceResp = saAccess.deliverOTPByPhone(userId, factorId);
        if (voiceResp != null){
            String output = XMLUtil.convertObjectToXML(voiceResp);
            return output;
        }
        else
        {
            String output = "<apiError>DeliverOTPByPhone returned null.</apiError>";
            return output;
        }
    }
}
