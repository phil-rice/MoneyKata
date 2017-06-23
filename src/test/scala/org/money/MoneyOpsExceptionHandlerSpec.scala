package org.money

import org.scalatest.{FlatSpec, Matchers}
import MoneyLanguage._
import org.scalamock.scalatest.MockFactory

class MoneyExceptionHandlerAsMoneyOpsSpec extends AbstractMoneyOpsSpec("MoneyExceptionHandler")(new MoneyOpsExceptionHandler(new SimpleMoneyOps[Int]))

class MoneyOpsExceptionHandlerSpec extends FlatSpec with Matchers with MockFactory {

  behavior of "MoneyExceptionHandler"

  def withMocks(fn: MoneyOps[Int] => MoneyOps[Int] => LoggerMock => Unit) = {
    val simpleMoney = mock[MoneyOps[Int]]
    implicit val logger = new LoggerMock
    val money = new MoneyOpsExceptionHandler(simpleMoney)
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
