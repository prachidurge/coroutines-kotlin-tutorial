import kotlinx.coroutines.*
import kotlinx.coroutines.delay

fun main() {

   // k5ExampleAsyncAwait()
    k5ExampleAsyncAwaitAll()
    k5ExampleAsyncAwaitWithContext()
}

fun k5ExampleAsyncAwait() = runBlocking {
    val startTime =  System.currentTimeMillis()
    val deferred1 = async {myComplexTheoryOFCoroutinity(startNum = 10)}
    val deferred2 = async {myComplexTheoryOFCoroutinity(20)}
    val deferred3 = async {myComplexTheoryOFCoroutinity(startNum = 30)}
    val sum = deferred1.await() + deferred2.await() + deferred3.await()
    println("async/await result = $sum")

    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - startTime}")
}

suspend fun myComplexTheoryOFCoroutinity(startNum: Int): Int {
    delay(1000)
    return startNum * 10
}

fun k5ExampleAsyncAwaitAll() = runBlocking {
    val startTime =  System.currentTimeMillis()
    val deferred1 = async {myComplexTheoryOFCoroutinity(startNum = 10)}.await()
    val deferred2 = async {myComplexTheoryOFCoroutinity(20)}.await()
    val deferred3 = async {myComplexTheoryOFCoroutinity(startNum = 30)}.await()
    val sum = deferred1 + deferred2 + deferred3
    println("async/await result = $sum")

    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - startTime}")
}

fun k5ExampleAsyncAwaitWithContext() = runBlocking {
    val startTime =  System.currentTimeMillis()
    val result1 = withContext(Dispatchers.Default) {myComplexTheoryOFCoroutinity(startNum = 10)}
    val result2 = withContext(Dispatchers.Default) {myComplexTheoryOFCoroutinity(20)}
    val result3 = withContext(Dispatchers.Default) {myComplexTheoryOFCoroutinity(startNum = 30)}
    val sum = result1 + result2 + result3
    println("async/await result = $sum")

    val endTime = System.currentTimeMillis()
    println("Time taken: ${endTime - startTime}")
}
