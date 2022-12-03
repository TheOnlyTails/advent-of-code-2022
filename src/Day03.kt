fun Char.toPriority() = code - if (this > 'Z') {
    96
} else {
    38
}

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map {
                // split into each rucksack compartment
                it.substring(0 until (it.length / 2)) to it.substring(it.length / 2)
            }
            .map { (comp1, comp2) ->
                // find common item
                comp1.find { it in comp2 }!!
            }
            .sumOf(Char::toPriority)
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
            .map {
                it.fold(it.first().toSet()) { acc, chars ->
                    acc intersect chars.toSet()
                }.single()
            }
            .sumOf(Char::toPriority)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
