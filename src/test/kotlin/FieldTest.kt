import org.junit.Test
import kotlin.test.assertEquals

class FieldTest {
    @Test
    fun testFieldToASCII() {
        val field = Field()
        val result = field.toASCII()
        val expected =
            """
                00000
                00000
                00000
                00000
                00000
                00000
                00000
                00000
            """.trimIndent()
        assertEquals(expected, result)
    }
}