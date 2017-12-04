package com.example.filedownload.file;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.filedownload.utils.Md5Utils;

import java.io.File;
import java.io.IOException;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/4.
 */

/**
 * 文件管理类
 */
public class FileStorageManager {
    private static final  FileStorageManager manager=new FileStorageManager();
    private Context mContext;
    public static FileStorageManager getInstance()
    {
        return manager;
    }
    private FileStorageManager()
    {

    }
    public void  init(Context context)
    {
        this.mContext=context;
    }
    public File getFileByName(String url)
    {
        File parent;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
            parent=mContext.getExternalCacheDir();
        }else
        {
            parent=mContext.getCacheDir();
        }
        String fileNanme= Md5Utils.generateCode(url);
        File file=new File(parent,fileNanme);
        if (!file.exists())
        {
            Log.e("LBH","进来了");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
