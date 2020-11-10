package cn.hecj.socket;

import java.io.InputStream;
import java.nio.ByteBuffer;

public class Readkit2 {
    // 存请求头
    private static final ByteBuffer headerBuffer = ByteBuffer.allocate(15);
    private static final ByteBuff dataBuffer = new ByteBuff(1024);
    private static final int HEAD_LENGTH = 15;
    static int total = 0;

    public static void readFrom(InputStream input) throws Exception {
//        if (isFull()) {
//            throw new IllegalStateException("Buffer full, call next() to consume");
//        }
        if (dataBuffer.getWritePostion() - dataBuffer.getReadPostion() < HEAD_LENGTH) {
            dataBuffer.read(input);
        }
        printByteBuffers(dataBuffer);
        for (int i = dataBuffer.getReadPostion(); i < dataBuffer.getWritePostion() - 2; i++) {
            if ((dataBuffer.get(i) == DataProtocol.Controller.type[0]) && (dataBuffer.get(i + 1) == DataProtocol.Controller.type[1])
                    && (dataBuffer.get(i + 2) == DataProtocol.Controller.type[2])) {
                System.arraycopy(dataBuffer.array(), i, headerBuffer.array(), 0, HEAD_LENGTH);
                headerBuffer.position(11);
                int length = headerBuffer.getInt();
                if (dataBuffer.getLimit() < length + HEAD_LENGTH) {
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

                System.out.println("total:"+(++total));

                // 下一个位置
                dataBuffer.nextBuff(i, HEAD_LENGTH+length);
                if (dataBuffer.getAvaiable() < 256 || dataBuffer.getReadPostion() >512) {
                    // 复位条件
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
