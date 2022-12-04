operator fun IntRange.contains(other: IntRange) =
    other.first in this && other.last in this

infix fun IntRange.overlaps(other: IntRange) =
    other.first in this || other.last in this

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map {
                    val (range1, range2) = it.split(",")
                range1 to range2
            }
            .map { (range1, range2) ->
                val (start1, end1) = range1.split("-").map(String::toInt)
                val (start2, end2) = range2.split("-").map(String::toInt)
                start1..end1 to start2..end2
            }
            .count { (range1, range2) ->
                range1 in range2 || range2 in range1
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map {
                val (range1, range2) = it.split(",")
                range1 to range2
            }
            .map { (range1, range2) ->
                val (start1, end1) = range1.split("-").map(String::toInt)
                val (start2, end2) = range2.split("-").map(String::toInt)
                start1..end1 to start2..end2
            }
            .count { (range1, range2) ->
                range1 overlaps  range2 || range2 overlaps range1
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
