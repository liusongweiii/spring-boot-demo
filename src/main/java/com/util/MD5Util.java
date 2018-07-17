package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Administrator
 */
public class MD5Util {

    public static String md5(String plainText) throws NoSuchAlgorithmException {
        String str;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(plainText.getBytes());
        byte b[] = md.digest();

        int i;

        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        str = buf.toString();
        return str;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.print(md5("admin"));
    }

}
