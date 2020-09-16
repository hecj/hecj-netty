package cn.hecj.test;

import java.util.HashSet;

public class Test {

    public static void main(String[] args){
        byte[] bs= {-45,120};

        String str = bytes_String16(bs);
        System.out.println(str);
    }


    public static String bytes_String16(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<b.length;i++) {
            sb.append(String.format("%02x", b[i])+" ");
        }
        return sb.toString();
    }
}
