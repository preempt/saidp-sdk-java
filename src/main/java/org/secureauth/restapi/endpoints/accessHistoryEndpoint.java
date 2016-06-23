package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.ResponseObject;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 6/23/16.
 */
public class accessHistoryEndpoint {

    public static String getAccessHistoryEndpoint(String host, String port, boolean ssl,boolean selfSigned, String realm, String appId, String appKey, String userId, String endUserIp) {
        SAAccess saAccess =  new SAAccess(host, port, ssl,selfSigned, realm, appId, appKey);
        ResponseObject ahResp = saAccess.accessHistory(userId, endUserIp);
        if (ahResp != null) {
            String output = XMLUtil.convertObjectToXML(ahResp);
            return output;
        } else {
            String output = "<apiError>AccessHistoryQuery returned null.</apiError>";
            return output;
        }
    }
}
