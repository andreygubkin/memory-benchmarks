import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

object TrueSharing : IDemo {

    override val title = "true sharing"

    override suspend fun execute() {
        val array = buildDemoArray()

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
            measureWorkMs(parallelism).also {
                println(it)
            }
        }
    }
}