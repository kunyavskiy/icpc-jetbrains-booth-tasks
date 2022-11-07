fun countCliques(graph: List<List<Int>>): Int {
   require(graph.size <= 32)
   val neighbourMasks = graph.map { row -> row.sumOf { 1 shl it } }
   val cache = mutableMapOf(0 to 1)
   fun calc(mask: Int): Int = cache.getOrPut(mask) {
       val last = mask.takeLowestOneBit()
       calc(mask xor (1 shl last)) + calc(mask and neighbourMasks[last])
   }
   return calc((1 shl graph.size) - 1)
}
