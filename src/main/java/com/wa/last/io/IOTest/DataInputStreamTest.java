package com.wa.last.io.IOTest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DataInputStreamTest {


    public static void main(String[] args) {


        try (FileInputStream fileInputStream = new FileInputStream("/Users/wenchang/Desktop/a.txt");
             DataInputStream dataInputStream = new DataInputStream(fileInputStream)) {
            int available = dataInputStream.available();

//读取String
//            byte[] bytes = new byte[12];
//            dataInputStream.read(bytes);
//            System.out.println(new String(bytes,0,available));



//读取int,错误示范，处理的应该是二进制文件，而不是文本文件
            System.out.println(dataInputStream.readInt());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
