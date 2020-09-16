package cn.hecj.test;
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
import javassist.bytecode.ByteArray;

import java.io.*;
import java.net.Socket;

/**
 * @author Administrator
 * @date 2014年2月14日
 * @version 1.0
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
//        BufferedReader in = null;
//        PrintWriter out = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            in = this.socket.getInputStream();
            out = this.socket.getOutputStream();
//            in = new BufferedReader(new InputStreamReader(
//                    this.socket.getInputStream()));
//            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            byte[] data = new byte[1024];
            int len = 0;
            while((len = in.read(data)) != -1){
                System.out.println(len);
                for(int i=0;i<len;i++){
                    System.out.print((byte)data[i]+" ");
                }
                System.out.println();
//                body = in.readLine();
//                if (body == null)
//                    break;
//                System.out.println("The time server receive order : " + body);
//                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new java.util.Date(
//                        System.currentTimeMillis()).toString() : "BAD ORDER";
//                out.println(currentTime);

            }

        } catch (Exception e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                out = null;
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}