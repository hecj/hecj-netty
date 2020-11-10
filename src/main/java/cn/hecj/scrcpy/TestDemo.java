package cn.hecj.scrcpy;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author ScrcpyClient
 * @date 2020年9月16日
 * @version 1.0
 */
public class TestDemo {

    public static void main(String[] args) {

        short num = 23232;
        int num2 = num & 0xffff;
        System.out.println(num2);
    }
}