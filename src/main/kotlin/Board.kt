class Board(private val width: Int, private val height: Int) {
    val tiles: IntArray = IntArray(width * height)

    fun equals(other: Board): Boolean {
        var equal = true
        loop@ for (y in 0 until height) {
            for (x in 0 until width) {
                equal = tiles[y * width + x] == other.tiles[y * width + x]
                if (!equal) {
                    break@loop
                }
            }
        }
        if (equal) {
            return true
        }

        // left-right flip
        equal = true
        loop@ for (y in 0 until height) {
            for (x in 0 until width) {
                equal = tiles[y * width + x] == other.tiles[y * width + width - x - 1]
                if (!equal) {
                    break@loop
                }
            }
        }
        if (equal) {
            return true
        }

        // up-down flip
        equal = true
        loop@ for (y in 0 until height) {
            for (x in 0 until width) {
                equal = tiles[y * width + x] == other.tiles[(height - y - 1) * width + x]
                if (!equal) {
                    break@loop
                }
            }
        }
        if (equal) {
            return true
        }

        // 180-degree rotation
        equal = true
        loop@ for (y in 0 until height) {
            for (x in 0 until width) {
                equal = tiles[y * width + x] == other.tiles[(height - y - 1) * width + width - x - 1]
                if (!equal) {
                    break@loop
                }
            }
        }
        return equal
    }

    fun toAscii(): String {
        return tiles
            .asIterable()
            .chunked(width) { row -> row.joinToString("") { it.toString() } }
            .joinToString("\n")
    }

    fun nextPosition(): Position? {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (isFilled(x, y)) {
                    // continue
                } else {
                    return Position(x, y)
                }
            }
        }
        return null
    }

    private fun fill(x: Int, y: Int, num: Int) {
        assert(x >= 0 && y >= 0 && x < width && y < height)
        tiles[y * width + x] = num
    }

    private fun isFilled(x: Int, y: Int): Boolean {
        assert(x >= 0 && y >= 0 && x < width && y < height)
        return tiles[y * width + x] > 0
    }

    fun canPlace(position: Position, orientation: BlockOrientation): Boolean {
        val (x, y) = position
        assert(x >= 0 && y >= 0 && x < width && y < height)

        val blockHeight = orientation.height
        val blockWidth = orientation.width

        if (y + blockHeight > height) {
            return false
        }

        val start = orientation.start()
        if (x - start < 0 || x - start + blockWidth > width) {
            return false
        }

        for (dx in 0 until blockWidth) {
            for (dy in 0 until blockHeight) {
                if (orientation.at(dx, dy) > 0 &&
                    isFilled(x - start + dx, y + dy)
                ) {
                    return false
                }
            }
        }
        return true
    }

    fun place(position: Position, orientation: BlockOrientation, blockId: BlockId) {
        // assume all blocks can be placed (i.e. canPlace check has passed)

        val (x, y) = position

        val blockHeight = orientation.height
        val blockWidth = orientation.width

        val start = orientation.start()

        for (dx in 0 until blockWidth) {
            for (dy in 0 until blockHeight) {
                if (orientation.at(dx, dy) > 0) {
                    fill(x - start + dx, y + dy, blockId.toInt())
                }
            }
        }
    }
}