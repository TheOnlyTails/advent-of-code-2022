import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

fun List<String>.splitByEmptyLines(): List<List<String>> {
    var current = mutableListOf<String>()

    return fold(emptyList()) { result, line ->
        if (line.isBlank()) {
            val newList = result.plusElement(current)
            current = mutableListOf()
            newList
        } else {
            current.add(line)
            result
        }
    }
}
