package com.zeropercenthappy.utilslibrary;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author ybq
 */

public class CacheUtils {

    public static File getCacheDir(Context context) {
        File dir = context.getExternalCacheDir();
        if (dir == null) {
            dir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "/" + context.getPackageName());
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static boolean clearCacheDir(Context context) {
        File cacheDir = getCacheDir(context);
        return FileUtils.deleteFile(cacheDir);
    }

    public static File getImageCacheFile(Context context) {
        StringBuilder path = new StringBuilder();
        String cacheDirPath = getCacheDir(context).getAbsolutePath();
        path.append(cacheDirPath);
        path.append("/image/");
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.US);
        path.append(simpleDateFormat.format(System.currentTimeMillis()));
        path.append(".jpg");
        File file = new File(path.toString());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getAudioCacheFile(Context context) {
        StringBuilder path = new StringBuilder();
        String cacheDirPath = getCacheDir(context).getAbsolutePath();
        path.append(cacheDirPath);
        path.append("/audio/");
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.US);
        path.append(simpleDateFormat.format(System.currentTimeMillis()));
        path.append(".mp3");
        File file = new File(path.toString());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getVideoCacheFile(Context context) {
        StringBuilder path = new StringBuilder();
        String cacheDirPath = getCacheDir(context).getAbsolutePath();
        path.append(cacheDirPath);
        path.append("/video/");
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.US);
        path.append(simpleDateFormat.format(System.currentTimeMillis()));
        path.append(".mp4");
        File file = new File(path.toString());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File getLogCacheFile(Context context) {
        StringBuilder path = new StringBuilder();
        String cacheDirPath = getCacheDir(context).getAbsolutePath();
        path.append(cacheDirPath);
        path.append("/log/");
        File dir = new File(path.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.US);
        path.append(simpleDateFormat.format(System.currentTimeMillis()));
        path.append(".log");
        File file = new File(path.toString());
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
