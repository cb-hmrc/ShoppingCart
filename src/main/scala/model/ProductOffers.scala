package model

/**
 * All product offers are defined here.
 */
object ProductOffers {

  /**
   * This function can be used to implement "buy one get one free", "three for the price of two", etc.
   */
  private def getNForThePriceOfMDiscount(n: Int, m: Int)(count: Int, price: BigDecimal) : BigDecimal = {
    val nDiscounts = count / n    // The discount can be applied for each whole "n" in the count for this product.
    price * nDiscounts
  }

  /**
   * Product offers can be looked up in this table, which returns a function that will calculate the discount.
   */
  private val offers = Map(
    Apple  -> getNForThePriceOfMDiscount(2, 1) _,
    Orange -> getNForThePriceOfMDiscount(3, 2) _
  )

  /**
   * Counts the number of each product in the list and applies the appropriate discount to each product.
   * If a product has no offer then no discount applies.
   */
  def calculateDiscount(products: List[Product]): BigDecimal = {
    val countOfEachProduct = products.groupBy(identity).mapValues(_.size)
    countOfEachProduct.toSeq.foldLeft(BigDecimal("0")) { (sofar, productAndCount) => {
      val product = productAndCount._1
      val count   = productAndCount._2
      val offer = offers.get(product)
      offer map {           // If there is an offer on this product then calculate the discount.
        discountFunction => sofar + discountFunction(count, product.price)
      } getOrElse {         // If there is no offer then there is no discount.
        sofar
      }
    } }
  }

}
