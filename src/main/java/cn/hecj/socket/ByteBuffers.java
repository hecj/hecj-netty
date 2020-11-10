package cn.hecj.socket;

import java.io.InputStream;

public class ByteBuffers {

    private byte[] buffer = null;

    private int limit = 0;
    // 读位置
    private int readPostion = 0;
    // 写位置
    private int writePostion = 0;

    private int capacity = 0;

    public ByteBuffers(int capacity){
        buffer = new byte[capacity];
        this.capacity = capacity;
    }

    public byte[] array(){
        return this.buffer;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getReadPostion() {
        return readPostion;
    }

    public void setReadPostion(int readPostion) {
        this.readPostion = readPostion;
    }

    public int getWritePostion() {
        return writePostion;
    }

    public void setWritePostion(int writePostion) {
        this.writePostion = writePostion;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getAvaiable(){
        return this.capacity - this.writePostion ;
    }

    public byte get(int postion){
        return this.buffer[postion];
    }

    public void read(InputStream inputStream) throws Exception{
        int length = inputStream.read(this.array(), this.getWritePostion(), this.getAvaiable());
        if (length == -1) {
            return;
        }
        this.setWritePostion(this.getWritePostion() + length);
        this.setLimit(this.getLimit() + length);
    }
}