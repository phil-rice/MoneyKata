package org.money

import org.scalatest.{FlatSpec, Matchers}
import MoneyLanguage._
import org.scalamock.scalatest.MockFactory

class MoneyExceptionHandlerAsMoneySpec extends AbstractMoneySpec("MoneyExceptionHandler")(new MoneyExceptionHandler(new SimpleMoney[Int]))

class MoneyExceptionHandlerSpec extends FlatSpec with Matchers with MockFactory {

  behavior of "MoneyExceptionHandler"

  def withMocks(fn: Money[Int] => Money[Int] => LoggerMock => Unit) = {
    val simpleMoney = mock[Money[Int]]
    implicit val logger = new LoggerMock
    val money = new MoneyExceptionHandler(simpleMoney)
    fn(money)(simpleMoney)(logger)
  }

  it should "log exceptions caused by add" in {
    val expection = new RuntimeException("someMessage")
    withMocks { implicit money =>
      moneyMock =>
        logger =>
          (moneyMock.add _).expects(1, 2).throwing(expection)
          intercept[RuntimeException](1 add 2) shouldBe expection
      logger.strings shouldBe List("1 and 2 caused an Exception java.lang.RuntimeException: someMessage")
    }
  }
  it should "log exceptions caused by split" in {
    val expection = new RuntimeException("someMessage")
    withMocks { implicit money =>
      moneyMock =>
        logger =>
          (moneyMock.split _).expects(1, 2).throwing(expection)
          intercept[RuntimeException](1 split 2) shouldBe expection
      logger.strings shouldBe List("1 split 2 caused an Exception java.lang.RuntimeException: someMessage")
    }
  }
}
