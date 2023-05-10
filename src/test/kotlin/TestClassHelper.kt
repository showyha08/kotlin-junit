import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach


class TestClassHelper {

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }


    val sut = TestClass()

    // staticメソッドがないのでCompanion Objectsを使う
    companion object {
        //生成メソッドによるフィクスチャセットアップ
        fun 別クラスでのStringオブジェクト生成(): String {
            return "String"
        }

        val BOOK = {
        }
    }

}

