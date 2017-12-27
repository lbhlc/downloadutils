package com.example.filedownload;

import com.example.filedownload.http.DownLoadCallBack;

/**
 * @author libohan
 *         邮箱:76681287@qq.com
 *         create on 2017/12/27.
 */

public class DownloadTask {
    private String url;
    private DownLoadCallBack callBack;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DownLoadCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(DownLoadCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DownloadTask that = (DownloadTask) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        return callBack != null ? callBack.equals(that.callBack) : that.callBack == null;
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (callBack != null ? callBack.hashCode() : 0);
        return result;
    }

    public DownloadTask(String url, DownLoadCallBack callBack) {
        this.url = url;
        this.callBack = callBack;
    }
}
