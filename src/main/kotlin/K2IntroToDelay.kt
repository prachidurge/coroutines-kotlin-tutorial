import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
    k2PrintWithDelay()

}


fun k2PrintWithDelay() {
    println("one")
    //printDelayed("two")
    printDelayedRB()
    printDelayedRB2()
    println("three")
}

/*
suspend fun printDelayed(message: String) {
    delay(2000)
    println(message)
}*/


suspend fun printDelayed(message: String) {
    delay(2000)
    println(message)
}

fun printDelayedRB() {
    println("one")
    runBlocking {
        printDelayed("two")
    }
    println("three")

}

/**
 *  Way#2: rooting the runBlocking
 */
fun printDelayedRB2() = runBlocking {

    println("one")
    printDelayed("two")
    println("three")
}

/* NOTES:
runBlocking() - interrupts the main thread. It is similar to threads
 */