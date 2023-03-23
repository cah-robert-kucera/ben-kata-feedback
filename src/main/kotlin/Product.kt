
enum class Product(val cents: Int){
  COLA(100),
  CHIPS(50),
  CANDY(65);

    fun canBePurchased(balance: Int) = balance >= cents

    fun canBePurchased2(balance: Int): Boolean { return balance >= cents}

 }

