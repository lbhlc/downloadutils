package com.example.administrator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.filedownload.R;
import com.example.filedownload.mythreads.PauseThread;

public class TestThreadActivity extends AppCompatActivity implements View.OnClickListener,PauseThread.TaskToDo{

    /**
     * 开始
     */
    private Button mStart;
    /**
     * 暂停
     */
    private Button mStop;
    private PauseThread pauseThread;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_thread);
        initView();


    }

    private void initView() {
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mStop = (Button) findViewById(R.id.stop);
        mStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.start:
                if (pauseThread==null) {
                    pauseThread=new PauseThread(this);
                    pauseThread.start();
                }else
                {
                    pauseThread.resumeThread();
                }
                break;
            case R.id.stop:
                pauseThread.pauseThread();
                break;
        }
    }

    @Override
    public void toDo() {
        Log.e("LBH","send="+i);
        i++;

    }
}
