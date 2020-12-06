import java.io.File
import java.io.InputStream

fun main(args: Array<String>) {
    problem1()
    problem2()
}

fun problem1() {
    val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 1/expense_report.txt").inputStream()
    val lineList = mutableListOf<String>()

    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    var expensis = mutableListOf<Int>()
    for (num in lineList) {
        expensis.add(num.toInt())
    }
    var numberPair: Pair<Int, Int> = Pair(0, 0)
    for (i in expensis) {
        for (j in expensis) {
            if (i + j == 2020) { numberPair = Pair(i, j) }
        }
    }
    val (a, b) = numberPair
    println("Problem1")
    println("Pair: $a, $b, added: ${a + b}, multiplied: ${a * b}")
}

fun problem2() {
        val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 1/expense_report.txt").inputStream()
        val lineList = mutableListOf<String>()

        inputStream.bufferedReader().forEachLine { lineList.add(it) }
        var expensis = mutableListOf<Int>()
        for (num in lineList) {
            expensis.add(num.toInt())
        }
        var numberPair: Triple<Int, Int, Int> = Triple(0, 0, 0)
        for (i in expensis) {
            for (j in expensis) {
                for (k in expensis) {
                    if (i + j + k == 2020) { numberPair = Triple(i, j, k) }
                }
            }
        }
        val (a, b, c) = numberPair
        println("Problem2")
        println("Pair: $a, $b, $c added: ${a + b + c}, multiplied: ${a * b * c}")
}