package com.tdt.cloud.utils;
/**
 * 2010-4-20
 */

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarInputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件解压缩工具类
 * @author superbeyone
 */
public class FileUnpackUtil {

    /**
     * 解压缩tar以及tar.gz文件
     *
     * @param file tar以及tar.gz文件地址
     */
    public static void unpackTarGz(File file) {
        unpackTarGz(file, file.getParentFile());
    }

    /**
     * 解压缩tar以及tar.gz文件
     *
     * @param srcFile  tar以及tar.gz文件地址
     * @param destFile 目标文件保存地址
     */
    public static void unpackTarGz(File srcFile, File destFile) {
        TarInputStream tarIn = null;
        OutputStream out = null;
        try {
            tarIn = new TarInputStream(new GZIPInputStream(new BufferedInputStream(new FileInputStream(srcFile))), 1024 * 2);
            createDirectory(destFile, null);//创建输出目录
            TarEntry entry = null;
            while ((entry = tarIn.getNextEntry()) != null) {
                if (entry.isDirectory()) {//是目录
                    entry.getName();
                    createDirectory(destFile, entry.getName());//创建空目录
                } else {//是文件
                    File tmpFile = new File(destFile, entry.getName());
                    //创建输出目录
                    createDirectory(tmpFile.getParentFile(), null);

                    out = new FileOutputStream(tmpFile);
                    int length = 0;
                    byte[] b = new byte[2048];
                    while ((length = tarIn.read(b)) != -1) {
                        out.write(b, 0, length);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (tarIn != null) {
                    tarIn.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 解压缩zip文件
     *
     * @param file    zip文件地址
     * @param charset 字符编码 window系统中文适用GB2312能解决乱码
     */
    public static void unpackZip(File file, String charset) {
        unpackZip(file, file.getParentFile(), charset);
    }

    /**
     * 解压缩zip文件
     *
     * @param srcFile  zip文件地址
     * @param destFile 解压缩后存放地址
     * @param charset  字符编码 window系统中文适用GB2312能解决乱码
     */
    public static void unpackZip(File srcFile, File destFile, String charset) {
        if (destFile == null) {
            destFile = new File(srcFile.getParentFile(), srcFile.getName().substring(0, srcFile.getName().lastIndexOf(".")));
        }
        ZipFile zipFile = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            Charset decoding = Charset.forName(charset);
            zipFile = new ZipFile(srcFile, decoding);
            createDirectory(destFile);
            Enumeration<?> enums = zipFile.entries();
            while (enums.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) enums.nextElement();
                if (entry.isDirectory()) {//是目录
                    createDirectory(destFile, entry.getName());//创建空目录
                } else {
                    //是文件
                    File tmpFile = new File(destFile, entry.getName());
                    if (!tmpFile.getParentFile().exists()) {
                        tmpFile.getParentFile().mkdirs();
                    }
                    in = zipFile.getInputStream(entry);
                    out = new BufferedOutputStream(new FileOutputStream(tmpFile));
                    int len = 0;
                    byte[] bytes = new byte[2048];
                    while ((len = in.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void unpackZip(File zipFile, File descDir) {
        try (ZipArchiveInputStream inputStream = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(zipFile)), "GBK")) {
            if (!descDir.exists()) {
                descDir.mkdirs();
            }
            ZipArchiveEntry entry = null;
            while ((entry = inputStream.getNextZipEntry()) != null) {

                if (entry.isDirectory()) {
                    File directory = new File(descDir, entry.getName());
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }

                } else {
                    OutputStream os = null;
                    try {
                        File file = new File(descDir, entry.getName());
                        File parentFile = file.getParentFile();
                        if (!parentFile.exists()) {
                            parentFile.mkdirs();
                        }
                        os = new BufferedOutputStream(new FileOutputStream(file));
                        //输出文件路径信息
                        System.out.println("解压文件的当前路径为:" + descDir + File.separator + entry.getName());
                        IOUtils.copy(inputStream, os);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createDirectory(File file) {
        createDirectory(file, null);
    }

    private static void createDirectory(File file, String subDir) {
        if (!(subDir == null || subDir.trim().equals(""))) {//子目录不为空  
            file = new File(file, subDir);
        }
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.mkdirs();
        }
    }
    

}