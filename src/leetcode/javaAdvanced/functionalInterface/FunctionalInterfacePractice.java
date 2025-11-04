package leetcode.javaAdvanced.functionalInterface;

import java.util.HashMap;
import java.util.Map;


@FunctionalInterface
interface Printer {
    int print(int value);
}

class MyException extends RuntimeException {
    public MyException(String message) {
        super(message);
    }
}

class CustomObject<K,V> {
    Map<K,V> map = new HashMap<>();

    public V addPlayer(K k, V v) {

        if(v instanceof  Integer) {

        }
        map.put(k, v);
        return v;
    }

    public Printer print = (value) -> value * 2;
}

class MiddleClass {
    CustomObject<String, Integer> customObject = new CustomObject<>();
    public void testFunction() throws MyException {

    }
}

public class FunctionalInterfacePractice {
    public static void main(String[] args) {
        MiddleClass middleClass = new MiddleClass();
        middleClass.testFunction();
    }
}
