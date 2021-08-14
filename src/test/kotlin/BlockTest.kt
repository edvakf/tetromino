import org.junit.Test
import kotlin.test.assertEquals

class BlockTest {
    @Test
    fun testOrientations() {
        val bI = BlockI
        val orientations = bI.orientations
        assertEquals(1, orientations[0].width)
        assertEquals(4, orientations[0].height)
        assertEquals(4, orientations[1].width)
        assertEquals(1, orientations[1].height)

        assertEquals(1, orientations[0].at(0,0))
        assertEquals(1, orientations[0].at(0,1))
        assertEquals(1, orientations[0].at(0,2))
        assertEquals(1, orientations[0].at(0,3))
    }

    @Test
    fun testStart() {
        assertEquals(0, BlockI.orientations[0].start())
        assertEquals(0, BlockT.orientations[0].start())
        assertEquals(1, BlockS.orientations[0].start())
        assertEquals(2, BlockL.orientations[0].start())
        assertEquals(0, BlockO.orientations[0].start())
    }
}