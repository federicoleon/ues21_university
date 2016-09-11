package com.ues21.utils

import java.security.MessageDigest

class StringUtils {

    private static final String MD5 = "MD5"
    
    public static String getMD5(String s) {
        return MessageDigest.getInstance(MD5).digest(s.bytes).encodeHex().toString()
    }
}