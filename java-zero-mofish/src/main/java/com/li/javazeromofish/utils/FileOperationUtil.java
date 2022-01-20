package com.li.javazeromofish.utils;

import cn.hutool.core.io.FileUtil;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/1/12 14:23
 */
public class FileOperationUtil {

    /**
     * 文件转数组
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] file2Bytes(String path) throws IOException {
        ByteArrayOutputStream baos = null;
        FileInputStream fis = null;
        byte[] buffer = new byte[0];
            File file = new File(path);
            fis = new FileInputStream(file);
            baos = new ByteArrayOutputStream(fis.available());
            byte[] bytes = new byte[fis.available()];
            int temp;
            while ((temp = fis.read(bytes)) != -1) {
                baos.write(bytes, 0, temp);
            }
            fis.close();
            baos.close();
            buffer = baos.toByteArray();
        return buffer;
    }

    /**
     * 拼接pdf demo
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        List<byte[]> bytes = new ArrayList<>();
        bytes.add(FileOperationUtil.file2Bytes("C:/Users/10586/AppData/Local/Oracle/BIPUBL~1/TEMPLA~2/tmp/tmp/121641971658414out.pdf"));
        bytes.add(FileOperationUtil.file2Bytes("C:/Users/10586/AppData/Local/Oracle/BIPUBL~1/TEMPLA~2/tmp/tmp/491641970348553out.pdf"));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();

        for (byte[] aByte : bytes) {
            pdfMergerUtility.addSource(new ByteArrayInputStream(aByte));
        }
        pdfMergerUtility.setDestinationStream(outputStream);

        pdfMergerUtility.mergeDocuments(null);

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\10586\\Documents\\甄云\\22_0122\\test.pdf");
        fileOutputStream.write(outputStream.toByteArray());
        fileOutputStream.close();
        outputStream.close();
    }
}
