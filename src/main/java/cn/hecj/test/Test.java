package cn.hecj.test;

import java.util.HashSet;

public class Test {

    public static void main(String[] args){
        boolean isOpen = false;

        // 如果开启了断言，会将isOpen的值改为true
        assert isOpen = true;

        // 打印是否开启了断言，如果为false，则没有启用断言
        System.out.println(isOpen);

        HashSet s;

        assert 1+1==2;
        System.out.println(" assert ok");
        String  num = "1";
        switch(num){
            case "b":
             int a;
        }


    }
}
