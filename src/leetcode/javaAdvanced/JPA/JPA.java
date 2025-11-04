package leetcode.javaAdvanced.JPA;

import java.sql.Statement;

class Demo {
    private static volatile Demo demo;
    private final static Object lock = new Object();
    private Demo(){}

    public void print() {
        System.out.println("This is singleton");
    }
    public static Demo getInstance() {
        if(demo == null) {
            synchronized (Demo.lock) {
                if(demo == null) {
                    demo = new Demo();
                }
            }
        }
        return demo;
    }
}

public class JPA {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        Demo d = Demo.getInstance();
        Demo d2 = Demo.getInstance();
        System.out.println(d == d2);
        d.print();
    }
}
