inline fun sortByOneDigit(
    data: IntArray, result: IntArray, digit: Int.() -> Int,
) {
   val count = IntArray(1 shl 16)
   for (x in data) { count[x.digit()]++ }
   val position = count.runningFold(0, Int::plus).toIntArray()
   for (x in data) { result[position[x.digit()]++] = x }
}

fun sortPositiveIntegers(x : IntArray) {
   val temp = IntArray(x.size)
   sortByOneDigit(x, temp) { this shr 16 }
   sortByOneDigit(temp, x) { this and ((1 shl 16) - 1) }
}
