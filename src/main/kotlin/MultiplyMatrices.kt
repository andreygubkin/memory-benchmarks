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
            700,
            800,
            900,
            1000,
            1100,
            1200,
            1300,
            1400,
            1500,
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
                sum += matrix1[row][i] * matrix2[i][column]
            }
            sum
        }
    }
}
