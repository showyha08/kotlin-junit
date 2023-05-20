class TestClass {
    fun multiply(x: Int, y: Int): Int {
        return x * y
    }

    fun divide(x: Int, y: Int): Float {
        if (y == 0) throw IllegalArgumentException("divide by zero.")
        return x.toFloat() / y.toFloat()
    }

    fun isEmptyOrNull(value: String?): Boolean {
        return (value === null || value.isEmpty())
    }

    // 委譲サンプル
    interface DelegationBase {
        fun print()
    }

    class BaseImpl : DelegationBase {
        override fun print() {
            print("override")
        }

    }

    class Derived(b: DelegationBase) : DelegationBase by b

    fun delegationExec() {
        val baseImpl = BaseImpl()
        val derived = Derived(baseImpl)

        baseImpl.print()
        derived.print()
    }

}