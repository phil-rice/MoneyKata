package org.money

import java.math.BigInteger

import org.money.Logger.PrintlnLogger

object HelloWorld extends App {

  import MoneyLanguage._

  type M = BigInt

  val one: M = 1
  val two: M = 2
  val three: M = 3
  val oneHundredTrillion: M = new BigInteger("1000000000000000000000000")

  implicit val logger = PrintlnLogger
  val metrics = new MetricsForMoney(new MoneyLogger(new SimpleMoney[M]()))
  val bragger = new MoneyBragger[M](metrics)
  implicit val money: Money[M] = bragger

  println("Result is " + (one add two add three split 2))
  println("Result of big split is " + (oneHundredTrillion split 3))

  println("Metrics are" + metrics.copyOfMetrics)
  println(bragger)
}
