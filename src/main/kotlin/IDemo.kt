interface IDemo {
    val title: String
    suspend fun execute()

    suspend fun run() {
        println(title)
        execute()
    }
}
