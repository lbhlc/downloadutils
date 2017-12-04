package com.example.filedownload.http;

import android.content.Context;

import com.example.filedownload.file.FileStorageManager;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/4.
 */

public class HttpManager {
    private static final HttpManager manager=new HttpManager();
    private Context mContext;
    private OkHttpClient mClient;
    private int NETWORK_CODE=1;

    public static HttpManager getInstance()
    {
        return manager;
    }
    private HttpManager()
    {
        mClient=new OkHttpClient();
    }
    public void init(Context context)
    {
        this.mContext=context;
    }

    /**
     *同步请求
     */
    public Response syncRequest(String url)
    {
        Request request=new Request.Builder().url(url).build();
        try {
            return mClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 异步的请求
     * @param url 链接地址
     */
    public void asyncRequest(final String url, final DownLoadCallBack callBack)
    {
        Request request=new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
               if (!response.isSuccessful()&&callBack!=null)
               {
                   callBack.failed(NETWORK_CODE,"请求失败");
               }
                File file= FileStorageManager.getInstance().getFileByName(url);
               byte[]buffer=new byte[1024*500];
               int len;
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                InputStream instream=response.body().byteStream();
                while ((len=instream.read(buffer,0,buffer.length))!=-1)
                {
                    fileOutputStream.write(buffer,0,len);
                    fileOutputStream.flush();
                }
                callBack.success(file);
            }
        });
    }
}
