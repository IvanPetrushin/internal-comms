import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SimpleTests {
    @Test
    fun sqr() {
        Assertions.assertEquals(0, sqr(0))
        Assertions.assertEquals(4, sqr(2))
        Assertions.assertEquals(9, sqr(-3))
    }
}