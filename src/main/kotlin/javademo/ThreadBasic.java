package javademo;

public class ThreadBasic {


    public static void main(String args[]) {
        RunnerBasic runner1 = new RunnerBasic();
        RunnerBasic runner2 = new RunnerBasic();
        runner1.start();
        runner2.start();

    }
}

/* Output:
Thread task: 0
Thread task: 0
Thread task: 1
Thread task: 1
..
Thread task: 9
Thread task: 9
 */

/*Conclusion:
Both threads 1 & 2 ran concurrently.
 */

class RunnerBasic extends Thread {
    public void run() {
        Util.taskForThread();
    }
}