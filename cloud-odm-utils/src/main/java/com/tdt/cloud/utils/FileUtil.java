package com.tdt.cloud.utils;

import jcifs.smb.SmbFile;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Mr.superbeyone
 * @project online-data-manager
 * @className FileUtil
 * @description 文件操作工具类
 * @date 2019-04-02 15:04
 **/

public class FileUtil {


    
    
    /**
     * 递归获取文件夹里的所有文件
     *
     * @param file     父文件
     * @param fileList 文件缓冲
     * @return 文件集合
     */
    public static List<File> getFileListFromPath(File file, List<File> fileList) {
        if (file.isDirectory()) {
            Arrays.stream(file.listFiles()).forEach((f) -> getFileListFromPath(f, fileList));
        } else {
            fileList.add(file);
        }
        return fileList;
    }



    /**
     * 拼接导出文件的文件名
     *
     * @param fileName 文件名
     * @param t        投影类型
     * @return 数据库文件名
     */
    public static String getExportFileName(String fileName, String t) {
        StringBuilder builder = new StringBuilder();
        String reg = "_|\\.";
        Pattern pattern = Pattern.compile(reg);
        String[] split = pattern.split(fileName);
        builder.append(split[0]).append("_")
                .append(split[1]).append("_")
                .append(t).append("_")
                .append(split[4]).append("_")
                .append(split[5]).append("_")
                .append(split[3]).append(".");
        if (split.length == 7) {
            builder.append(split[6]);
        }
        return builder.toString();
    }

    public static byte[] getContent(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            return getContent(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static byte[] getContent(InputStream inputStream) {
        byte[] bytes = new byte[]{};
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            //byte[] buffer = new byte[16 * 1024];
            while (true) {
                int len = inputStream.read(buffer);
                if (len == -1) {
                    break;
                }
                outputStream.write(buffer, 0, len);
            }
            bytes = outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytes;
    }

    /**
     * 计算父类文件夹
     *
     * @return
     */
    public static String calcParentFolder(String pac, String name, String layer, int rowNum) {
        StringBuilder builder = new StringBuilder();
        builder.append(StringUtils.isBlank(pac) ? "156000000" : pac).append("_")
                .append(StringUtils.isBlank(name) ? "中国" : name).append("_");
        if (rowNum > 10000000) {
            builder.append(rowNum);
        } else {
            builder.append(new DecimalFormat("00000000").format(rowNum));
        }
        return builder.append("_").append(layer).toString();
    }


    /**
     * 计算文件路径
     *
     * @param filename
     * @return
     */
    public static String calcFilePath(String filename) {
        String reg = "_|\\.";
        Pattern pattern = Pattern.compile(reg);
        String[] split = pattern.split(filename);
        if (split.length < 5) {
            return filename;
            
        }
        try {
            return calcFilePath(Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[3]));
        } catch (Exception e) {
            e.printStackTrace();
            return filename;
        }
    }

    public static String calcFilePath(int x, int y, int l) {
        String path = l + File.separator;
        int preRow = 0;
        int preCol = 0;
        int preSize = 0;
        int grade = getGrade(l);
        for (int i = 0; i < grade; i++) {
            int size = 1 << (5 * (grade - i)); // 左移次数
            x = x - preRow * preSize;
            y = y - preCol * preSize;

            int r = x / size;
            int c = y / size;

            path += String.format("%02d", r);
            path += String.format("%02d", c);
            path += File.separator;

            preRow = r;
            preCol = c;
            preSize = size;
        }
        return path;
    }

    private static int getGrade(int l) {
        int grade = (l - 4) / 5;
        if ((l - 4) % 5 != 0) {
            grade++;
        }
        if (l < 4) {
            grade = 0;
        }
        return grade;
    }

    public static String resetMongoDBFileNameParam(File file) {
        return resetMongoDBFileNameParam(file.getName());
    }

    /**
     * 根据实体文件的文件名重新获取Mongo数据库的文件名字段值
     *
     * @param fileName
     * @return
     */
    public static String resetMongoDBFileNameParam(String fileName) {
        String reg = "_|\\.";
        Pattern pattern = Pattern.compile(reg);
        String[] splitStr = pattern.split(fileName);
        if (splitStr.length < 6 || splitStr.length > 7) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(splitStr[0]).append("_")
                .append(splitStr[1]).append("_")
                .append("00000000").append("_")
                .append(splitStr[5]).append("_")
                .append(splitStr[3]).append("_")
                .append(splitStr[4]).append(".");
        if (splitStr.length == 7) {
            builder.append(splitStr[6]);
        }
        return builder.toString();
    }

    public static File generateNewTxtFile(File parent, String filename) {
        if (StringUtils.isBlank(filename)) {
            filename = "record_" + getLocalDateTime() + ".txt";
        } else {
            filename = filename + "_" + getLocalDateTime() + ".txt";
        }
        if (!parent.exists()) {
            parent.mkdirs();
        }
        File file = new File(parent, filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    



    private static String getLocalDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss_SSS"));
    }

    /**
     * 数据删除时不选择库时需要获得操作的库列表
     *
     * @param file
     * @param set
     * @return
     */
    @Deprecated
    public static Set<String> readDbNameFromFile(File file, Set<String> set) {
        try {
            if (!file.exists()) {
                return null;
            }
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files.length > 0) {
                    for (File f : files) {
                        readDbNameFromFile(f, set);
                    }
                }
            } else {

                if (StringUtils.endsWithIgnoreCase(file.getName(), ".png") || StringUtils.endsWithIgnoreCase(file.getName(), ".jpg")) {
                    set.add(file.getName().substring(0, file.getName().length() > 5 ? 5 : file.getName().length()));
                }
                if (StringUtils.endsWithIgnoreCase(file.getName(), ".txt")) {
                    return readDbNameFromTxt(file, set);
                }
                if (StringUtils.endsWithIgnoreCase(file.getName(), ".zip")) {
                    return readDbNameFromZip(new FileInputStream(file), set);
                }
                if (StringUtils.endsWithIgnoreCase(file.getName(), ".tar")
                        || StringUtils.endsWithIgnoreCase(file.getName(), ".gz")
                        || StringUtils.endsWithIgnoreCase(file.getName(), ".tar.gz")) {
                    return readDbNameFromTar(new FileInputStream(file), file.getName(), set);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    /**
     * 数据删除时不选择库时需要获得操作的库列表
     *
     * @param file
     * @param set
     * @return
     */
    @Deprecated
    public static Set<String> readDbNameFromSmbFile(SmbFile file, Set<String> set) {
        try {
            if (!file.exists()) {
                return null;
            }
            if (file.isDirectory()) {
                SmbFile[] files = file.listFiles();
                if (files.length > 0) {
                    for (SmbFile f : files) {
                        readDbNameFromSmbFile(f, set);
                    }
                }
            } else {

                if (StringUtils.endsWithIgnoreCase(file.getName(), ".png") || StringUtils.endsWithIgnoreCase(file.getName(), ".jpg")) {
                    set.add(file.getName().substring(0, file.getName().length() > 5 ? 5 : file.getName().length()));
                }
                if (StringUtils.endsWithIgnoreCase(file.getName(), ".txt")) {
                    return readDbNameFromTxt(file, set);
                }
                if (StringUtils.endsWithIgnoreCase(file.getName(), ".zip")) {
                    return readDbNameFromZip(file.getInputStream(), set);
                }
                if (StringUtils.endsWithIgnoreCase(file.getName(), ".tar")
                        || StringUtils.endsWithIgnoreCase(file.getName(), ".gz")
                        || StringUtils.endsWithIgnoreCase(file.getName(), ".tar.gz")) {
                    return readDbNameFromTar(file.getInputStream(), file.getName(), set);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    @Deprecated
    private static Set<String> readDbNameFromTxt(Object file, Set<String> set) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file instanceof File ? new FileInputStream((File) file) : ((SmbFile) file).getInputStream()))) {
            Stream<String> lines = reader.lines();
            lines.forEach(line ->

            {
                set.add(line.substring(0, line.length() > 5 ? 5 : line.length()));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return set;
    }


    @Deprecated
    private static Set<String> readDbNameFromZip(InputStream inputStream, Set<String> set) {
        try (ZipInputStream zin = new ZipInputStream(new BufferedInputStream(inputStream), Charset.defaultCharset())) {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    String fileName = entry.getName();
                    if (StringUtils.contains(fileName, "/")) {
                        fileName = StringUtils.substringAfterLast(fileName, "/");
                    }
                    if (StringUtils.contains(fileName, "\\")) {
                        fileName = StringUtils.substringAfterLast(fileName, "\\");
                    }
                    long size = entry.getSize();
                    if (size > 0) {
                        //图片文件
                        if (StringUtils.endsWithIgnoreCase(fileName, ".png") || StringUtils.endsWithIgnoreCase(fileName, ".jpg")) {
                            set.add(fileName.substring(0, fileName.length() > 5 ? 5 : fileName.length()));
                        } else if (StringUtils.endsWithIgnoreCase(fileName, ".txt")) {
                            //TODO: zip文件内txt操作可以改成异步形式
                            //TXT文件操作修改
                            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
                            String line;
                            while ((line = bufferedInputStream.readLine()) != null) {
                                set.add(line.substring(0, line.length() > 5 ? 5 : line.length()));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return set;
    }

    @Deprecated
    private static Set<String> readDbNameFromTar(InputStream inputStream, String tarName, Set<String> set) {
        ArchiveInputStream archiveInputStream = null;
        try {

            if (StringUtils.endsWithIgnoreCase(tarName, ".gz")) {
                archiveInputStream = new ArchiveStreamFactory()
                        .createArchiveInputStream("tar", new GZIPInputStream(new BufferedInputStream(inputStream)));
            } else {
                archiveInputStream = new ArchiveStreamFactory()
                        .createArchiveInputStream("tar", new BufferedInputStream(inputStream));
            }
            TarArchiveEntry entry = null;
            while ((entry = (TarArchiveEntry) archiveInputStream.getNextEntry()) != null) {

                if (entry.getSize() > 0) {
                    String fileName = entry.getName();
                    if (StringUtils.contains(fileName, "/")) {
                        fileName = StringUtils.substringAfterLast(fileName, "/");
                    }
                    if (StringUtils.contains(fileName, "\\")) {
                        fileName = StringUtils.substringAfterLast(fileName, "\\");
                    }


                    //图片文件
                    if (StringUtils.endsWithIgnoreCase(fileName, ".png") || StringUtils.endsWithIgnoreCase(fileName, ".jpg")) {
                        set.add(fileName.substring(0, fileName.length() > 5 ? 5 : fileName.length()));
                    } else if (StringUtils.endsWithIgnoreCase(fileName, ".txt")) {
                        //TODO: Tar文件内txt操作可以改成异步形式
                        //TXT文件操作修改
                        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(archiveInputStream));
                        String line;
                        while ((line = bufferedInputStream.readLine()) != null) {
                            set.add(line.substring(0, line.length() > 5 ? 5 : line.length()));
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            if (archiveInputStream != null) {
                try {
                    archiveInputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return set;
    }

    public static boolean checkFileWriterStreamIsClosed(BufferedOutputStream bufferedWriter) {
        try {
            bufferedWriter.write("".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    @Deprecated
    public static SmbFile getSmbConn(String username, String password, String ip) {

        try {

            if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
                return new SmbFile("smb://" + ip + "/");
            } else {
                return new SmbFile("smb://" + username + ":" + password + "@" + ip + "/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static SmbFile getSmbConn(String url) {

        try {

            if (StringUtils.isNotBlank(url)) {
                return new SmbFile(url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    ///////////////////////////////////////////////// PBF  /////////////////////////////////////////////////////////


    public static String getPbfFileName(String path) {
        String[] split = StringUtils.split(path, "/");
        if (split.length == 3) {
            return split[0] + "_" + split[1] + "_" + StringUtils.substringBefore(split[2], ".");
        }
        return "";
    }

    public static String getPbfCollectionName(String prefix, String fileName) {
        String l = StringUtils.substringBefore(fileName, "_");
        int level = Integer.parseInt(l);
        return getPbfCollectionName(prefix, level);
    }


    public static String getPbfFileName(File file) {
        String fileName = file.getName();
        String y = StringUtils.substringBefore(fileName, ".");
        String x = file.getParentFile().getName();
        String l = file.getParentFile().getParentFile().getName();

        return l + "_" + x + "_" + y;
    }

    public static String getPbfCollectionName(String prefix, File file) {
        String l = file.getParentFile().getParentFile().getName();
        int level = Integer.parseInt(l);
        return getPbfCollectionName(prefix, level);
    }

    public static String getPbfCollectionName(String prefix, int level) {

        if (level <= 10) {
            return prefix + "_1_10";
        } else if (level <= 15) {
            return prefix + "_11_15";
        } else if (level == 16) {
            return prefix + "_16";
        } else if (level == 17) {
            return prefix + "_17";
        } else if (level == 18) {
            return prefix + "_18";
        }
        return prefix + "_" + level;
    }
}
