package cn.hecj.scrcpy;
import java.io.*;
import java.net.Socket;

/**
 * @author ScrcpyClient
 * @date 2020年9月16日
 * @version 1.0
 */
public class ScrcpyClient {

    public static void main(String[] args) throws Exception{

        File file = new File("/Users/hecj/Desktop/hecj99/test2/test2.h264");
        FileOutputStream outputStream = new FileOutputStream(file);
        int port = 27183;
        Socket socket = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            socket = new Socket("192.168.50.88", port);
            in = socket.getInputStream();
            out = socket.getOutputStream();

            while(true){
                int len = in.available();
                if(len>0){
                    System.out.println(len);
                    byte[] bytes = new byte[len];
                    in.read(bytes);

                    outputStream.write(bytes);
                    outputStream.flush();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                out = null;
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}