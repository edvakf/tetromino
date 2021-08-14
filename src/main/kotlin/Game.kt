class Game {
    val width: Int = 5
    val height: Int = 8
    val numPerBlock: Int = 2

    private fun getBlockOrientation(blockId: BlockId, orientationId: Int): BlockOrientation {
        return Block.of(blockId).orientations[orientationId]
    }

    fun findSolutions(): Int {
        val solutionBoards: MutableList<Board> = mutableListOf()

        // Breadth-First search all solutions
        findSolution(solutionBoards, Placements.Initial)

        val distinctSolutionBoards: MutableList<Board> = mutableListOf()
        for ((index, board) in solutionBoards.withIndex()) {
            var hasSymmetric = false
            for (i in index + 1 until solutionBoards.size) {
                if (board.equals(solutionBoards[i])) {
                    hasSymmetric = true

                    break
                }
            }
            if (!hasSymmetric) {
                distinctSolutionBoards.add(board)

                println("-----")
                println(board.toAscii())
            }
        }
        return distinctSolutionBoards.size
    }

    fun findSolution(solutionBoards: MutableList<Board>, placements: Placements) {
        val board = Board(width, height)
        val placedBlocks: MutableList<BlockId> = mutableListOf()

        // fill board and make list of placed blocks
        var pl = placements
        while (true) {
            when (pl) {
                is Placements.Initial -> {
                    break
                }
                is Placements.Next -> {
                    val orientation = getBlockOrientation(pl.blockId, pl.orientationId)
                    board.place(pl.position, orientation, pl.blockId)
                    placedBlocks.add(pl.blockId)
                    pl = pl.prev
                }
            }
        }

        val nextPosition = board.nextPosition()
        if (nextPosition == null) {
            solutionBoards.add(board)
            return
        }

        val blockIds = BlockId.all()
        assert(placedBlocks.size < numPerBlock * blockIds.size)

        // next block to try
        for (blockId in blockIds) {
            var appeared = 0
            for (id in placedBlocks) {
                if (id == blockId) {
                    appeared++
                }
            }
            assert(appeared <= numPerBlock) { "some type of block is placed too many times" }
            if (appeared < numPerBlock) {
                val orientations = Block.of(blockId).orientations
                for ((orientationId, orientation) in orientations.withIndex()) {
                    if (board.canPlace(nextPosition, orientation)) {
                        findSolution(
                            solutionBoards,
                            Placements.Next(blockId, orientationId, nextPosition, placements)
                        )
                    }
                }
            }
        }
    }
}

data class Position(val x: Int, val y: Int)

sealed class Placements {
    object Initial : Placements()
    data class Next(
        val blockId: BlockId,
        val orientationId: Int,
        val position: Position,
        val prev: Placements
    ) : Placements()
}