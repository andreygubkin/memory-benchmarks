suspend fun main() {

    /**
     * План:
     * - true/false sharing
     * - data alignment
     * - matrix transpose
     */

    listOf(
        TrueSharing,
        FalseSharing,
        FalseSharingAtomic,
    ).forEach {
        it.run()
    }
}
