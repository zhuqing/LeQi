/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leqi.io;

import java.io.*;

public class Write {

    public static void write(File file, String content) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(content);
        writer.close();
    }

    public static void write(String path, String content) {
        String s = new String();
        String s1 = new String();

        try {
            File f = new File(path);
            if (f.exists()) {
                System.err.println("文件存在!");
            } else {
                System.err.println("文件不存在，正在创建...!");
                if (f.createNewFile()) {
                    System.err.println("文件创建成功！");
                } else {
                    System.err.println("文件创建失败！");
                }
            }
            s1 += content;
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            BufferedWriter writer = new BufferedWriter(write);
            writer.write(s1);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
