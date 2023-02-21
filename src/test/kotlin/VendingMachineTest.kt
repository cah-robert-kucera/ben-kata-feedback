import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class VendingMachineTest {
  lateinit var subject: VendingMachine

  @BeforeEach
  fun setUp() {
    subject = VendingMachine()
  }

  @Test
  fun `coin value does not increases when penny is inserted`() {
    val expected = 0

    subject.acceptCoin(CoinTypes.PENNY)

    assertEquals(expected, subject.balance)
  }

  @Test
  fun `coin value increases by 5 when nickle is inserted`() {
    val expected = 5

    subject.acceptCoin(CoinTypes.NICKLE)

    assertEquals(expected, subject.balance)
  }

  @Test
  fun `coin value increases by 10 when dime is inserted`() {
    val expected = 10

    subject.acceptCoin(CoinTypes.DIME)

    assertEquals(expected, subject.balance)
  }

  @Test
  fun `coin value increases by 25 when quarter is inserted`() {
    val expected = 25

    subject.acceptCoin(CoinTypes.QUARTER)

    assertEquals(expected, subject.balance)
  }

  @Test
  fun `coin value increases to 50 cents when given 1 quarter, 2 dimes and 1 nickle`() {
    val expected = 50

    subject.acceptCoin(CoinTypes.QUARTER)
    subject.acceptCoin(CoinTypes.DIME)
    subject.acceptCoin(CoinTypes.DIME)
    subject.acceptCoin(CoinTypes.NICKLE)

    assertEquals(expected, subject.balance)
  }

  @Test
  fun `display value returns INSERT COIN when no coins have been inserted`() {
    val expected = "INSERT COIN"

    assertEquals(expected, subject.display)
  }

  @Test
  fun `display value returns $0,25 when a quarter is inserted`() {
    val expected = "$0.25"

    subject.acceptCoin(CoinTypes.QUARTER)

    assertEquals(expected, subject.display)
  }

  @Test
  fun `display value returns $1,25 when a 5 quarters are inserted`() {
    val expected = "$1.25"

    subject.acceptCoin(CoinTypes.QUARTER)
    subject.acceptCoin(CoinTypes.QUARTER)
    subject.acceptCoin(CoinTypes.QUARTER)
    subject.acceptCoin(CoinTypes.QUARTER)
    subject.acceptCoin(CoinTypes.QUARTER)

    assertEquals(expected, subject.display)
  }

  @Test
  fun `when penny is inserted it is returned to the user via the coin return slot`() {
    val expected = listOf(CoinTypes.PENNY)

    subject.acceptCoin(CoinTypes.PENNY)

    assertEquals(expected, subject.coinReturn)
  }
}