package cn.hecj.socket;
/*
 * Copyright 2013-2018 Lilinfeng.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @author lilinfeng
 * @date 2014年2月14日
 * @version 1.0
 */
public class TimeClient {

    /**
     * @param args
     */
    public static void main(String[] args) {

        int port = 8080;
        if (args != null && args.length > 0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 采用默认值
            }

        }
        Socket socket = null;
        InputStream in = null;
        PrintWriter out = null;
        try {
            socket = new Socket("127.0.0.1", port);
            in = socket.getInputStream();

            OutputStream outputStream = socket.getOutputStream();

            for(int i=0;i<200;i++){
                System.out.println(i);
                Thread.sleep(30);
                ByteBuffer headerBuffer = ByteBuffer.allocate(11+15);
//        // 15
                headerBuffer.put(DataProtocol.Controller.type);
                headerBuffer.putLong(System.currentTimeMillis());
                headerBuffer.putInt(20);
                // 类型 1 byte
                headerBuffer.put((byte)5);
                // id  1 byte
                int id = 1;
                headerBuffer.put((byte)id);

                //  按下 抬起  1 byte
                headerBuffer.put((byte)2);

                int x = (int)33;
                int y = (int)22;

                // 坐标
                headerBuffer.put((byte) (x >> 8));
                headerBuffer.put((byte) x);
                headerBuffer.put((byte) (y >> 8));
                headerBuffer.put((byte) y);
                int displayW = 1000;
                int displayH = 2000;
                // 屏幕
                headerBuffer.put((byte) (displayW >> 8));
                headerBuffer.put((byte) displayW);
                headerBuffer.put((byte) (displayH >> 8));
                headerBuffer.put((byte) displayH);

                headerBuffer.flip();
                outputStream.write(headerBuffer.array());
            }

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
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