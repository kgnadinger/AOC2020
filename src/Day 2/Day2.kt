import java.io.File
import java.io.InputStream


fun main() {
    val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 2/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    problem1(lineList)
    problem2(lineList)
}

fun problem1(input: List<String>) {
    var goodPasswordCount = 0
    for (line in input) {
        var passwordArguments = line.split(" ")

        val rules = passwordArguments.first().split("-")
        val (min, max) = Pair<Int, Int>(rules.first().toInt(), rules.last().toInt())

        val letter = passwordArguments[1].first().toString()
        val password = passwordArguments.last().split("").filter { char -> char != "" }
        val count = password.filter({ char -> char == letter}).count()
        if (count in min..max) { goodPasswordCount += 1 }
//        println("\"min: $min, max: $max\", Letter: $letter, password: $password, count: $count")
    }
    println("Count: $goodPasswordCount")
}

fun problem2(input: List<String>) {
    var goodPasswordCount = 0
    for (line in input) {
        var passwordArguments = line.split(" ")

        val rules = passwordArguments.first().split("-")
        val (first, second) = Pair<Int, Int>(rules.first().toInt() - 1, rules.last().toInt() - 1)

        val letter = passwordArguments[1].first().toString()
        val password = passwordArguments.last().split("").filter { char -> char != "" }
//        if (second >= password.count()) { break }
        var positionFoundLetter = Pair(false, false)
        if (password[first] == letter) {
            positionFoundLetter = Pair(true, false)
        }
        if (password[second] == letter) {
            val firstBool = positionFoundLetter.first
            positionFoundLetter = Pair(firstBool, true)
        }
        // true, true

        if (positionFoundLetter.first != positionFoundLetter.second) { goodPasswordCount += 1 }
        println("first: $first, second: $second, Letter: $letter, password: $password")
    }
    print("Count: $goodPasswordCount")
}