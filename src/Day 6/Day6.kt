import java.io.File
import java.io.InputStream

fun main() {
    val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 6/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    problem1day6(lineList)
    problem2day6(lineList)
}

fun problem1day6(input: List<String>) {
    var answers = mutableListOf<List<String>>()
    var answerCache = mutableListOf<String>()
    for (line in input) {
        if (line == "") {
            answers.add(answerCache)
            answerCache = mutableListOf()
        } else {
            answerCache.add(line)
        }
    }
    answers.add(answerCache)
    val answer = answers.map { answer ->
        answer.map { response ->
            response.split("")
        }.flatten().filter({ it != ""}).distinct().count()
    }.reduce { acc, num -> acc + num}
    println("Problem 1 -> Count: $answer")
}

fun problem2day6(input: List<String>) {
    var answers = mutableListOf<List<String>>()
    var answerCache = mutableListOf<String>()
    for (line in input) {
        if (line == "") {
            answers.add(answerCache)
            answerCache = mutableListOf()
        } else {
            answerCache.add(line)
        }
    }
    answers.add(answerCache)
    val answer = answers.map { answer ->
        answer.map { response ->
            response.split("").filter({ it != ""})
        }.reduce({ acc, list ->  acc.intersect(list).toList() }).count()
    }.reduce { acc, num -> acc + num}
//    answer.forEach { println(it) }
   println("Problem 2 -> $answer")
}