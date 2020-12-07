import java.io.File
import java.io.InputStream


fun main() {
    val inputStream: InputStream = File("/Users/kevingnadinger/Desktop/workspace/Advent of Code/src/Day 4/input.txt").inputStream()
    val lineList = mutableListOf<String>()
    inputStream.bufferedReader().forEachLine { lineList.add(it) }
    problem1day4(lineList)
    problem2day4(lineList)
}

fun problem1day4(input: List<String>) {
    var passports = mutableListOf<List<String>>()
    var passportCache = mutableListOf<String>()
    for (line in input) {
        if (line == "") {
            passports.add(passportCache)
            passportCache = mutableListOf()
        } else {
            passportCache.add(line)
        }
    }
    passports.add(passportCache)
    var splitPassports = passports.map { passport ->
        passport
                .map({ it.split(" ")})
                .flatten()
                .map({ field ->
                    val splitField = field.split(":")
                    Pair(splitField[0], splitField[1])
                })
    }
    var requiredKeys = listOf<String>("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var validPassports = splitPassports.filter { passport ->
        passport.map({ it.first }).containsAll(requiredKeys)
    }
    println("Count: ${validPassports.count()}")
}

fun problem2day4(input: List<String>) {
    var passports = mutableListOf<List<String>>()
    var passportCache = mutableListOf<String>()
    for (line in input) {
        if (line == "") {
            passports.add(passportCache)
            passportCache = mutableListOf()
        } else {
            passportCache.add(line)
        }
    }
    passports.add(passportCache)
    var splitPassports = passports.map { passport ->
        passport
                .map({ it.split(" ")})
                .flatten()
                .map({ field ->
                    val splitField = field.split(":")
                    Pair(splitField[0], splitField[1])
                })
    }
    var requiredKeys = listOf<String>("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var presentPassports = splitPassports.filter { passport ->
        passport.map({ it.first }).containsAll(requiredKeys)
    }
    var validPassports = presentPassports.filter { passport ->
        var isValid = true
        for ((key, value) in passport) {
            when (key) {
                "byr" -> isValid = (value.length == 4) && (value.toInt() >= 1920 && value.toInt() <= 2002)
                "iyr" -> isValid = (value.length == 4) && (value.toInt() >= 2010 && value.toInt() <= 2020)
                "eyr" -> isValid = (value.length == 4) && (value.toInt() >= 2020 && value.toInt() <= 2030)
                "hgt" -> {
                    if (value.contains("cm")) {
                        val splitHeight = value.split("cm").filter { it != "" }
                        val height = splitHeight.first().toInt()
                        isValid = (height >= 150 && height <= 193)
                    } else if (value.contains("in")) {
                        val splitHeight = value.split("in").filter { it != "" }
                        val height = splitHeight.first().toInt()
                        isValid = (height >= 59 && height <= 76)
                    } else {
                        isValid = false
                    }
                }
                "hcl" -> {
                    val splitColor = value.split("").filter { it != "" }
                    val validCharacters = (0..9).toList().map({ it.toString() }) + ('a'..'f').toList().map({ it.toString() })
                    if ((splitColor.first() == "#") && (splitColor.count() == 7)) {
                        val charsToTest = splitColor.drop(1)
                        isValid = validCharacters.containsAll(charsToTest)
                    } else {
                        isValid = false
                    }
                }
                "ecl" -> isValid = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
                "pid" -> isValid = (value.length == 9) && (value.toIntOrNull() != null)
            }
            if (!isValid) {
                break
            }
        }
        isValid
    }
    validPassports.forEach { println(it) }
    println("Problem 2 Count: ${validPassports.count()}")
}