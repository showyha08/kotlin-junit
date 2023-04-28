import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TestClassTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    val tc = TestClass()

    @Test
    fun multiplyで1と2の乗算結果である2が取得できる() {
        val expected = 2
        val actual = tc.multiply(1, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun multiplyで2と7の乗算結果である14が取得できる() {
        val expected = 14
        val actual = tc.multiply(2, 7)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun devideで4と2の除算結果が取得できる() {
        val expected = 2
        val actual = tc.devide(4, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun devideで3と2の除算結果が取得できる() {
        val expected = 1.5
        val actual = tc.devide(3, 2)
        assertThat(expected).isEqualTo(actual)
    }


}