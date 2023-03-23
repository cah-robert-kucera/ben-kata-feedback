//You can give an enum a value, so you don't need an interface. :)
enum class Coin(val cents: Int) {
    PENNY(1),
    NICKLE(5),
    DIME(10),
    QUARTER(25);

    fun isValid() = this != PENNY
}
