class Dsu(n: Int) {
   val p = IntArray(n) { it }

   fun root(a: Int): Int = 
       if (p[a] == a) a else root(p[a]).also { p[a] = it }

   fun join(a: Int, b: Int): Boolean {
       val ra = root(a)
       val rb = root(b)
       p[ra] = rb
       return ra == rb
   }
}

data class Edge(val from: Int, val to: Int, val weight: Int)

fun minimumSpanningTree(edges: List<Edge>): List<Edge> =
   with (Dsu(edges.maxOf { maxOf(it.from, it.to) } + 1)) {
       edges.sortedBy { it.weight }
           .filter { join(it.from, it.to) }
   }
