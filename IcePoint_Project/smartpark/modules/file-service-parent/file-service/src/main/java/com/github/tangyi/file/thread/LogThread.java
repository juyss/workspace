package com.github.tangyi.file.thread;



import com.github.tangyi.file.util.DownloadMain;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 多线程下载日志记录
 *
 * @author ck
 *
 */
public class LogThread implements Callable<Boolean> {

    public static AtomicLong LOCAL_FINISH_SIZE = new AtomicLong();
    public static AtomicLong DOWNLOAD_SIZE = new AtomicLong();
    public static AtomicLong DOWNLOAD_FINISH_THREAD = new AtomicLong();
    private long httpFileContentLength;

    public LogThread(long httpFileContentLength) {
        this.httpFileContentLength = httpFileContentLength;
    }

    @Override
    public Boolean call() throws Exception {
        return true;
    }
}