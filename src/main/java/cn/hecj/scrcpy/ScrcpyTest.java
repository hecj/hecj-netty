package cn.hecj.scrcpy;
import java.io.*;
import java.net.Socket;

/**
 * @author ScrcpyClient
 * @date 2020年9月16日
 * @version 1.0
 */
public class ScrcpyTest {

    public static void main(String[] args) throws Exception{

        File file = new File("/Users/hecj/Desktop/hecj99/test2/test2.h264");
        FileInputStream inputStream = new FileInputStream(file);

        int leng = inputStream.available();
        System.out.println("size:"+leng);
        byte[] bytes = new byte[leng];
        inputStream.read(bytes,0,leng);

        inputStream.close();

        File file2 = new File("/Users/hecj/Desktop/hecj99/test2/data/f1.h264");
        FileInputStream inputStream2 = new FileInputStream(file2);

        int leng2 = inputStream2.available();
        System.out.println("size2:"+leng2);
        byte[] bytes2 = new byte[leng2];
        inputStream2.read(bytes2,0,leng2);

        inputStream2.close();

        for(int i=0;i<leng;i++){
            if(bytes[i] ==bytes2[0] && bytes[i+1] ==bytes2[1]&& bytes[i+2] ==bytes2[2]
                    && bytes[i+3] ==bytes2[3] && bytes[i+4] ==bytes2[4] ){
                System.out.println("----");
                System.out.println("index "+i);
                System.out.println("i value "+bytes[i]);
            }
        }

    }
}