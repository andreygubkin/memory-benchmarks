import kotlin.system.measureTimeMillis

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
            200,
            300,
        ).forEach { matrixSize ->
            val ms = measureMatrixMultiplyMs(matrixSize)
            println(ms)
        }
    }

    private infix fun Matrix.multiply(matrix2: Matrix): Matrix {
        val matrix1 = this
        return matrix1
    }
}