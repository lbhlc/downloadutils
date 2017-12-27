package com.example.administrator.filedownload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.filedownload.file.FileStorageManager;
import com.example.filedownload.http.DownLoadCallBack;
import com.example.filedownload.http.HttpManager;
import com.example.filedownload.utils.Logger;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPersiossion();
        File file = FileStorageManager.getInstance().getFileByName("http://www.hellokitty.com");
        Logger.debug("LBH", "file.path=" + file.getAbsolutePath());
        HttpManager.getInstance().asyncRequest("https://img.alicdn.com/tfs/TB1TE6vdaagSKJjy0FhXXcrbFXa-966-644.jpg_490x490q100.jpg_.webp", new DownLoadCallBack() {
            @Override
            public void success(File file) {
                Log.e("LBH", "file.path=" + file.getAbsolutePath());
                final Bitmap bitmap= BitmapFactory.decodeFile(file.getAbsolutePath());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageview.setImageBitmap(bitmap);
                    }
                });
            }

            @Override
            public void failed(int errorcode, String errorMessage) {
                Log.e("LBH", "报错了");
            }

            @Override
            public void progress(int progress) {

            }
        });
    }

    /**
     * android 6.0动态权限获取模块
     */
    private void checkPersiossion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.e("LBH", "大哥没有权限");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    private void initView() {
        mImageview = (ImageView) findViewById(R.id.image);
    }
}
