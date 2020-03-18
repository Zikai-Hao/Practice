package UnreentrantLock;

public class UnreentrantLock  {
    private boolean isLock=false;
    public synchronized void lock() throws InterruptedException{
        while(isLock){
            System.out.println("获取锁失败，进入阻塞状态"+Thread.currentThread().getName());
            wait();
        }
        isLock=true;
    }

    public synchronized void unlock(){
        System.out.println("释放锁资源");
        isLock = false;
        notify();
    }
}


