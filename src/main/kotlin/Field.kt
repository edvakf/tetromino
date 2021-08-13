class Field {
    val tiles: IntArray = IntArray(5 * 8)

    fun toASCII(): String {
        return tiles
            .asIterable()
            .chunked(5) { row -> row.map{ it.toString() }.joinToString("") }
            .reversed()
            .joinToString("\n")
    }

}
