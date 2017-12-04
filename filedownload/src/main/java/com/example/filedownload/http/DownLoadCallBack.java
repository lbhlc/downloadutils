package com.example.filedownload.http;

import java.io.File;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/4.
 */
/**
 *
 *下载回调类
 */
public interface DownLoadCallBack {

    void success(File file);
    void failed(int errorcode,String errorMessage);
    void progress(int progress);
}
