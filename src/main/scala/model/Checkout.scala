package model

/**
 * The checkout calculation is defined in this object.
 */
object Checkout {

  def calculateCostBeforeDiscount(products: List[Product]) : BigDecimal = {
    products.foldLeft(BigDecimal("0"))(
      (sofar, product) => sofar + product.price
    )
  }

  def calculateCostIncludingDiscount(products: List[Product]) : BigDecimal = {
    calculateCostBeforeDiscount(products) - ProductOffers.calculateDiscount(products)
  }

}
