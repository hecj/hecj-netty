package cn.hecj.socket;

import java.io.InputStream;

public class ByteBuff {

    private byte[] buffer = null;

    private int limit = 0;
    // 读位置
    private int readPostion = 0;
    // 写位置
    private int writePostion = 0;

    private int capacity = 0;

    public ByteBuff(int capacity){
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
    /**
     * 复制到首部
     */
    public void copyToHead(){
        System.arraycopy(this.array(), this.getReadPostion(), this.array(), 0, this.getWritePostion() - this.getReadPostion());
        this.setWritePostion(this.getWritePostion() - this.getReadPostion());
        this.setReadPostion(0);
    }

    public void nextBuff(int currentReadPostion, int readLength){
        this.setReadPostion(currentReadPostion + readLength);
        this.setLimit(this.getLimit() - readLength);
    }

}