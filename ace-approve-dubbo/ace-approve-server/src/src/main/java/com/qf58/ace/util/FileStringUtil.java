package com.qf58.ace.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA. Description: User: weicaijia Date: 2018/12/5 14:08 Time: 14:15
 */
public class FileStringUtil {


    /**
     * 文件内容读成String
     *
     * @param path 文件路径
     * @return 字符
     */
    public String readFile(String path) {
        String result = "";
        InputStream input = this.getClass().getResourceAsStream(path);
        try {
            byte[] buf = new byte[input.available()];
            int len = input.read(buf);
            result = new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }





}
