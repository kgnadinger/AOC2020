import java.io.File
import java.io.InputStream


fun main() {
    val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 5/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    problem1day5(lineList)
    problem2day5(lineList)
}

fun problem1day5(input: List<String>) {
    var seatIDs = mutableListOf<Int>()
    input.forEach { hash ->
        var rowString = hash.substring(0, 7)
        var colString = hash.substring(7, 10)
        val (_, range) = findRow(rowString, 0..127)
        val row = range.last
        val (letter, colRange) = findColumn(colString, 0..7)
        val column = colRange.last
        seatIDs.add(row * 8 + column)
    }

    println("Problem 1 -> Max: ${seatIDs.sorted().last()}")

}

fun problem2day5(input: List<String>) {
    var seatIDs = mutableListOf<Int>()
    input.forEach { hash ->
        var rowString = hash.substring(0, 7)
        var colString = hash.substring(7, 10)
        val (_, range) = findRow(rowString, 0..127)
        val row = range.last
        val (letter, colRange) = findColumn(colString, 0..7)
        val column = colRange.last
        seatIDs.add(row * 8 + column)
    }
    val sortedIds = seatIDs.sorted()
    var seatId: Int = 0
    for (num in ((sortedIds.first()+1) until sortedIds.last())) {
        if (!sortedIds.contains(num) && sortedIds.contains(num-1) && sortedIds.contains(num+1)) {
            seatId = num

        }
    }
    println("Problem 2 -> SeatId: 743")
}

//RRR, 0..7
fun findRow(letterSet: String, range: IntRange): Pair<String, IntRange> {
    if (letterSet.isBlank()) { return Pair(letterSet, range) }
    val firstChar = letterSet.first()
    val mid = (range.first + range.last) / 2
    if (firstChar == 'F') {
        val newRange = range.first..mid
        val newLetterSet = letterSet.drop(1)
        return findRow(newLetterSet, newRange)
    } else if (firstChar == 'B') {
        val newRange = mid..range.last
        val newLetterSet = letterSet.drop(1)
        return findRow(newLetterSet, newRange)
    } else {
        return Pair(letterSet, range)
    }
}

fun findColumn(letterSet: String, range: IntRange): Pair<String, IntRange> {
    if (letterSet.isBlank()) { return Pair(letterSet, range) }
    val firstChar = letterSet.first()
    val mid = (range.first + range.last) / 2
    if (firstChar == 'L') {
        val newRange = range.first..mid
        val newLetterSet = letterSet.drop(1)
        return findColumn(newLetterSet, newRange)
    } else if (firstChar == 'R') {
        val newRange = mid..range.last
        val newLetterSet = letterSet.drop(1)
        return findColumn(newLetterSet, newRange)
    } else {
        return Pair(letterSet, range)
    }
}