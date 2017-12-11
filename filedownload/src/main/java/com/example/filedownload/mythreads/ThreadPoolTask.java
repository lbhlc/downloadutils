package com.example.filedownload.mythreads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/11.
 */

public class ThreadPoolTask {

    private static class MyRunnable implements Runnable
    {
        public volatile boolean flag=true;//加上关键字后使所有的flag都能立即响应flag值的变化
        @Override
        public void run() {
            while (flag&&!Thread.interrupted())
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;//如果不加return不能被立刻中断
                }
                System.out.print("running"+"\n");
            }
        }
    }
    public static void main(String args[]) throws InterruptedException {
        //final ArrayBlockingQueue<Runnable>queue=new ArrayBlockingQueue<Runnable>(10);
//        final LinkedBlockingDeque<Runnable>queue=new LinkedBlockingDeque<>();//如果换成这个linkblock，会一直向该队列中添加，因为其没有最大的size所以不会触发最大线程开启
//        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS,queue);
//        for (int i=0;i<16;i++)
//        {
//            final int index=i;
//            threadPoolExecutor.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.print("index="+index+","+"queue size="+queue.size()+"\n");
//                }
//            });
//        }
        MyRunnable runnable=new MyRunnable();
        Thread thread=new Thread(runnable);
        thread.start();
        Thread.sleep(100);
        runnable.flag=false;
        thread.interrupt();

    }
}
