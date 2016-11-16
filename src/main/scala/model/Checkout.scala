package model

/**
 * The checkout calculation is defined in this object.
 */
object Checkout {

  def calculateCost(products: List[Product]) : BigDecimal = {
    products.foldLeft(BigDecimal("0"))(
      (sofar, product) => sofar + product.price
    )
  }

}
