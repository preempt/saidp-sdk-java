package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.DFPConfirmResponse;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class dfpConfirmEndpoint {

    public static String confirmDfp(String host, String port, boolean ssl,boolean selfSigned, String realm, String appId, String appKey, String userId, String fp_id){
        SAAccess saAccess =  new SAAccess(host, port, ssl,selfSigned, realm, appId, appKey);
        DFPConfirmResponse confirmResp = saAccess.DFPConfirm(userId, fp_id);
        if (confirmResp != null){
            String output = XMLUtil.convertObjectToXML(confirmResp);
            return output;
        }
        else
        {
            String output = "<apiError>DFPConfirm returned null.</apiError>";
            return output;
        }
    }
}
