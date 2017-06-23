package org.money

import scala.reflect.ClassTag

object HelloWorld extends App {

  import MoneyLanguage._


  def demo[M: MoneyOps:ClassTag](one: M, two: M, three: M): Unit = {
    println(s"Result for ${implicitly[ClassTag[M]].runtimeClass} is " + (one add two add three split 2))
  }

  implicit val moneyOpsForInt= new SimpleMoneyOps[Int]
  implicit val moneyOpsForBigInt = new SimpleMoneyOps[BigInt]
  demo[Int](1, 2, 3)
  demo[BigInt](1, 2, 3)

  //  implicit val logger = PrintlnLogger

  //  val bragger = new MoneyOpsBragger[M](metrics)

  //  val oneGadzillion: M = new BigInteger("100000000000000000000000000000")
  //  println("Result of big split is " + (oneGadzillion split 3))
  //
  //  println("Metrics are" + metrics.copyOfMetrics)
  //  println(bragger)
  //
  //  one split 0
}
