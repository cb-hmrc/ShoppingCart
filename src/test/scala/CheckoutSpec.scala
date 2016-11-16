package model

import org.scalatest._

/**
 * Test the checkout calculations.
 */
class CheckoutSpec extends FlatSpec with Matchers {

  "An empty basket" should "cost zero" in {
    val products = List()
    val cost = Checkout.calculateCostBeforeDiscount(products)
    cost should be ( BigDecimal("0") )
  }

  "1 Apple" should "cost 0.60" in {
    val products = List(Apple)
    val cost = Checkout.calculateCostBeforeDiscount(products)
    cost should be ( BigDecimal("0.60") )
  }

  "1 Orange" should "cost 0.25" in {
    val products = List(Orange)
    val cost = Checkout.calculateCostBeforeDiscount(products)
    cost should be ( BigDecimal("0.25") )
  }

  "3 Apples and 1 Orange" should "cost 2.05 before applying any discount" in {  // Test the given example.
  val products = List(Apple, Apple, Orange, Apple)
    val cost = Checkout.calculateCostBeforeDiscount(products)
    cost should be ( BigDecimal("2.05") )
  }

  "2 Apples after applying discount" should "cost the same as 1 Apple before discount" in {
    val products = List.fill(2)(Apple)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List(Apple)) )
  }

  "3 Apples after applying discount" should "cost the same as 2 Apples before discount" in {
    val products = List.fill(3)(Apple)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(2)(Apple)) )
  }

  "5 Apples after applying discount" should "cost the same as 3 Apples before discount" in {
    val products = List.fill(5)(Apple)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(3)(Apple)) )
  }

  "3 Oranges after applying discount" should "cost the same as 2 Oranges before discount" in {
    val products = List.fill(3)(Orange)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(2)(Orange)) )
  }

  "4 Oranges after applying discount" should "cost the same as 3 Oranges before discount" in {
    val products = List.fill(4)(Orange)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(3)(Orange)) )
  }

  "5 Oranges after applying discount" should "cost the same as 4 Oranges before discount" in {
    val products = List.fill(5)(Orange)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(4)(Orange)) )
  }

  "6 Oranges after applying discount" should "cost the same as 4 Oranges before discount" in {
    val products = List.fill(6)(Orange)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(4)(Orange)) )
  }

  // It is important to test a combination of discounts, so that we know that the fold in
  // ProductOffers.calculateDiscount is working correctly.
  "5 Apples and 6 Oranges after applying discount" should "cost the same as 3 Apples and 4 Oranges before discount" in {
    val products = List.fill(5)(Apple) ++ List.fill(6)(Orange)
    val cost = Checkout.calculateCostIncludingDiscount(products)
    cost should be ( Checkout.calculateCostBeforeDiscount(List.fill(3)(Apple) ++ List.fill(4)(Orange)) )
  }

}
