package com.li.javazeromofish.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/1/13 14:18
 */
public class FileUtils {

    public static void byte2File() throws IOException {

        byte[] bytes = new byte[]{};

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\10586\\Documents\\甄云\\22_0122\\test.pdf");
        fileOutputStream.write(bytes);

    }

    public static void main(String[] args) throws IOException {
        FileUtils.byte2File2();

//        byte[] bytes = FileOperationUtil.file2Bytes("C:\\Users\\10586\\Documents\\甄云\\22_0122\\test.pdf");

//        byte[] bytes = FileOperationUtil.file2Bytes("C:/Users/10586/AppData/Local/Oracle/BIPUBL~1/TEMPLA~2/tmp/tmp/511642056698876out.pdf");
//        System.out.println(Arrays.toString(bytes));

//        String isFlag = "-1";
//
//
//        if (!isFlag.equals("-1") || !isFlag.equals(-1)) {
//            System.out.println(true);
//        }

    }


    public static void byte2File2() throws IOException {

        String[] split = org.apache.commons.io.FileUtils.readFileToString(new File("C:\\Users\\10586\\WorkSpace\\byts.txt")).split(",");
        int[] ints = Arrays.asList(split).stream().mapToInt(value -> Integer.valueOf(value.replace(" ", ""))).toArray();

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\10586\\Documents\\甄云\\22_0122\\test.pdf");
        for (int anInt : ints) {
            fileOutputStream.write(anInt);
        }
    }


}
