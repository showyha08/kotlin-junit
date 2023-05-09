import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class TestClassTest {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    val sut = TestClass()

    @Test
    fun multiplyで1と2の乗算結果である2が取得できる() {
        val expected = 2
        val actual = sut.multiply(1, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun multiplyで2と7の乗算結果である14が取得できる() {
        val expected = 14
        val actual = sut.multiply(2, 7)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun devideで4と2の除算結果が取得できる() {
        val expected = 2f
        val actual = sut.divide(4, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun divideで3と2の除算結果が取得できる() {
        val expected = 1.5f
        val actual = sut.divide(3, 2)
        assertThat(expected).isEqualTo(actual)
    }

    @Test()
    fun divideで0の除算でエラーを返す() {
        val error = assertThrows<Exception> {
            sut.divide(1, 0)
        }
        assertThat(error.message).isEqualTo("divide by zero.")
        assertThat(error.javaClass.name).isEqualTo("java.lang.IllegalArgumentException")
    }

    @Test
    fun `副作用のないメソッドのテスト`() {
        // 準備
        val expected = true
        // 実行
        val actual = sut.isEmptyOrNull("")
        // 検証
        assertEquals(expected, actual)
    }

    @Test
    fun `副作用のあるテスト`() {
        val sut = arrayListOf<Int>()
        sut.add(1)
        // 戻り値からはオブジェクトの状態がわからないため、間接的に確認するしかない
        assertThat(sut.size).isEqualTo(1)
        assertThat(sut.get(0)).isEqualTo(1)
    }

    @Test
    fun `生成メソッドによるフィクスチャセットアップパターンを活用するテスト`() {
        // 準備
        val expected = Stringオブジェクト生成()
        // 検証
        assertThat(expected).isEqualTo("String")
    }

    //生成メソッドによるフィクスチャセットアップ
    fun Stringオブジェクト生成(): String {
        return "String"
    }

}

