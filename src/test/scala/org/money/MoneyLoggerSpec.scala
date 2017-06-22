package org.money

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FlatSpec, Matchers}


class MoneyLoggerAsMoneySpec extends AbstractMoneySpec("MoneyLogger")(new MoneyLogger(new SimpleMoney[Int]))

class MoneyLoggerSpec extends FlatSpec with Matchers {

  import MoneyLanguage._

  class LoggerMock extends Logger {
    var strings = List[String]();

    override def log(s: => String): Unit = strings = strings :+ s
  }

  behavior of "MoneyLogger"

  it should "log additions" in {
    implicit val logger = new LoggerMock
    implicit val money = new MoneyLogger(new SimpleMoney[Int])

    1 add 2
    2 add 3

    logger.strings shouldBe List("1 + 2 => 3", "2 + 3 => 5")

  }

  it should "log splits" in {
    implicit val logger = new LoggerMock
    implicit val money = new MoneyLogger(new SimpleMoney[Int])

    1 split 2
    5 split 2

    logger.strings shouldBe List("1 split 2 ways => AmountAndRemainder(0,1)", "5 split 2 ways => AmountAndRemainder(2,1)")
  }

}
