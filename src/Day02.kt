private enum class RockPaperScissors(val play: Char, val response: Char, val score: Int) {
    Rock('A', 'X', 1),
    Paper('B', 'Y', 2),
    Scissors('C', 'Z', 3);

    companion object {
        val winners = mapOf(
            Rock to Paper,
            Paper to Scissors,
            Scissors to Rock,
        )
    }
}

private enum class RoundResult(val c: Char) {
    Lose('X'),
    Draw('Y'),
    Win('Z')
}

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map {
                val (play, response) = it.split(" ")
                play to response
            }
            .map { (play, response) ->
                RockPaperScissors.values().find { it.play == play.single() }!! to RockPaperScissors.values()
                    .find { it.response == response.single() }!!
            }.sumOf { (play, response) ->
                val drawScore = if (play == response) 3 else 0
                val winnerScore = if (RockPaperScissors.winners[play] == response) 6 else 0
                response.score + drawScore + winnerScore
            }
    }

    fun part2(input: List<String>): Int {
        return input
            .map {
                val (play, response) = it.split(" ")
                play to response
            }
            .map { (play, response) ->
                val enumPlay = RockPaperScissors.values().find { it.play == play.single() }!!
                val correspondingResponse = when (RoundResult.values().find { it.c == response.single() }!!) {
                    RoundResult.Lose -> RockPaperScissors.winners[RockPaperScissors.winners[enumPlay]]!!
                    RoundResult.Draw -> enumPlay
                    RoundResult.Win -> RockPaperScissors.winners[enumPlay]!!
                }

                enumPlay to correspondingResponse
            }.sumOf { (play, response) ->
                val drawScore = if (play == response) 3 else 0
                val winnerScore = if (RockPaperScissors.winners[play] == response) 6 else 0
                response.score + drawScore + winnerScore
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
