import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BoardTest {
    @Test
    fun testCanPlace() {
        val board = Board(3, 3)
        val orientation = BlockT.orientations[0]
        assertTrue(board.canPlace(Position(0, 0), orientation))
    }

    @Test
    fun testCanPlace2() {
        val board = Board(3, 3)
        val orientation = BlockT.orientations[0]
        assertFalse(board.canPlace(Position(1, 0), orientation))
    }

    @Test
    fun testPlace() {
        val board = Board(3, 3)
        val orientation = BlockT.orientations[0]
        assertTrue(board.canPlace(Position(0, 0), orientation))
        board.place(Position(0, 0), orientation, BlockId.T)
        val actual = board.toAscii()
        val expected = """
            222
            020
            000
        """.trimIndent()
        assertEquals(expected, actual)
    }

    @Test
    fun testPlace2() {
        val board = Board(3, 3)
        val orientation = BlockT.orientations[0]
        assertTrue(board.canPlace(Position(0, 1), orientation))
        board.place(Position(0, 1), orientation, BlockId.T)
        val actual = board.toAscii()
        val expected = """
            000
            222
            020
        """.trimIndent()
        assertEquals(expected, actual)
    }

    @Test
    fun testPlace3() {
        val board = Board(3, 3)
        val orientation1 = BlockL.orientations[2]
        assertTrue(board.canPlace(Position(0, 0), orientation1))
        board.place(Position(0, 0), orientation1, BlockId.L)

        val orientation2 = BlockT.orientations[2]
        assertTrue(board.canPlace(Position(1, 1), orientation2))
        board.place(Position(1, 1), orientation2, BlockId.T)

        val actual = board.toAscii()
        val expected = """
            444
            420
            222
        """.trimIndent()
        assertEquals(expected, actual)
    }
}