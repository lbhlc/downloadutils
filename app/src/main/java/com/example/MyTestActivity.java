package com.example;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.filedownload.R;

public class MyTestActivity extends AppCompatActivity {

    private static android.os.Handler handler=new android.os.Handler()
    {

    };
    private static int value=0;
    private  Runnable runnable=new Runnable() {
        @Override
        public void run() {
            Log.e("LBH","lbhlc");

            if (value<4) {
                handler.postDelayed(this, 1000);
                value++;
            }else {
                handler.removeCallbacks(this);
            }
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
        handler.postDelayed(runnable,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
