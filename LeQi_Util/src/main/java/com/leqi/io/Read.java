/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqi.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Read {

    public static String read(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        String content = inputStream2String(in);
        in.close();
        return content;
    }

    public static String read(URL file) {
        String s = null;
        InputStream in = null;
        try {
            in = file.openStream();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (in == null) {
            System.err.println("文件不存在!");
        } else {
            try {
                return inputStream2String(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 读取FXMl内容
     *
     * @param path
     * @return
     */
    public static String readString(String path) {
        String text = null;
        InputStream is = Read.class.getResourceAsStream(path);
        try {
            text = inputStream2String(is);
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
        return text;
    }

    public static String inputStream2String(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n = 0; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }
}
