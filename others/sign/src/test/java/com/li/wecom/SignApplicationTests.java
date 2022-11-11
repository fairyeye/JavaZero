package com.li.wecom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;

@SpringBootTest
class SignApplicationTests {

    @Test
    void contextLoads() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec("C:/Users/10586/.pyenv/pyenv-win/versions/3.11.0rc2/python.exe d:/Code/Ato/daily-info/index.py");
        InputStream inputStream = exec.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        inputStream.close();
        int i = exec.waitFor();
        System.out.println(i);
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("0.1").compareTo(BigDecimal.ZERO) > 0);
        System.out.println(new BigDecimal("0.1").multiply(BigDecimal.valueOf(60)).multiply(BigDecimal.valueOf(60))
                .multiply(BigDecimal.valueOf(1000)).longValue());
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process exec = runtime.exec("C:/Users/10586/.pyenv/pyenv-win/versions/3.11.0rc2/python.exe d:/Code/Ato/daily-info/index.py");
//            InputStream inputStream = exec.getInputStream();
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            String line = null;
//            while ((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
//            }
//            inputStream.close();
//            int i = exec.waitFor();
//            System.out.println(i);
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
