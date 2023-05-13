import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.Assumptions.assumingThat
import org.junit.jupiter.api.extension.*
import org.junit.jupiter.api.io.TempDir
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.io.File
import java.util.stream.Stream


class TestClassTest {

    @BeforeEach
    fun setUp() {
        println("setupだよ")
    }

    @AfterEach
    fun tearDown() {
        println("tearDownだよ")
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

    @Test
    fun `生成メソッドによるフィクスチャセットアップパターンを活用するテスト2`() {
        // 準備
        val expected = TestClassHelper
        // 検証
        assertThat(expected.別クラスでのStringオブジェクト生成()).isEqualTo("String")
    }


    //生成メソッドによるフィクスチャセットアップ
    fun Stringオブジェクト生成(): String {
        return "String"
    }

    //パラメータ化テスト
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun パラメータテストLessThan(n: Int) {
        assertThat(n).isLessThan(10)
    }

    // 同じ型の場合
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun パラメータテストGreaterThan(n: Int) {
        assertThat(n).isGreaterThan(0)
    }

    // 違う型の場合 a,0,b,1 となる
    @ParameterizedTest
    @CsvSource("a, 1", "b, 2", "c, 3")
    fun testWithMultipleValueSourcesForCSV(s: String, i: Int) {
        System.out.println(s)
        System.out.println(i)
    }

    // MethodSourceを使う場合staticメソッドにする必要があり、JavaからStaticとして呼び出すには@JvmStaticをつける必要がある
    companion object {
        @JvmStatic
        fun testProvider(): Stream<Arguments> {
            return Stream.of(
                arguments(1, "a"),
                arguments(2, "b"),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("testProvider")
    fun testWithMultipleValueSourcesForMethodSource(i: Int, s: String) {
        System.out.println(s)
        System.out.println(i)
    }

    // Assume(仮定,推定)を利用したテスト
    // AssumeTrueは、条件を満たしている場合に後続処理を続ける、満たさない場合はスキップする。
    // 早期リターンみたいなもの
    @Test
    fun testAssumeTrue() {
        assertThat(true).isTrue()
        assumeTrue(System.getProperties()["os.name"].toString().contains("Windows"))
        System.out.println("スキップされるよ")
        // スキップされるためエラーにならない
        assertThat(true).isFalse()
    }


    // assumingThat(仮定して)は、条件を満たした場合に第２引数の処理を実行し、後続処理はスキップされない。
    // if文みたいなもの
    @Test
    fun assumingThatを利用したテスト() {
        assumingThat(
            System.getProperties()["os.name"].toString().contains("Windows"),
            { System.out.println("スキップされるよ") })
        System.out.println("表示されるよ")
    }

    // ParameterizedTestと組み合わせる
    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 1, 2, 3])
    fun パラメータテストとassumeTrueの組み合わせ(n: Int) {
        // 0と-1をスキップし0より大きいテストを通す
        assumeTrue(n != 0 && n != -1)
        assertThat(n).isGreaterThan(0)
    }

    // 第２引数にエラーメッセージを渡す
    @Test
    fun assertTrueでのエラーメッセージ() {
        // 成功するよは出力されない
        assertTrue(true, "成功するよ！")
        //assertTrue(false, "失敗するよ！")
        //org.opentest4j.AssertionFailedError: 失敗するよ！ ==> expected: <true> but was: <false>
    }

    // ルール
    // JUnit5では@Ruleは廃止された
    // Extensionになったため、@ExtendWithを利用して拡張する
    class TestExtension : Extension, BeforeAllCallback, AfterAllCallback {
        override fun beforeAll(context: ExtensionContext?) {
            println("beforeAllのテスト！")
        }

        override fun afterAll(context: ExtensionContext?) {
            println("afterAllのテスト！")
        }
    }

    // この書き方だと拡張できない
    @ExtendWith(TestExtension::class)
    @Test
    fun test() {
        assertEquals(1, 1)
    }


    // クラスに対してだと拡張できる
    // beforeAllのテスト！afterAllのテスト！が表示されsetupやtearDownは表示されない
    @ExtendWith(TestExtension::class)
    class ExtendWithTest {
        @Test
        fun test() {
            assertEquals(1, 1)
        }
    }

    // JUnit5が自動的に初期化するのでlateinitにする
    // lateinitはプリミティブ型は使えない、var、non-nullになる
    @TempDir
    lateinit var anotherTempDir: File

    @Test
    fun TempDirのテスト() {
        val file = File(anotherTempDir, "test.txt")
        file.createNewFile()
        // ファイルはこんな感じで作られる
        // /var/folders/0w/_l7yyzkj53xfklc4z8t07hy80000gn/T/junit1505577895122092482/test.txt
        assertTrue(file.exists())
        // テスト終了後自動的に削除される
        // ls: /var/folders/0w/_l7yyzkj53xfklc4z8t07hy80000gn/T/junit1505577895122092482/test.txt: No such file or directory
    }

    // Timeuotのテスト
    @Test
    fun testTimeout() {
        // 5秒間スレッドを停止
//        Thread.sleep(TimeUnit.SECONDS.toMillis(5))

        // 次のアサーションは2秒以内に終わらないため失敗する
//        assertTimeout(ofSeconds(2)) {
//            Thread.sleep(TimeUnit.SECONDS.toMillis(5))
//        }

    }


}
