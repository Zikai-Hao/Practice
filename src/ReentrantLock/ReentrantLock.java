package ReentrantLock;

public class ReentrantLock {
    private boolean islock = false;
    private Thread lockOwner = null;
    private int lockCount= 0;
    public synchronized void lock() throws InterruptedException{
        Thread thread = Thread.currentThread();
        while(islock&&lockOwner!=thread){
            System.out.println("获取锁失败，进入阻塞状态"+Thread.currentThread().getName());
            wait();
        }
        islock=true;
        System.out.println("上锁");
        lockOwner=thread;
        lockCount++;
    }

    public synchronized void unLock(){
        if(!islock) return;//排除未上锁情况
        Thread thread = Thread.currentThread();
        if(thread==lockOwner){
            lockCount--;
            System.out.println("锁层数减一");
            if(lockCount==0){
                System.out.println("解锁成功"+Thread.currentThread().getName());
                lockOwner=null;
                islock=false;
                notify();
            }
        }
    }
}
