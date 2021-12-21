package com.example.thread.ThreadCollect;

public class MultiThread extends Thread{
    @Override
    public void run() {
        System.out.println("我是多线程，我开始run了");

        while (!isInterrupted()){
            //do something, but no tthrow InterruptedException
                for (int i = 0;i<5000000;i++){
                    System.out.println("aasdf");
                    for (int j = 0;j<5000000;j++){
                    }
                }
            }
    }
}
