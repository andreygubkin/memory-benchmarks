import kotlin.system.measureTimeMillis

@Suppress("unused")
object MultiplyMatrices : IDemo {
    override val title = "multiply matrices"

    override suspend fun execute() {
        fun measureMatrixMultiplyMs(
            matrixSize: Int,
        ): Long {
            val matrix1 = buildRandomMatrix(size = matrixSize)
            val matrix2 = buildRandomMatrix(size = matrixSize)

            val ms = measureTimeMillis {
                (matrix1 multiply matrix2).sendToBlackHole()
            }

            return  ms
        }

        println("ms")
        listOf(
            100,
            150,
            200,
            250,
            300,
            350,
            400,
        ).forEach { matrixSize ->
            val ms = measureMatrixMultiplyMs(matrixSize)
            println(ms)
        }
    }

    private infix fun Matrix.multiply(
        matrix2: Matrix,
    ): Matrix {
        val matrix1 = this
        return buildMatrix(size = matrix1.size) { row, column ->
            var sum = 0.0
            for (i in 0 until size) {
                for (j in 0 until size) {
                    sum += matrix1[row][i] * matrix2[j][column]
                }
            }
            sum
        }
    }
}
