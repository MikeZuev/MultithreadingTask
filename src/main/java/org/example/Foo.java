package org.example;

import java.util.concurrent.Semaphore;

public class Foo {

    boolean workSecond = true;
    boolean workThird = true;

    public synchronized void first(Runnable r) {
        System.out.println("first");
        workSecond = false;
        notify();


    }

    public synchronized void second(Runnable r) {
       while(workSecond) {
           try {
               wait();
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }

        System.out.println("second");
        notify();
        workThird = false;

    }

    public synchronized void third(Runnable r) {
        try {
            while(workThird){
                wait();
            }
            System.out.println("third");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
