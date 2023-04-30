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
}