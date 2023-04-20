package com.wa.last.io.IOTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IoTest1 {
    public static void main(String[] args) {
        int b = 0;
        FileInputStream in = null;
        try {
            in = new FileInputStream("C:\\Users\\41639\\Desktop\\java\\FileText\\src\\TestFileImportStream.java");
        } catch (FileNotFoundException e) {
            System.out.println("file is not found");
            System.exit(-1);
        }
        try {
            long num = 0;
            while ((b = in.read()) != -1) {
                System.out.println((char) b);
                num++;
            }
            in.close();
            System.out.println();
            System.out.println("共读取了" + num + "个字节");
        } catch (IOException e) {
            System.out.println("IO异常，读取失败");
            System.exit(-1);
        }
    }
}
