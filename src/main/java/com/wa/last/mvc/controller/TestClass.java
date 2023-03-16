package com.wa.last.mvc.controller;

import com.wa.last.utils.JwtTokenUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TestClass {

    public static void main(String[] args) {

        Map<String, Object> param = new HashMap<>();
        param.put("iuid", "27a1aa5c-3b2e-b486-babb-0959d6f82cb3");
        param.put("a", "27a1aa5c-");
        param.put("b", "27a1aa5c-3b2e-");
        param.put("c", "27a1aa5c-3b2e-b486-babb-0959d6f82cb3");
        param.put("d", "27a1aa5c-");
        param.put("e", "27a1aa5c-");
        param.put("f", "27a1aa5c-");
        param.put("g", "27a1aa5c-");
        param.put("h", "27a1aa5c-");
        param.put("i", "27a1aa5c-");
        param.put("j", "27a1aa5c-");
        param.put("k", "27a1aa5c-");

        String s = JwtTokenUtil.generateToken(param,"张三");
//        JwtTokenUtil.parseToken("Xxt67lSayt5hkI7MnL7KSG-C8Jw97ZuZxS1MMGZ8hpM8RTCpd2D1JT2_wF-YxUrgCyUSd4dP2DGBFnXBLf2z0bL36w5u0GDg0Az1MZvOFbmvhLPDlOX-zMd_5gUpaI7lKRDINQlHWKBFsSvQfA8kWofC1GOF_ZeNyDD_7GvLmvrwwnq8kzDZmVPK8exnvgF8g12xbhQ53dLEXCQsnX9FnuObeeQTzVYbZVB0b5urQNfgQY4a414JEgGLsw1VDzRUu1y0isVsYEdVo3nekCRlhBu23xxcfnEdykmjndWWkqaKcsI-Z4ldIuRrBNFBDWBQamBp4SYnr6ZoDlRawqgp7lMNZk3kn-jOjCawqg-9fiAoKTFQxtIH5cea7LMOYk2FWEuw6GxGgKQNfbCCA3pxDGvA6cZGj3pA99TPt6Ppf6drN8IF_fRTlBCQ3TZyqDt1T5iv_jTc97P_79uU5w6-LZVKiDgtVL4CjIMdWHvOArp6RiiHyoqmKsF3SzXUQFKm0uUQlVP0GMsxOsIu1w-hFvlReHR2U4SC6J1qqyGLsgqctJNcgui1MhfX2ldS-4_Ay9QGvQMnGpbX3NoKPEpxoR_vJXQKJP8D7MGVSPiB7K61D9BjGbaukRxr_m4MxtYQJFpxIBRJVT0SsA9GjsLYu48bZ4Ks7WwDDTMGtlI3Xlk");
        System.out.println(s);
    }
}
