import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main() {
    k4CustomWaitingDispatcher()
}

fun k4CustomWaitingDispatcher() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")

    val customExecuter = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
    this.launch(customExecuter) {
        // will not block
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")

}
/** 1) observe the name of thread.
 *  2) Try to re-run program and see that old instance of main() hasn't exit yet!!!
 *    // use : (customExecuter.executor as ExecutorService).shutdown()
 * */

/* This launch method, returns nothing... in real scenarios, we want our coroutine to return something,
   like a message or some data fetched from server, or a result of a long running calculation..
 */