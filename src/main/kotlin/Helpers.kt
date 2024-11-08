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
internal fun buildArray(
    @Suppress("SameParameterValue") size: Int,
): LongArray {
    return LongArray(size) {
        Random.nextLong(until = 1_000)
    }
}

