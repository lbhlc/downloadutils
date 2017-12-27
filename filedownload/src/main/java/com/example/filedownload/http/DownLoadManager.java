package com.example.filedownload.http;

import android.support.annotation.NonNull;

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
    public static DownLoadManager getInstance()
    {
        return sManager;
    }
    public void download(String url,DownLoadCallBack callBack)
    {
       // HttpManager.getInstance().asyncRequest(url,new call);
    }
}
