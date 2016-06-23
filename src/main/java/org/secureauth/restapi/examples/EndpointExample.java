package org.secureauth.restapi.examples;

import org.secureauth.restapi.endpoints.*;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;

/**
 * Created by jhickman on 4/27/16.
 */
public class EndpointExample {

    private static String applianceHost = "host.company.com";
    private static String appliancePort = "443";
    private static boolean applianceSSL = true;
    private static boolean selfSigned = true;
    private static String realm = "secureauth1";
    private static String applicationID = "...";
    private static String applicationKey = "...";
    private static String userId = "user1";
    private static String host_addr = "172.0.0.1";
    private static String fingerprintJson = "{\"fingerprint\":{\"fonts\":\"American Typewriter,Andale Mono,Apple Chancery,Apple Color Emoji,Apple SD Gothic Neo,Arial,Arial Black,Arial Hebrew,Arial Narrow,Arial Rounded MT Bold,Arial Unicode MS,AVENIR,Ayuthaya,Bangla Sangam MN,Baskerville,Bauhaus 93,Big Caslon,Bodoni 72,Bodoni 72 Oldstyle,Bodoni 72 Smallcaps,Bookshelf Symbol 7,Bradley Hand,Brush Script MT,Chalkboard,Chalkboard SE,Chalkduster,Cochin,Comic Sans MS,Copperplate,Courier,Courier New,Devanagari Sangam MN,Didot,English 111 Vivace BT,Euphemia UCAS,Futura,Geeza Pro,Geneva,Georgia,GeoSlab 703 Lt BT,GeoSlab 703 XBd BT,Gill Sans,Gujarati Sangam MN,Gurmukhi MN,Heiti SC,Heiti TC,Helvetica,Helvetica Neue,Hiragino Kaku Gothic ProN,Hiragino Mincho ProN,Hoefler Text,Humanst 521 Cn BT,Impact,Kailasa,Kannada Sangam MN,Krungthep,LUCIDA GRANDE,Malayalam Sangam MN,Marion,Marker Felt,Microsoft Sans Serif,Modern No. 20,Monaco,Nadeem,Noteworthy,OPTIMA,Oriya Sangam MN,OSAKA,Palatino,Papyrus,Plantagenet Cherokee,Savoye LET,Sinhala Sangam MN,Skia,Snell Roundhand,Tahoma,Tamil Sangam MN,Telugu Sangam MN,Thonburi,Times,Times New Roman,Trebuchet MS,Univers CE 55 Medium,Verdana,Wingdings,Wingdings 2,Wingdings 3,Zapfino\",\"plugins\":\"Widevine Content Decryption Module:Enables Widevine licenses for playback of HTML audio/video content. (version: 1.4.8.866),Shockwave Flash:Shockwave Flash 21.0 r0,Chrome PDF Viewer:,Native Client:,Chrome PDF Viewer:Portable Document Format\",\"timezone\":\"America/Los_Angeles\",\"video\":\"1920x1080x24\",\"local_storage\":\"true\",\"session_storage\":\"true\",\"cookie_enabled\":true,\"user_agent\":\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36\"}}";
    private static String accept = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    private static String accept_encode = "gzip, deflate, sdch";
    private static String accept_lang = "en-US,en;q=0.8";
    private static String accept_charset = "";

    public static void main(String[] args){

        System.out.println("++++++++++++++++++Start Test++++++++++++++++++");

        //usersEndpoint get XML test.
        String users = usersEndpoint.getMFAOptions(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId);
        System.out.println("++++++++++++Start UserEndpoint Test++++++++++++");
        System.out.println(users);
        System.out.println("++++++++++++End UserEndpoint Test++++++++++++");

        //Hardcoded FactorIds, we could grab them from the XML, but for testing it is easy to change.
        String emailFactorId = "Email1";
        String smsFactorId = "Phone1";
        String voiceFactorId = "Phone1";

        //dfpJSendpoint get XML test.
        String dfpJs = dfpJsEndpoint.getJavaScriptSrc(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey);
        System.out.println("++++++++++++Start DFPJSEndpoint Test++++++++++++");
        System.out.println(dfpJs);
        System.out.println("++++++++++++End DFPJSEndpoint Test++++++++++++");

        //dfpValidateEndpoint get XML test.
        String dfpValidate = dfpValidateEndpoint.validateDfp(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, host_addr, fingerprintJson, accept, accept_charset, accept_encode, accept_lang);
        System.out.println("++++++++++++Start DFPValidate Test++++++++++++");
        System.out.println(dfpValidate);
        System.out.println("++++++++++++End DFPValidate Test++++++++++++");

        //get fingerprint id from validate to use for confirm
        String fp_id = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource input = new InputSource();
            input.setCharacterStream(new StringReader(dfpValidate));

            Document doc = builder.parse(input);

            NodeList nodes = doc.getElementsByTagName("fingerprint_id");
            if (nodes != null) {
                Element element = (Element) nodes.item(0);
                fp_id = getCharacterDataFromElement(element);
            }
        }
        catch (Exception e) {
            fp_id = "";
            e.printStackTrace();
        }


        //dfpConfirmEndpoint get XML test.
        String dfpConfirm = dfpConfirmEndpoint.confirmDfp(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, fp_id);
        System.out.println("++++++++++++Start DFPConfirm Test++++++++++++");
        System.out.println(dfpConfirm);
        System.out.println("++++++++++++End DFPConfirm Test++++++++++++");

        //adaptAuthEndpoint get XML test.
        String adaptAuth = adaptAuthEndpoint.getAdaptEndpoint(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, host_addr);
        System.out.println("++++++++++++Start AdaptiveAuth Test++++++++++++");
        System.out.println(adaptAuth);
        System.out.println("++++++++++++End AdaptiveAuth Test++++++++++++");


        //accessHistoryEndpoint get XML test.
        String accessHistory = accessHistoryEndpoint.getAccessHistoryEndpoint(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, host_addr);
        System.out.println("++++++++++++Start AccessHistory Test++++++++++++");
        System.out.println(accessHistory);
        System.out.println("++++++++++++End AccessHistory Test++++++++++++");

        //emailAuthEndpoint get XML test.
        String emailAuth = emailAuthEndpoint.sendOtpByEmail(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, emailFactorId);
        System.out.println("++++++++++++Start Deliver OTP By Email Test++++++++++++");
        System.out.println(emailAuth);
        System.out.println("++++++++++++End Deliver OTP By Email Test++++++++++++");

        //voiceAuthEndpoint get XML test.
        String voiceAuth = voiceAuthEndpoint.sendOtpByVoice(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, voiceFactorId);
        System.out.println("++++++++++++Start Deliver OTP By Voice Test++++++++++++");
        System.out.println(voiceAuth);
        System.out.println("++++++++++++End Deliver OTP By Voice Test++++++++++++");

        //smsAuthEndpoint get XML test.
        String smsAuth = smsAuthEndpoint.sendOtpBySMS(applianceHost, appliancePort, applianceSSL,selfSigned, realm, applicationID, applicationKey, userId, smsFactorId);
        System.out.println("++++++++++++Start Deliver OTP By SMS Test++++++++++++");
        System.out.println(smsAuth);
        System.out.println("++++++++++++End Deliver OTP By SMS Test++++++++++++");

        System.out.println("++++++++++++++++++End Test++++++++++++++++++");
    }

    private static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }
}
