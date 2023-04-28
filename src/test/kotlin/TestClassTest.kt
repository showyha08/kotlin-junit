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

    @Test
    fun multiplyで1と2の乗算結果である2が取得できる() {
        val expected = 2
        val actual = TestClass().multiply(1, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun multiplyで2と7の乗算結果である14が取得できる() {
        val expected = 14
        val actual = TestClass().multiply(2, 7)
        assertThat(expected).isEqualTo(actual)
    }

}