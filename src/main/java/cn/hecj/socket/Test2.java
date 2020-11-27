package cn.hecj.socket;

public class Test2 {

    public static void main(String[] args) {
        byte a = (byte)-24;
        // 无符号
        System.out.println((a&0XFF) == 232);
        // 变成无符号
        System.out.println(-24&0XFF);
    }
}
