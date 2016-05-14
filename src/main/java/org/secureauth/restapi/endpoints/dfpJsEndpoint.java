package org.secureauth.restapi.endpoints;

import org.secureauth.sarestapi.SAAccess;
import org.secureauth.sarestapi.data.JSObjectResponse;
import org.secureauth.sarestapi.util.XMLUtil;

/**
 * Created by jhickman on 4/26/16.
 */
public class dfpJsEndpoint {

    public static String getJavaScriptSrc(String host, String port, boolean ssl,boolean selfSigned, String realm, String appId, String appKey)
    {
        SAAccess saAccess =  new SAAccess(host, port, ssl,selfSigned, realm, appId, appKey);
        JSObjectResponse jsSrc = saAccess.javaScriptSrc();
        if (jsSrc != null) {
            String output = XMLUtil.convertObjectToXML(jsSrc);
            return output;
        }
        else
        {
            String output = "<apiError>javaScriptSrc returned null.</apiError>";
            return output;
        }
    }
}
