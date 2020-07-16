package net.download;

/**
 * 下载进度接口
 * Created by wison on 2017/3/9.
 */
public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
