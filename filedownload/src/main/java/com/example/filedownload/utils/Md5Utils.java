package com.example.filedownload.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/4.
 */

public class Md5Utils {
    public static void main(String args[])
    {
        System.out.println(generateCode("http://www.imooc.com"));
    }
    public static String generateCode(String url)
    {
//        if (TextUtils.isEmpty(url))
//        {
//            return null;
//        }
        StringBuffer buffer=new StringBuffer();
        try {
            MessageDigest digest=MessageDigest.getInstance("md5");
            digest.update(url.getBytes());
            byte[] cipher=digest.digest();

            for (byte b : cipher) {
                String hexStr=Integer.toHexString(b&0xff);//将byte转换一个16进制的字符串
                buffer.append(hexStr.length()==1?"0"+hexStr:hexStr);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
}
