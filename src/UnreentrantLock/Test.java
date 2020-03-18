package UnreentrantLock;

public class Test {
    private UnreentrantLock unreentrantLock = new UnreentrantLock();
    public static void main(String[] args) {
        new Test().method1();
    }
    public void method1(){
        try {
            unreentrantLock.lock();
            System.out.println("执行方法一");
            method2();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            unreentrantLock.unlock();
        }
    }
    public void method2(){
        try {
            unreentrantLock.lock();
            System.out.println("执行方法二");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            unreentrantLock.unlock();
        }
    }
}
