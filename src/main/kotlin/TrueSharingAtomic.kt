import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicLong
import kotlin.system.measureTimeMillis

@Suppress("unused")
object TrueSharingAtomic : IDemo {
    override val title = "true sharing atomic"

    override suspend fun execute() {
        val array = buildRandomArray(
            size = 1 shl 26, // 64 mb * Long = 512mb
        )

        val totalSum = AtomicLong()

        fun work() {
            var partialSum = 0L
            for (i in 0L..array.size) {
                partialSum += array[(i % array.size).toInt()]
            }
            totalSum.addAndGet(partialSum)
        }

        suspend fun measureWorkMs(
            parallelism: Int,
        ): Long {
            val ms = measureTimeMillis {
                withContext(Dispatchers.IO.limitedParallelism(parallelism)) {
                    repeat(parallelism) {
                        launch {
                            work()
                        }
                    }
                }
            }
            return ms
        }

        val maxThreads = 16

        suspend fun warmUp() {
            measureWorkMs(maxThreads) // потоки создать
        }

        warmUp()

        println("ms")
        repeat(maxThreads) {
            val parallelism = it + 1
            measureWorkMs(parallelism).also { ms ->
                println(ms)
            }
        }
    }
}