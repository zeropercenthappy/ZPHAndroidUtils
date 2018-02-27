package com.zeropercenthappy.utilslibrary;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author zeropercenthappy
 */

public class CacheUtils {

    public static File getCacheDir(Context context) {
        return context.getExternalCacheDir();
    }

    public static boolean clearCacheDir(Context context) {
        File cacheDir = getCacheDir(context);
        return FileUtils.deleteFile(cacheDir);
    }

    /**
     * @param context
     * @param fileName 想要保存的缓存文件名，包括后缀
     * @return
     */
    public static File createCacheFile(Context context, String fileName) {
        File dir = getCacheDir(context);
        String cacheFilePath = dir.getPath() + File.separator + fileName;
        File cacheFile = new File(cacheFilePath);
        if (cacheFile.exists()) {
            cacheFile.delete();
        }
        try {
            cacheFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheFile;
    }

    /**
     * @param context
     * @param fileExtension 想要保存的缓存文件后缀名
     * @return
     */
    public static File createFormatedCacheFile(Context context, String fileExtension) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss",
                Locale.US);
        File dir = getCacheDir(context);
        fileExtension = fileExtension.startsWith(".") ? fileExtension : ("." + fileExtension);
        String cacheFilePath = dir.getAbsolutePath() + File.separator +
                simpleDateFormat.format(System.currentTimeMillis()) + fileExtension;
        File cacheFile = new File(cacheFilePath);
        if (cacheFile.exists()) {
            cacheFile.delete();
        }
        try {
            cacheFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cacheFile;
    }
}
