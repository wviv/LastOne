package com.wa.last.io.IOTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest {


    public static void main(String[] args) {


        try (FileInputStream fileInputStream = new FileInputStream("/Users/wenchang/Desktop/a.txt")) {

            //每次读取1kb
            byte[] bytes = new byte[1024];

            while (fileInputStream.read(bytes) != -1){

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }


}
