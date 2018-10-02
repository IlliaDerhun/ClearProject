package clearproject.illiaderhun.com.github.deadblock;

public class Main extends Object {
    private static Object Lock1 = new Object();
    private static Object Lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new ThreadDemo1();
        Thread thread2 = new ThreadDemo2();
        thread1.start();
        thread2.start();
    }

    private static class ThreadDemo1 extends Thread {
        @Override
        public void run() {
            synchronized(Lock1) {
                System.out.println("Holding lock1");

                try { Thread.sleep(10); }
                catch (InterruptedException ex){}
                System.out.println("Waiting for lock2");

                synchronized (Lock2){
                    System.out.println("Holding lock 1 & 2");
                }
            }
        }
    }

    private static class ThreadDemo2 extends Thread{
        @Override
        public void run() {
            synchronized (Lock2) {// synchronized(Lock1) - non deadblock
                System.out.println("Holding lock1");

                try{Thread.sleep(10);}
                catch (InterruptedException e) {}
                System.out.println("Waiting for lock2");

                synchronized (Lock1) {// synchronized(Lock2) - non deadblock
                    System.out.println("Holding lock 2 & 1");
                }
            }
        }

    }
}
