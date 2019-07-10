package zzu.mxd.cpp;

public class Test {
    static {
        System.load("C:/Users/麻晓东/Desktop/Test/x64/Debug/Test.dll");
    }

    public static void main(String[] args){
        TestNative testNative = new TestNative();
        testNative.test();
    }
}
