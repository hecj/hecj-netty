package cn.hecj.socket;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class Readkit2 {
    // 存请求头
    private static final ByteBuffer headerBuffer = ByteBuffer.allocate(15);
    private static final ByteBuff dataBuffer = new ByteBuff(1024);
    static int total = 0;

    public static void readFrom(InputStream input) throws Exception {
//        if (isFull()) {
//            throw new IllegalStateException("Buffer full, call next() to consume");
//        }
        if (dataBuffer.getWritePostion() - dataBuffer.getReadPostion() < 15) {
            dataBuffer.read(input);
        }
        printByteBuffers(dataBuffer);
        for (int i = dataBuffer.getReadPostion(); i < dataBuffer.getWritePostion() - 2; i++) {
            if ((dataBuffer.get(i) == DataProtocol.Controller.type[0]) && (dataBuffer.get(i + 1) == DataProtocol.Controller.type[1])
                    && (dataBuffer.get(i + 2) == DataProtocol.Controller.type[2])) {
                System.arraycopy(dataBuffer.array(), i, headerBuffer.array(), 0, 15);
                headerBuffer.position(11);
                int length = headerBuffer.getInt();
                System.out.println("读取数据长度length:"+length);
                if (dataBuffer.getLimit() < length + 15) {
                    dataBuffer.read(input);
                    printByteBuffers(dataBuffer);
                    return;
                }
                printByteBuffers(dataBuffer);
//                    // 输入控制协议
//                    buffer.compact();
//                    int head = buffer.position();
//                    System.arraycopy(dataBuffer.array(),i+15,rawBuffer,head,length);
//                    buffer.position(head + length);
//                    buffer.flip();

                // 下一个位置
                dataBuffer.nextBuff(i, 15+length);
                if (dataBuffer.getAvaiable() < 256 || dataBuffer.getReadPostion() >512) {
                    dataBuffer.copyToHead();
                }
                printByteBuffers(dataBuffer);
                break;
            } else {
                printByteBuffers(dataBuffer);
                dataBuffer.setReadPostion(i + 1);
                dataBuffer.setLimit(dataBuffer.getLimit() - 1);
            }
        }
    }

    private static void printByteBuffers(ByteBuff byteBuffers) {
        System.out.println("ByteBuffers  ReadPostion:" + dataBuffer.getReadPostion() + " ,WritePostion:" + dataBuffer.getWritePostion() + ", Limit:" + dataBuffer.getLimit() + ", Avaiable:" + dataBuffer.getAvaiable());
    }
}
