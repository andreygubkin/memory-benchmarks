private fun getDemos(): List<IDemo> = listOf(
    //TrueSharing,
    //FalseSharing,
    //FalseSharingAtomic,
    //TrueSharingAtomic,
    MultiplyMatrices,
    MultiplyMatricesWithTransposition,
)

suspend fun main() {
    getDemos()
        .forEach {
            it.run()
        }
}
