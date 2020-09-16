package cn.hecj.test;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class Test1 {


    @Test
    public void test1(){

        int number[] = {1,2,3,4};
        String s = "";

        System.out.println();
    }

    @Test
    public void test2(){
        System.out.println(test22());

        OutOfMemoryError d;
        HashMap a;

    }

    public int test22(){
        System.out.println("test22");
        try{
            return test333();
        }catch (Exception ex){

        }finally {
            System.out.println("finally");
        }
        return 1;
    }
    public int test333(){
        System.out.println("test333");
        return 333;
    }

    @Test
    public void test3(){
        Object obj = new Object();
        Thread t1 = new Thread(){
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("t1 start");
                    try {
                         obj. wait();
                        Thread.sleep(200l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 end");
                }

            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                    System.out.println("t2 start");
                    try {
                        Thread.sleep(5000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                synchronized (obj) {
                    obj.notify();//唤起
                };
                    System.out.println("t2 end");
            }
        };

        t1.start();
        t2.start();

        try {
            Thread.sleep(200000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void  main(String[] args){

        int width = 720;
        int height = 1280;

        /**
         buffer[DEVICE_NAME_FIELD_LENGTH] = (byte) (width >> 8);
         buffer[DEVICE_NAME_FIELD_LENGTH + 1] = (byte) width;
         buffer[DEVICE_NAME_FIELD_LENGTH + 2] = (byte) (height >> 8);
         buffer[DEVICE_NAME_FIELD_LENGTH + 3] = (byte) height;
         */



        System.out.println(width >>8);
        byte lowWidht = (byte)(width >>8);
        System.out.println(lowWidht);

        int aa = 720;
        byte ba = (byte)(aa >>8);
//        System.out.println((UnsignedByte)aa);

        System.out.println(2 << 8);

        System.out.println(512|-48);

        System.out.println(-48 & 0xff);

        send("deviceName",720,1280);

    }

    static int DEVICE_NAME_FIELD_LENGTH = 64;
    private static void send(String deviceName, int width, int height)  {
        byte[] buffer = new byte[64 + 4];

        byte[] deviceNameBytes = deviceName.getBytes(StandardCharsets.UTF_8);
        int len = Math.min(64 - 1, deviceNameBytes.length);
        System.arraycopy(deviceNameBytes, 0, buffer, 0, len);
        // byte[] are always 0-initialized in java, no need to set '\0' explicitly

        buffer[DEVICE_NAME_FIELD_LENGTH] = (byte) (width >> 8);
        buffer[DEVICE_NAME_FIELD_LENGTH + 1] = (byte) width;
        buffer[DEVICE_NAME_FIELD_LENGTH + 2] = (byte) (height >> 8);
        buffer[DEVICE_NAME_FIELD_LENGTH + 3] = (byte) height;

        System.out.println(buffer);
    }



}
