package cn.hecj.scrcpy;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author ScrcpyClient
 * @date 2020年9月16日
 * @version 1.0
 */
public class ScrcpyFrame {

    private static int findHead(byte[] data, int start, int len)
    {
//        System.out.println("findHead: start="+start+" len="+len);
        int offset = start;
        int end = offset + len;
        int found = -1;

        while(offset + 3 < end){
            if (data[offset+0]==0x0 && data[offset+1]==0x0 && data[offset+2]==0x1){
                found = offset;
                break;
            }
            offset++;
        }
        return found;
    }
    public static void main(String[] args) throws Exception{

    }

    public static void getFrameData(InputStream inputStream) throws Exception{

        // 1M缓存
        byte[] bufferData = new byte[1 * 1024 * 1024];
        // 1帧的数据
        byte[] frameData = new byte[1 * 1024 * 1024];
        // 下一个buffer开始位置
        int nextBufferOffset= 0;
        // 扫描位置
        int checkOffset = 0;
        // 数据的长度
        int bufferTotal = 0;

        int loadLength = 10*1024;

        int head1 = -1;
        int head2 = -1;

        int num = 0;

        while(true){

            if(inputStream.available() == 0){
               continue;
            }

            // 找第1个header
            while(true){
                inputStream.read(bufferData,nextBufferOffset,loadLength);
                // 数据长度
                bufferTotal += loadLength;
                // 下一次数据加载位置
                nextBufferOffset += loadLength;
                // 找head
                head1 = findHead(bufferData, checkOffset, bufferTotal-checkOffset);
                if(head1 != -1){
                    // 找第二个
                    checkOffset = head1+4;
                    break;
                }
                // 下一次check位置
                checkOffset = bufferTotal;
            }

            // 找第2个header
            while(true){
                inputStream.read(bufferData,nextBufferOffset,loadLength);
                // 数据长度
                bufferTotal += loadLength;
                // 下一次数据加载位置
                nextBufferOffset += loadLength;
                // 找head
                head2 = findHead(bufferData, checkOffset, bufferTotal-checkOffset);
                if(head2 != -1){
                    // 找第二个
                    checkOffset = head2+4;
                    break;
                }
                // 下一次check位置
                checkOffset = bufferTotal;
            }
            // 找到一帧数据，复制到帧缓存
            int frameLength = head2-head1;
            System.arraycopy(bufferData,head1,frameData,0,frameLength);

            // buffer中的数据  head2 -> bufferTotal
            // 重置数据
            bufferTotal = bufferTotal-head2;
            System.arraycopy(bufferData,head2,bufferData,0,bufferTotal);

            nextBufferOffset = bufferTotal;
            checkOffset = 0;
            head1 = -1;
            head2 = -1;

            System.out.println("找到一帧: "+(num++)+"_"+frameLength);

        }



    }
}