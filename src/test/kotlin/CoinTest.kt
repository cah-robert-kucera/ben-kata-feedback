
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CoinTest {
  @Test
  fun `coins have appropriate values associated with them when used`() {
    val expectedMap = mapOf("PENNY" to 1, "NICKLE" to 5, "DIME" to 10, "QUARTER" to 25)

    assertEquals(expectedMap.keys.size, Coin.values().size)
    Coin.values().forEach { coin ->
      val expectedCoinValue = expectedMap[coin.name]

      assertEquals(expectedCoinValue, coin.cents)
    }
  }
}
