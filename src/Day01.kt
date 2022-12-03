fun main() {
    fun part1(input: List<String>): Int {
        return input.splitByEmptyLines().maxOf { it.map(String::toInt).sum() }
    }

    fun part2(input: List<String>): Int {
        return input.splitByEmptyLines()
            .map { it.map(String::toInt).sum() }
            .sortedDescending()
            .also { println(it) }
            .take(3)
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
