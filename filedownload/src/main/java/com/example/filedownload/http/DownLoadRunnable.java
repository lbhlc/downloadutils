package com.example.filedownload.http;

import com.example.filedownload.file.FileStorageManager;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/27.
 */

public class DownLoadRunnable implements Runnable{
    private long mStart;
    private long mEnd;
    private String mUrl;
    private DownLoadCallBack callBack;

    public DownLoadRunnable(long mStart, long mEnd, String mUrl, DownLoadCallBack callBack) {
        this.mStart = mStart;
        this.mEnd = mEnd;
        this.mUrl = mUrl;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        Response response=HttpManager.getInstance().syncRequestByRanage(mUrl,mStart,mEnd);
        if (response==null&&callBack!=null)
        {
            callBack.failed(HttpManager.NETWORK_ERROR_CODE,"网络出问题了");
            return;
        }
        File file= FileStorageManager.getInstance().getFileByName(mUrl);
        try {
            RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rwd");
            byte[]buffer=new byte[1024*500];
            int len;
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            InputStream instream=response.body().byteStream();
            while ((len=instream.read(buffer,0,buffer.length))!=-1)
            {
                randomAccessFile.write(buffer,0,len);

            }
            if (callBack!=null) {
                callBack.success(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
