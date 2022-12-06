fun main() {
    fun part1(input: List<String>): Int {
        return input.single().withIndex().windowed(4).first {
            it.map(IndexedValue<Char>::value).toSet().size == it.size
        }.last().index + 1
    }

    fun part2(input: List<String>): Int {
        return input.single().withIndex().windowed(14).first {
            it.map(IndexedValue<Char>::value).toSet().size == it.size
        }.last().index + 1
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
