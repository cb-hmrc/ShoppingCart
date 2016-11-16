package model

import org.scalatest._

/**
 * Test the checkout calculations.
 */
class CheckoutSpec extends FlatSpec with Matchers {

  "An empty basket" should "cost zero" in {
    val products = List()
    val cost = Checkout.calculateCost(products)
    cost should be ( BigDecimal("0") )
  }

  "1 Apple" should "cost 0.60" in {
    val products = List(Apple)
    val cost = Checkout.calculateCost(products)
    cost should be ( BigDecimal("0.60") )
  }

  "1 Orange" should "cost 0.25" in {
    val products = List(Orange)
    val cost = Checkout.calculateCost(products)
    cost should be ( BigDecimal("0.25") )
  }

  "3 Apples and 1 Orange" should "cost 2.05" in {  // Test the given example.
  val products = List(Apple, Apple, Orange, Apple)
    val cost = Checkout.calculateCost(products)
    cost should be ( BigDecimal("2.05") )
  }

}
