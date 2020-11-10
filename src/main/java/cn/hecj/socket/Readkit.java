package cn.hecj.socket;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class Readkit {
    // 存请求头
    private static final ByteBuffer headerBuffer = ByteBuffer.allocate(15);
    private static final ByteBuffers dataBuffer = new ByteBuffers(1024);
    static int total = 0;

    public static void readFrom(InputStream input) throws Exception {
//        if (isFull()) {
//            throw new IllegalStateException("Buffer full, call next() to consume");
//        }
        if (dataBuffer.getWritePostion() - dataBuffer.getReadPostion() < 15) {
            dataBuffer.read(input);
        }

        // 读取
        printByteBuffers(dataBuffer);
        for (int i = dataBuffer.getReadPostion(); i < dataBuffer.getWritePostion() - 2; i++) {
            printByteBuffers(dataBuffer);
            if ((dataBuffer.get(i) == DataProtocol.Controller.type[0]) && (dataBuffer.get(i + 1) == DataProtocol.Controller.type[1])
                    && (dataBuffer.get(i + 2) == DataProtocol.Controller.type[2])) {
                // 遍历事件协议
                System.out.println("-----------------事件位置:" + i );
                headerBuffer.clear();
                System.arraycopy(dataBuffer.array(), i, headerBuffer.array(), 0, 15);
                headerBuffer.position(11);
                int length = headerBuffer.getInt();
                // 剩余长度不够length，继续read
                if (dataBuffer.getLimit() < length + 15) {
                    //继续read
                    dataBuffer.read(input);
                    return;
                }
                System.out.println("-----------------事件位置:" + i + ",total:" + (++total));
                System.out.println("事件长度length:" + length);
                printByteBuffers(dataBuffer);

//                    // 输入控制协议
//                    buffer.compact();
//                    int head = buffer.position();
//                    System.arraycopy(dataBuffer.array(),i+15,rawBuffer,head,length);
//                    buffer.position(head + length);
//                    buffer.flip();

                // 复位
                dataBuffer.setReadPostion(i + 15 + length);
                dataBuffer.setLimit(dataBuffer.getLimit() - 15 - length);
                printByteBuffers(dataBuffer);
                if (dataBuffer.getAvaiable() < 512) {
                    printByteBuffers(dataBuffer);
                    // 复制到开头
                    System.arraycopy(dataBuffer.array(), dataBuffer.getReadPostion(), dataBuffer.array(), 0, dataBuffer.getWritePostion() - dataBuffer.getReadPostion());
                    dataBuffer.setWritePostion(dataBuffer.getWritePostion() - dataBuffer.getReadPostion());
                    dataBuffer.setReadPostion(0);
                }
                break;
            } else {
                System.out.println("-----------------没有读到数据total:" + (total));
                dataBuffer.setReadPostion(i + 1);
                dataBuffer.setLimit(dataBuffer.getLimit() - 1);
            }
        }
    }

    private static void printByteBuffers(ByteBuffers byteBuffers) {
        System.out.println("ByteBuffers  ReadPostion:" + dataBuffer.getReadPostion() + " ,WritePostion:" + dataBuffer.getWritePostion() + ", Limit:" + dataBuffer.getLimit() + ", Avaiable:" + dataBuffer.getAvaiable());
    }
}
