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

    public static boolean deleteDir(File dir) {
        try {
            if (dir != null && dir.isDirectory()) {
                String[] children = dir.list();
                //递归删除目录中的子目录下
                for (String aChildren : children) {
                    boolean success = deleteDir(new File(dir, aChildren));
                    if (!success) {
                        return false;
                    }
                }
            } else {
                return false;
            }
            // 目录此时为空，可以删除
            return dir.delete();
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }
}
