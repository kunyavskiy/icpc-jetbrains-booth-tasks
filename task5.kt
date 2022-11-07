fun commaSprinklerFromDressRehearsal(words: List<String>) {
   fun String.clean() = trimEnd('.', ',')
   val distinctWords = words.map { it.clean() }.distinct()
   val id = distinctWords.indices.associateBy { distinctWords[it] }
   val wordId = IntArray(words.size) { id[words[it].clean()]!! }
   val positions = wordId.indices.groupBy { wordId[it] }
       .let { g -> List(distinctWords.size) { g[it]!!.toIntArray() } }
   val comma = Array(2) { BooleanArray(distinctWords.size) }
   fun process(id: Int, before: Int) {
       if (comma[before][id]) return
       comma[before][id] = true
       for (i in positions[id]) {
           val neighbour = if (before == 1) i - 1 else i + 1
           val other = wordId.getOrNull(neighbour) ?: continue
           if (!words[neighbour].endsWith('.')) { 
               process(other,1 - before)
           }
       }
   }
   for (i in distinctWords.indices) {
       if (positions[i].any { words[it].endsWith(",") }) process(i,0)
       if (positions[i].any { it > 0 && words[it - 1].endsWith(",") })
           process(i,1)
   }
   fun String.needComma() = 
       !endsWith('.') && !endsWith(',') && comma[0][id[this]!!]
   println(words.joinToString(" ") { if (it.needComma()) "$it," else it })
}
