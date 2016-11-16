package model

/**
 * Definition of all products and their prices.
 */
sealed case class Product(name: String, price: BigDecimal)

object Apple extends Product("Apple", BigDecimal("0.60"))
object Orange extends Product("Orange", BigDecimal("0.25"))
