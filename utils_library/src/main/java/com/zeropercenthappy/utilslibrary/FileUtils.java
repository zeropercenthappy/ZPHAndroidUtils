package com.zeropercenthappy.utilslibrary;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author ybq
 */

public class FileUtils {
    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean writeFile(String filename, String content, boolean append) {
        boolean isSuccess = false;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename, append));
            bufferedWriter.write(content);
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(bufferedWriter);
        }
        return isSuccess;
    }

    public static String getFileMimeType(File file) {
        String path = file.getAbsolutePath();

        if (TextUtils.isEmpty(path)) {
            return null;
        }

        int dotPosition = path.lastIndexOf(".");
        if (dotPosition == -1) {
            return null;
        }
        String extension = path.substring(dotPosition + 1);
        extension = extension.toLowerCase();
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    public static String getFileMimeType(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        int dotPosition = path.lastIndexOf(".");
        if (dotPosition == -1) {
            return null;
        }
        String extension = path.substring(dotPosition + 1);
        extension = extension.toLowerCase();
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
    }

    public static boolean deleteFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }

        if (file.isFile()) {
            return file.delete();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                deleteFile(subFile);
            }
            return file.delete();
        } else {
            return false;
        }
    }

    public static long getFileSize(String filepath) {
        if (TextUtils.isEmpty(filepath)) {
            return 0;
        }
        File file = new File(filepath);
        return (file.exists() && file.isFile() ? file.length() : 0);
    }

    public static long getFileSize(File file) {
        if (file == null) {
            return 0;
        }
        return (file.exists() && file.isFile() ? file.length() : 0);
    }
}
