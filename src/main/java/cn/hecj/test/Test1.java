package cn.hecj.test;
import org.junit.Test;

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
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(500l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t1 run");
                }
            }
        };
        t1.setDaemon(true);
        t1.start();

         try {
            Thread.sleep(5000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}
