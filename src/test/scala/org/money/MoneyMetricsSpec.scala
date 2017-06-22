package org.money

import org.scalatest.{FlatSpec, Matchers}

class MetricsForMoneyAsMoneySpec extends AbstractMoneySpec("MoneyMetrics")(new MetricsForMoney(new SimpleMoney[Int]))


class MoneyMetricsSpec extends FlatSpec with Matchers{
behavior of "MoneyMetrics"
  import MoneyLanguage._
  it should "count the number of times add and split are called " in {
    implicit val money = new MetricsForMoney(new SimpleMoney[Int])

    1 add 2 add 3 add 4 split 2

    money.copyOfMetrics shouldBe Map("add" -> 3, "split" -> 1)
  }
}
