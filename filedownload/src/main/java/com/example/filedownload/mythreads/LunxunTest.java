package com.example.filedownload.mythreads;

import android.os.Message;
import android.util.Log;

import java.util.logging.Handler;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/13.
 */

public class LunxunTest {

    private static android.os.Handler handler=new android.os.Handler()
    {
        @Override
        public void handleMessage(Message msg) {
          switch (msg.what)
          {
              case 1:
                  break;
              case 2:
                  break;
              case 3:
                  break;
              case 4:
                  break;
              default:
                  break;
          }
        }
    };
    private static int value=0;
    private static Runnable runnable=new Runnable() {
        @Override
        public void run() {
            System.out.print("lbh");

            if (value<5) {
                handler.postDelayed(this, 1000);
                value++;
            }else {
                handler.removeCallbacks(this);
            }
        }


    };
    public static void main(String args[])
    {
        handler.postDelayed(runnable,1000);
    }
}
