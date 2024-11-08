import kotlin.random.Random

/**
 * Отмечать востребованные объекты, чтобы компилятор не откидывал якобы неиспользуемые вычисления
 */
internal fun Any.sendToBlackHole() {
    this.toString()
}

/**
 * Построить массив случайных лонгов
 */
internal fun buildRandomArray(
    size: Int,
): LongArray {
    return LongArray(size) {
        Random.nextLong(until = 1_000)
    }
}

typealias Matrix = Array<DoubleArray>

/**
 * Построить матрицу случайных даблов
 */
internal fun buildRandomMatrix(
    size: Int,
): Matrix {
    return Array(size) {
        DoubleArray(size) {
            Random.nextDouble()
        }
    }
}

/**
 * Построить матрицу
 */
internal fun buildMatrix(
    size: Int,
    calcElement: (row: Int, column: Int) -> Double,
): Matrix {
    return Array(size) { row ->
        DoubleArray(size) { column ->
            calcElement(row, column)
        }
    }
}
