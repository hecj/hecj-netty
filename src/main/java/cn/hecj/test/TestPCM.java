package cn.hecj.test;

import java.io.File;
import java.io.FileInputStream;

public class TestPCM {

    public static void main(String[] args) throws Exception{

//        File file = new File("/Users/hecj/Desktop/test.pcm");
        File file = new File("/Users/hecj/Desktop/cosmop/test.pcm");

        FileInputStream inputStream = new FileInputStream(file);

//        byte[] bytes = new byte[1024];
        int n = -1;
//        while((n = inputStream.read(bytes))!=-1){
//            System.out.println(n);
//        }
        System.out.println("开始读");
        int length = inputStream.available();
        for(int i=0;i<length;i++){
            int b = inputStream.read();
            if(b>0){
                System.out.println(b);
            }
        }
        System.out.println("读完!");
    }
}
