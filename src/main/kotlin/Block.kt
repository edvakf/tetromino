sealed class Block(val orientations: Array<BlockOrientation>) {
    companion object {
        fun of(id: BlockId): Block {
            return when (id) {
                is BlockId.I -> BlockI
                is BlockId.T -> BlockT
                is BlockId.S -> BlockS
                is BlockId.L -> BlockL
                is BlockId.O -> BlockO
            }
        }
    }
}

object BlockI : Block(
    arrayOf(
        BlockOrientation(1, 4, intArrayOf(1, 1, 1, 1)),
        BlockOrientation(4, 1, intArrayOf(1, 1, 1, 1)),
    )
)

object BlockT : Block(
    arrayOf(
        BlockOrientation(3, 2, intArrayOf(1, 1, 1, 0, 1, 0)),
        BlockOrientation(2, 3, intArrayOf(1, 0, 1, 1, 1, 0)),
        BlockOrientation(3, 2, intArrayOf(0, 1, 0, 1, 1, 1)),
        BlockOrientation(2, 3, intArrayOf(0, 1, 1, 1, 0, 1)),
    )
)

object BlockS : Block(
    arrayOf(
        BlockOrientation(3, 2, intArrayOf(0, 1, 1, 1, 1, 0)),
        BlockOrientation(2, 3, intArrayOf(1, 0, 1, 1, 0, 1)),
        BlockOrientation(3, 2, intArrayOf(1, 1, 0, 0, 1, 1)),
        BlockOrientation(2, 3, intArrayOf(0, 1, 1, 1, 1, 0)),
    )
)

object BlockL : Block(
    arrayOf(
        BlockOrientation(3, 2, intArrayOf(0, 0, 1, 1, 1, 1)),
        BlockOrientation(2, 3, intArrayOf(1, 1, 0, 1, 0, 1)),
        BlockOrientation(3, 2, intArrayOf(1, 1, 1, 1, 0, 0)),
        BlockOrientation(3, 2, intArrayOf(1, 1, 1, 0, 0, 1)),
        BlockOrientation(2, 3, intArrayOf(1, 1, 1, 0, 1, 0)),
        BlockOrientation(3, 2, intArrayOf(1, 0, 0, 1, 1, 1)),
        BlockOrientation(2, 3, intArrayOf(0, 1, 0, 1, 1, 1)),
    )
)

object BlockO : Block(
    arrayOf(
        BlockOrientation(2, 2, intArrayOf(1, 1, 1, 1)),
    )
)

class BlockOrientation(val width: Int, val height: Int, private val tiles: IntArray) {
    fun at(x: Int, y: Int): Int {
        assert(x >= 0 && y >= 0 && x < width && y < height)
        return tiles[y * width + x]
    }

    fun start(): Int {
        for (x in 0 until width) {
            if (tiles[x] > 0) { // tiles[x] is the xth column on the 0th row
                return x
            }
        }
        assert(false) { "unreachable" }
        return 0
    }
}


sealed class BlockId(private val id: Int) {
    object I : BlockId(1)
    object T : BlockId(2)
    object S : BlockId(3)
    object L : BlockId(4)
    object O : BlockId(5)

    fun toInt(): Int {
        return id
    }

    companion object {
        fun all(): Array<BlockId> {
            return arrayOf(I, T, S, L, O)
        }
    }
}