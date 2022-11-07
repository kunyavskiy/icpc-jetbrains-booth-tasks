fun turtleWaysCount(n: Int, m: Int): Int {
   val moves = listOf(1 to 0, 0 to 1, 1 to 1)
   val dp = Array(n) { IntArray(m) }
   dp[0][0] = 1
   for (i in dp.indices) {
       for (j in dp[i].indices) {
           dp[i][j] = moves.sumOf { (di, dj) ->
               dp.getOrNull(i - di)?.getOrNull(j - dj) ?: 0
           }
       }
   }
   return dp.last().last()
}
