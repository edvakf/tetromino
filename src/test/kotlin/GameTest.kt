import org.junit.Test
import kotlin.test.assertEquals

class GameTest {
    @Test
    fun testFindSolutions() {
        val game = Game()
        assertEquals(783, game.findSolutions())
    }
}