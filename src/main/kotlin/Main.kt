import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun main() {

    val array: LongArray = buildArray(
        size = 1 shl 29, // 512 mb * Long = 4gb
    )

    fun work() {
        var sum = 0L
        for (i in 0L..array.size) {
            sum += array[(i % array.size).toInt()]
        }
        sum.sendToBlackHole()
    }

    suspend fun measureWorkMs(
        parallelism: Int,
    ): Long {
        //val threads = hashSetOf<String>()
        val ms = measureTimeMillis {
            withContext(Dispatchers.IO.limitedParallelism(parallelism)) {
                repeat(parallelism) {
                    launch {
                        //threads.add(Thread.currentThread().name)
                        work()
                    }
                }
            }
        }
        //check(threads.size == parallelism)
        return ms
    }

    val maxThreads = 16

    suspend fun warmUp() {
        measureWorkMs(maxThreads) // потоки создать
    }

    warmUp()

    repeat(maxThreads) {
        val parallelism = it + 1
        measureWorkMs(parallelism).also {
            //println("${parallelism}\t$it")
            println("$it")
        }
    }
}

/**
 *
 */
private fun Any.sendToBlackHole() {
    this.toString()
}

private fun buildArray(
    @Suppress("SameParameterValue") size: Int,
): LongArray {
    return LongArray(size) {
        Random.nextLong(until = 1_000)
    }
}
