package cn.hecj.socket;

public enum DataProtocol {

    Video(new byte[]{0,0,2}),
    Controller(new byte[]{0,0,9}),
    BitRate(new byte[]{0,0,8});

    public byte[] type;
    DataProtocol(byte[] bytes){
        this.type = bytes;
    }

}