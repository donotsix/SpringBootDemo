package org.com.thread.test;

public class SyncThread2 implements Runnable{
    int monney=100;
    
    
    public int getMonney() {
        return monney;
    }


    public void setMonney(int monney) {
        this.monney = monney;
    }


    @Override
    public void run() {
        for(int i=0;i<100;i++){
            monney+=100;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            monney-=100;
           
            System.out.println(Thread.currentThread().getName()+":"+monney);
        }
      
    }

}
