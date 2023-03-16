package com.wa.last.io.IOTest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamTest {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("/Users/wenchang/Desktop/a.txt");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            bufferedInputStream.read(bytes);
            System.out.println(new String(bytes,0, available));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
