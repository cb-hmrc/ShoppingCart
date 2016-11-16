package controller

import model._

/**
 * Implement a rudimentary command line interface.
 * Can run it from sbt by: sbt "run item1 item2 ... itemN".
 */
object CommandLineInterface {

  def main(args: Array[String]): Unit = {
    val products = args.toList map { arg =>
      arg.toLowerCase match {       // There may be a neater way of doing this reverse mapping.
        case "apple" => Apple
        case "orange" => Orange
      }
    }
    println(s"Shopping Cart contains: ${products.map(_.name).mkString(", ")}")
    val cost = Checkout.calculateCost(products)
    println(s"Cost is £${cost}\n")
  }

}
