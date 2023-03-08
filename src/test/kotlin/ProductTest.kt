import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

//I'm wrestling with if this and the coin test are valuable on their own or if they could / should be tested as part of the vending machine test. I'm not sure either way.
internal class ProductTest {
  @Test
  fun `coins have appropriate values associated with them when used`() {
    val expectedMap = mapOf("COLA" to 100, "CHIPS" to 50, "CANDY" to 65)

    assertEquals(expectedMap.keys.size, Coin.values().size)
    Product.values().forEach { product ->
      val expected = expectedMap[product.name]

      assertEquals(expected, product.cents)
    }
  }
}
