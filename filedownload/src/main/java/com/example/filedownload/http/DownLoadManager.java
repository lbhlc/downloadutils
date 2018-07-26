package com.example.filedownload.http;

import android.support.annotation.NonNull;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/27.
 */

public class DownLoadManager {
    private static final DownLoadManager sManager=new DownLoadManager();
    private final static int MAX_THREAD=2;
    private static final ThreadPoolExecutor mThreadpoolExecuteor=new ThreadPoolExecutor(MAX_THREAD, MAX_THREAD, 60, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), new ThreadFactory() {
        private AtomicInteger atomicInteger=new AtomicInteger(1);
        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread=new Thread(runnable,"down thread"+atomicInteger.getAndIncrement());
            return thread;
        }
    });

    private DownLoadManager() {
    }

    public static DownLoadManager getInstance()
    {
        return sManager;
    }
    public void download(final String url, final DownLoadCallBack callBack)
    {
        HttpManager.getInstance().asyncRequest(url, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()&&callBack!=null)
                {
                    callBack.failed(HttpManager.NETWORK_ERROR_CODE,"网络出问题了");
                    return;
                }
                long length=response.body().contentLength();
                if (length==-1)
                {
                    if (callBack==null)
                    {
                        return;
                    }
                    callBack.failed(HttpManager.CONTENT_LENGTH_ERROR,"content length -1");
                    return;
                }
                processDownload(url,length,callBack);
            }
        });
    }

    private void processDownload(String url,long length,DownLoadCallBack callBack) {
        long threadDownloadSize=length/MAX_THREAD;
        for (int i=0;i<MAX_THREAD;i++)
        {
            long startSize=i*threadDownloadSize;
            long endSzie=(i+1)*threadDownloadSize-1;//0-99,100个，但实际下载是0-49，threadDownloadSize是50，所以减1
            mThreadpoolExecuteor.execute(new DownLoadRunnable(startSize,endSzie,url,callBack));
        }

    }
}
