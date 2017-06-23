package org.money

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}


class MoneyLoggerAsMoneyOpsSpec extends AbstractMoneyOpsSpec("MoneyLogger")(new MoneyOpsLogger(new SimpleMoneyOps[Int]))
class LoggerMock extends Logger {
  var strings = List[String]();

  override def log(s: => String): Unit = strings = strings :+ s
}
class MoneyOpsLoggerSpec extends FlatSpec with Matchers {

  import MoneyLanguage._



  behavior of "MoneyLogger"

  it should "log additions" in {
    implicit val logger = new LoggerMock
    implicit val money = new MoneyOpsLogger(new SimpleMoneyOps[Int])

    1 add 2
    2 add 3

    logger.strings shouldBe List("1 + 2 => 3", "2 + 3 => 5")

  }

  it should "log splits" in {
    implicit val logger = new LoggerMock
    implicit val money = new MoneyOpsLogger(new SimpleMoneyOps[Int])

    1 split 2
    5 split 2

    logger.strings shouldBe List("1 split 2 ways => AmountAndRemainder(0,1)", "5 split 2 ways => AmountAndRemainder(2,1)")
  }

}
