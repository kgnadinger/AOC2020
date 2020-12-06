import java.io.File
import java.io.InputStream


fun main() {
    val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 3/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    problem1day3(lineList)
    problem2day3(lineList)
}

fun numberOfTrees(map: List<List<String>>, x_bar: Int, y_bar: Int): Int {
    val columnCount = map.first().count()
    var numberOfTrees = 0
    for (position in 0..map.count()-1) {
        val (x, y) = Pair(position * x_bar % columnCount, position * y_bar)
        println("x: $x, y: $y")
        if (x > map.count() || y > map.count()) { break }
        if (map[y][x] == "#") { numberOfTrees += 1 }
    }
    return numberOfTrees
}

fun problem1day3(input: List<String>) {
    var map = mutableListOf<List<String>>()
    input.forEach { line ->
        map.add(line.split("").filter({ it != "" }))
    }
    map.forEach { println(it) }
    var count = numberOfTrees(map, 3, 1)
    println("Problem 1 Count: $count")
}

fun problem2day3(input: List<String>) {
    var map = mutableListOf<List<String>>()
    input.forEach { line ->
        map.add(line.split("").filter({ it != "" }))
    }
    map.forEach { println(it) }
    var slopes = listOf<Pair<Int, Int>>(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))
    var counts = mutableListOf<Int>()
    for (slope in slopes) {
        counts.add(numberOfTrees(map, slope.first, slope.second))
    }
    val multiplied: Long = counts.map({it.toLong()}).reduce({ acc: Long, count: Long -> acc * count})
    println("Problem 2 Count: $counts, Multiplied $multiplied")

}