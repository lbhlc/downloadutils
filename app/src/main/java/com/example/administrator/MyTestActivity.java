package com.example.administrator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.MyJpushService;
import com.example.administrator.filedownload.R;

/**
 * 测试轮询的demo
 */
public class MyTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static Handler handler = new Handler();
    private static int value = 0;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {


            if (value < 4) {
                handler.postDelayed(this, 1000);
                Log.e("LBH", "lbhlc");
                value++;
            } else {
                handler.removeCallbacks(this);
            }
        }


    };
    /**
     * 暂停
     */
    private Button mButton;
    /**
     * 开始
     */
    private Button mButtonstart;
    /**
     * 暂停
     */
    private Button mButtonend;
    Intent intent=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_test);
        initView();
       // intent=new Intent(MyTestActivity.this, MyJpushService.class);
       // handler.postDelayed(runnable,1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        value=0;
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.buttonend);
        mButton.setOnClickListener(this);
        mButtonstart = (Button) findViewById(R.id.buttonstart);
        mButtonstart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            default:
                break;
            case R.id.buttonstart:
                intent.putExtra("result", true);
                startService(intent);
                break;
            case R.id.buttonend:
                intent.putExtra("result", false);
                startService(intent);
                break;
        }
    }
}
