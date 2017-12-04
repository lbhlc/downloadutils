package com.example.administrator.filedownload;

import android.app.Application;

import com.example.filedownload.file.FileStorageManager;
import com.example.filedownload.http.HttpManager;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/4.
 */

public class Myapplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FileStorageManager.getInstance().init(this);
        HttpManager.getInstance().init(this);
    }
}
