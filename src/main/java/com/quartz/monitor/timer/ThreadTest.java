package com.quartz.monitor.timer;


public class ThreadTest {
    static GetAuthorInfoTimer timer = new GetAuthorInfoTimer();
    public static void main(String[] args){
        MyThread[] threads = new MyThread[100];
        for(MyThread thread : threads){
            thread = new MyThread();
            thread.start();
        }
    }
    
     static class MyThread extends Thread{
        public void run(){
            timer.executeAuthorInfoTask();
        }
    }
}
