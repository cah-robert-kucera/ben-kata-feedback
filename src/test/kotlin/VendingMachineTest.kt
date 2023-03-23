import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class VendingMachineTest {
//A shared object means you could easily have state shared between tests, and that tests would not pass if run out of order.


  @Test
  fun `coin value does not increases when penny is inserted`() {

    val machine = VendingMachine()
    machine.acceptCoin(Coin.PENNY)

//    If your expected is a single value, you don't need to create an explanatory variable, and can just throw the value into your assert
    assertEquals(0, machine.balance)
  }

  @Test
  fun `coin value increases by 5 when nickle is inserted`() {

    //This line is now in all the tests, but unlike prod code, it can be _good_ to make tests WET (not dry). A good test should tell you everything it does at a glance, shouldn't require a ton of setup, so some repitition is fine.
    val machine = VendingMachine()
    machine.acceptCoin(Coin.NICKLE)

    assertEquals(5, machine.balance)
  }

  @Test
  fun `coin value increases by 10 when dime is inserted`() {
    val expected = 10

    val machine = VendingMachine()
    machine.acceptCoin(Coin.DIME)

    assertEquals(expected, machine.balance)
  }

  @Test
  fun `coin value increases by 25 when quarter is inserted`() {
    val expected = 25

    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER)

    assertEquals(expected, machine.balance)
  }

  @Test
  fun `coin value increases to 50 cents when given 1 quarter, 2 dimes and 1 nickle`() {
    val expected = 50

    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.DIME, Coin.DIME, Coin.NICKLE)

    assertEquals(expected, machine.balance)
  }

  @Test
  fun `display value returns INSERT COIN when no coins have been inserted`() {
    val expected = "INSERT COIN"
    val machine = VendingMachine()
    assertEquals(expected, machine.display)
  }

  @Test
  fun `display value returns $0,25 when a quarter is inserted`() {
    val expected = "$0.25"
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER)

    assertEquals(expected, machine.display)
  }

  @Test
  fun `display value returns $1,25 when a 5 quarters are inserted`() {
    val expected = "$1.25"
    val machine = VendingMachine()
    machine.acceptCoin(
        Coin.QUARTER,
        Coin.QUARTER,
        Coin.QUARTER,
        Coin.QUARTER,
        Coin.QUARTER,
    )

    assertEquals(expected, machine.display)
  }

  @Test
  fun `when penny is inserted it is returned to the user via the coin return slot`() {
    val expected = listOf(Coin.PENNY)
    val machine = VendingMachine()

    machine.acceptCoin(Coin.PENNY)

    assertEquals(expected, machine.coinReturn)
  }

  @Test
  fun `when a dime and two pennies are inserted the two pennies are returned to the user via the coin return slot`() {
    val expected = listOf(Coin.PENNY, Coin.PENNY)
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.PENNY, Coin.PENNY)

    assertEquals(expected, machine.coinReturn)
  }

  @Test
  fun `when cola is selected with enough money inserted it is dispensed`() {
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.QUARTER, Coin.QUARTER, Coin.QUARTER)

    val actual = machine.selectProduct(Product.COLA)

    //Having your expected inline also makes it easier to know what's expected as your eyes don't have to scan back up to find the variable reference. It's less to hold in our heads.
    assertEquals(Product.COLA, actual)
  }

  @Test
  fun `when chips is selected with enough money inserted it is dispensed`() {
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.QUARTER)
    val expected = Product.CHIPS

    val actual = machine.selectProduct(Product.CHIPS)

    assertEquals(expected, actual)
  }

  @Test
  fun `when candy is selected with enough money inserted it is dispensed`() {
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.QUARTER, Coin.DIME, Coin.NICKLE)
    val expected = Product.CANDY

    val actual = machine.selectProduct(Product.CANDY)

    assertEquals(expected, actual)
  }

  @Test
  fun `when cola is selected with insufficient money inserted no product is dispensed`() {
    val machine = VendingMachine()
    val actual = machine.selectProduct(Product.COLA)

    assertNull(actual)
  }

  @Test
  fun `when product is vended balance is reset`() {
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.QUARTER)
    val expected = 0

    machine.selectProduct(Product.CHIPS)

    assertEquals(expected, machine.balance)
  }

  @Test
  fun `when product is vended a thank you message is displayed`() {
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.QUARTER)
    val expected = "THANK YOU"

    machine.selectProduct(Product.CHIPS)

    //I think this was a neat/smart way to handle a side effect / do the display
    assertEquals(expected, machine.display)
  }

  @Test
  fun `when candy is selected with balance at 75 cents then 1 dime should be returned`() {
    val machine = VendingMachine()
    machine.acceptCoin(Coin.QUARTER, Coin.QUARTER, Coin.QUARTER)
    val expected = listOf(Coin.DIME)

    machine.selectProduct(Product.CANDY)

    assertEquals(expected, machine.coinReturn)
  }

  @Test
  fun `when candy is selected with balance at 115 cents then 2 quarters should be returned`() {
    val machine = VendingMachine()
    machine.acceptCoin(
        Coin.QUARTER,
        Coin.QUARTER,
        Coin.QUARTER,
        Coin.QUARTER,
        Coin.DIME,
        Coin.NICKLE)
    val expected = listOf(Coin.QUARTER, Coin.QUARTER)

    machine.selectProduct(Product.CANDY)

    assertEquals(expected, machine.coinReturn)
  }

  //I think your tests names are very nice. Clean and readable.
  @Test
  fun `when chips are selected with balance at 90 cents then 1 quarter, 1 dime, and 1 nickle should be returned`() {
    val machine = VendingMachine()
    machine.acceptCoin(
        Coin.QUARTER,
        Coin.NICKLE,
        Coin.DIME,
        Coin.DIME,
        Coin.DIME,
        Coin.DIME,
        Coin.DIME,
        Coin.DIME)
    val expected = listOf(Coin.QUARTER, Coin.DIME, Coin.NICKLE)

    machine.selectProduct(Product.CHIPS)

    assertEquals(expected, machine.coinReturn)
  }

  @Test
  fun `when return coins is pressed the coins entered by the customer are returned`() {
    val machine = VendingMachine()
    machine.acceptCoin(
      Coin.QUARTER,
      Coin.DIME,
      Coin.NICKLE)
    val expected = listOf(Coin.QUARTER, Coin.DIME, Coin.NICKLE)

  //Good job deciding what the 'interface' was. You didn't set up a web server or build a front end, but instead to make buttons just functions that can be called. (I've seen people try to do the other options; it was painful, lol)
    machine.returnCoins()

    assertEquals(expected, machine.coinReturn)
  }

  @Test
  fun `when return coins is pressed the display should say insert coin`() {
    val expected = "INSERT COIN"
    val machine = VendingMachine()

    machine.returnCoins()

    assertEquals(expected, machine.display)
  }
}
