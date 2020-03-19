package ReentrantLock;

import UnreentrantLock.UnreentrantLock;

public class Test {
    private ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        new Test().method1();
    }
    public void method1(){
        try {
            reentrantLock.lock();
            System.out.println("执行方法一");
            method2();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unLock();
        }
    }
    public void method2(){
        try {
            reentrantLock.lock();
            System.out.println("执行方法二");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantLock.unLock();
        }
    }
}
