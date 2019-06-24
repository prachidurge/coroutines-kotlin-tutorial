package javademo;

import static java.lang.Thread.sleep;

public class Util {

    public static void taskForThread(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread task: "+ i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void task2ForThread(){
        for (int i = 0; i < 10; i++) {
            System.out.println("Second task: "+ i);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
