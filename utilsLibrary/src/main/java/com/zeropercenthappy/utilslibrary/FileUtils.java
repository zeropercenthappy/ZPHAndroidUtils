package com.zeropercenthappy.utilslibrary;

import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ybq
 */

public class FileUtils {
    /**
     * io流缓存大小
     */
    private static final int BUFFER_SIZE = 8192;

    /**
     * 关闭io流
     *
     * @param closeables
     */
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

    /**
     * 检查文件是否存在，若不存在则新建文件
     *
     * @param file
     * @return
     */
    public static boolean checkFileAndCreate(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 检查文件是否存在，若不存在则新建文件
     *
     * @param path
     * @return
     */
    public static boolean checkFileAndCreate(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        File file = new File(path);
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 通过InputStream写入文件
     */
    public static boolean writeFileByIS(File storageFile, InputStream inputStream, boolean append) {
        if (!checkFileAndCreate(storageFile) || inputStream == null) {
            return false;
        }
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(storageFile, append));
            byte[] data = new byte[BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                outputStream.write(data, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeIO(inputStream, outputStream);
        }
    }

    /**
     * 向文件写入content
     *
     * @param filename 目标文件
     * @param content  内容
     * @param append   是否追加
     * @return
     */
    public static boolean writeContent2File(String filename, String content, boolean append) {
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

    /**
     * 获取文件的mimeType
     *
     * @param file
     * @return
     */
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

    /**
     * 获取文件的mimeType
     *
     * @param path
     * @return
     */
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

    /**
     * 删除文件或目录
     *
     * @param file
     * @return
     */
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

    /**
     * 获取文件或目录的大小，单位：Byte
     *
     * @param file
     * @return
     */
    public static long getSize(File file) {
        long size = 0;

        if (file == null || !file.exists()) {
            return size;
        }
        if (file.isFile()) {
            return file.length();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                size += getSize(subFile);
            }
        }

        return size;
    }

    /**
     * 获取文件或目录的大小，单位：Byte
     *
     * @param path
     * @return
     */
    public static long getSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return 0;
        }
        File file = new File(path);
        long size = 0;

        if (!file.exists()) {
            return size;
        }
        if (file.isFile()) {
            return file.length();
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                size += getSize(subFile);
            }
        }

        return size;
    }

    /**
     * 复制文件
     *
     * @param sourceFile  源文件
     * @param tartgetFile 目标文件
     * @param replace     是否替换
     * @return
     */
    public static boolean copyFile(File sourceFile, File tartgetFile, boolean replace) {
        if (sourceFile == null || !sourceFile.exists()) {
            return false;
        }
        if (replace && tartgetFile != null && tartgetFile.exists()) {
            if (!deleteFile(tartgetFile)) {
                //若目标文件删除失败
                return false;
            }
        }
        if (!checkFileAndCreate(tartgetFile)) {
            //若目标文件创建失败
            return false;
        }

        InputStream inputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            bufferedOutputStream =
                    new BufferedOutputStream(new FileOutputStream(tartgetFile, false));
            byte data[] = new byte[BUFFER_SIZE];
            int lengh;
            while ((lengh = inputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                bufferedOutputStream.write(data, 0, lengh);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (inputStream != null) {
                closeIO(inputStream);
            }
            if (bufferedOutputStream != null) {
                closeIO(bufferedOutputStream);
            }
        }
    }
}
