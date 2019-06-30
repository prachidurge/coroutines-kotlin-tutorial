import kotlinx.coroutines.*

fun main() {
    //  k3BlockingDispatcher()
    //k3LaunchGlobalScope()
    // k3LaunchGlobalScopeBadPractice()
    // k3LaunchGlobalWaiting()
    //k3LaunchLocalWaiting()
    k3LaunchLocalWaitingDispatcher()
}

// Running on another thread but still blocking the main thread.
fun k3BlockingDispatcher() {
    runBlocking(Dispatchers.Default) {
        println("one - from thread ${Thread.currentThread().name}")
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    println("three - from thread ${Thread.currentThread().name}")
}

/** Observation:
1) runBlocking runs both println and printlnDelayed() in sequence.
2) println(three) is on main thread, but it is executed only after the runBlock has finished it's task.
 */

fun k3LaunchGlobalScope() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    GlobalScope.launch {
        // will not block
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }

    println("three - from thread ${Thread.currentThread().name}")
}

/** Observation:
1) two is never printed!!

Reason:
Now that a non-blocking scope is launched,
Program knows not to block while doing the printDelay() task,
however, it doesn't know what to do with the delayed task of printDelay()
and doesn't really wait for it to happen and moves on!
 */

fun k3LaunchGlobalScopeBadPractice() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    GlobalScope.launch {
        // will not block
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    //***  delay(3000)  // switch between this and bottom delay
    println("three - from thread ${Thread.currentThread().name}")
    //***  delay(3000)
}

fun k3LaunchGlobalWaiting() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    val job = GlobalScope.launch {
        // will not block
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    //***  delay(3000)  // switch between this and bottom delay
    println("three - from thread ${Thread.currentThread().name}")
    //***  delay(3000)
    job.join()
}

fun k3LaunchLocalWaiting() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    this.launch {
        // will not block
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    //***  delay(3000)  // switch between this and bottom delay
    println("three - from thread ${Thread.currentThread().name}")
    //***  delay(3000)
    // job.join()
}

/** Observation: all the three printlns are on main thread - see the output */

fun k3LaunchLocalWaitingDispatcher() = runBlocking {
    println("one - from thread ${Thread.currentThread().name}")
    this.launch(Dispatchers.Default) {
        // will not block
        printlnDelayed("two - from thread ${Thread.currentThread().name}")
    }
    //***  delay(3000)  // switch between this and bottom delay
    println("three - from thread ${Thread.currentThread().name}")
    //***  delay(3000)
    // job.join()
}