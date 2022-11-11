package com.li.wecom;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private static String sToken = "Mnh3b6CpHVZYw7o";
    private static String sCorpID = "wwe28519ca3b7ac480";
    private static String sEncodingAESKey = "nxbOem4DeXj6UkchAWOoE2eFIpoPpyg6fM6hDDlyGkN";

    @RequestMapping(value = "/")
    public String sing(@RequestParam("msg_signature") String msgSignature, @RequestParam("echostr") String echostr
            , @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce) throws AesException {

        System.out.println(msgSignature);
        System.out.println(echostr);
        System.out.println(timestamp);
        System.out.println(nonce);
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);

        System.out.println(wxcpt);

        return wxcpt.VerifyURL(msgSignature, timestamp, nonce, echostr);
    }
}
