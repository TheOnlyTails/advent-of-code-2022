fun getStackStringIndex(stackIndex: Int) = 4 * stackIndex + 1

fun parsing(input: List<String>): Pair<List<List<Int>>, MutableList<MutableList<Char>>> {
    val (instructions, rawStacks) = input
        .filter(String::isNotBlank)
        .partition { it.startsWith("move") }

    val stackCount = rawStacks.last().last().toString().toInt()

    val stacks = (0 until stackCount)
        .map {
            rawStacks.dropLast(1)
                .mapNotNull { l ->
                    l.getOrNull(getStackStringIndex(it))
                }
                .filter { it != ' ' }
                .toMutableList()
        }
        .toMutableList()

    return instructions.map { inst ->
        inst.filter { it.isDigit() || it.isWhitespace() }
            .trim()
            .split(" ")
            .filter(String::isNotBlank)
            .map(String::toInt)
    } to stacks
}

fun main() {
    fun part1(input: List<String>): String {
        val (instructions, stacks) = parsing(input)

        for ((amount, from, to) in instructions) {
            for (i in 0 until amount) {
                stacks[to - 1].add(0, stacks[from - 1].removeFirst())
            }
        }

        return stacks.joinToString("") { it.first().toString() }
    }

    fun part2(input: List<String>): String {
        val (instructions, stacks) = parsing(input)

        for ((amount, from, to) in instructions) {
            val cache = mutableListOf<Char>()
            for (i in 0 until amount) {
                cache += stacks[from - 1].removeFirst()
            }
            stacks[to - 1].addAll(0, cache)
        }

        return stacks.joinToString("") { it.first().toString() }
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
