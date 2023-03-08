class VendingMachine {
    private var coins: ArrayList<Coin> = arrayListOf()
    private val inventory: Map<Product, Int> =
        mapOf(Pair(Product.COLA, 1), Pair(Product.CHIPS, 1), Pair(Product.CANDY, 1))
    private var justVended = false

    //fancy use of the getter as well as using the sumof function
    val balance: Int
        get() = coins.sumOf { it.cents }

    //Looks like you don't need a setter.
    //I think it might be cleaner in this case to just make `getDisplayMessage` a public function
    val display: String
        get() = getDisplayMessage()

    //Could we remove this shared state by making the relevant functions just return the list of coins?
    var coinReturn: ArrayList<Coin> = arrayListOf()

    fun acceptCoin(vararg coinsToInsert: Coin) {
        val (valid, invalid) = coinsToInsert.partition { it.isValid() }
        coins.addAll(valid)
        coinReturn.addAll(invalid)
    }

    fun selectProduct(product: Product): Product? {
        //I like how you're using these inline if statements
        return if (product.canBePurchased(balance)) vend(product) else null

        //if vend were a pure function, you could do something like this, but as is it would call the side effects, which you don't want
        //vend(product).takeIf { canAffordProduct(product) }
    }

    fun returnCoins() {
        coinReturn.addAll(coins)
        coins = arrayListOf()
    }

    private fun getDisplayMessage(): String {
        return when {
            coins.isNotEmpty() -> formatBalanceDisplay()
            justVended -> {
                justVended = false
                "THANK YOU"
            }

            else -> "INSERT COIN"

        }
    }

    private fun vend(product: Product): Product {
        makeChange(product)
        coins = arrayListOf()
        justVended = true
        return product
    }

    private fun makeChange(product: Product) {
        var delta = balance - product.cents

        //I remember this being tricky to figure out
        while (delta > 0) {
            val coin = getLargestPossibleCoin(delta)
            coinReturn.add(coin)
            delta -= coin.cents
        }
    }

    private fun getLargestPossibleCoin(delta: Int): Coin {
        return when {
            delta >= 25 -> Coin.QUARTER
            delta >= 10 -> Coin.DIME
            delta >= 5 -> Coin.NICKLE
            else -> Coin.PENNY
        }
    }

    private fun formatBalanceDisplay(): String {
        val dollars = balance / 100
        val cents = balance % 100
        return "$$dollars.$cents"
    }

}
