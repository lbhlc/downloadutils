package com.example.filedownload.utils;

import android.util.Log;

import java.util.Locale;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/4.
 */

public class Logger {
    public static final boolean DEBUG=true;
    public static void debug(String tag,String message)
    {
        if (DEBUG)
        {
            Log.d(tag,message);
            Log.e("LBH","?????");
        }
    }
    public static void debug(String tag,String message,Object...args)
    {
        if (DEBUG)
        {
            Log.d(tag,String.format(Locale.getDefault(),message,args));
        }
    }
    public static void error(String tag,String message)
    {
        if (DEBUG)
        {
            Log.e(tag,message);
        }
    }
    public static void info(String tag,String message)
    {
        if (DEBUG)
        {
            Log.i(tag,message);
        }
    }
}
