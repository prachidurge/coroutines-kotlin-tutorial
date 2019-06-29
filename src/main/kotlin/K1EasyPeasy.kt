import kotlinx.coroutines.delay

fun main() {
    synchronousFunc()
    exampleDelayedUsingThread()
}

fun synchronousFunc() {
    println("one")
    println("two")
    println("three")
}


fun exampleDelayedUsingThread() {
    println("one")
    printDelayedThread("two")
    println("three")
}

fun printDelayedThread(message: String) {
    Thread.sleep(2000) // A very engineered code here!!!
    println(message)
}


/* fun printDelayed(message: String) {
    delay(2000)
    println(message)
}*/


