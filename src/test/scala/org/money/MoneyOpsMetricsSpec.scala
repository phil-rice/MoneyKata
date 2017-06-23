package org.money

import org.scalatest.{FlatSpec, Matchers}

class MetricsForMoneyAsMoneyOpsSpec extends AbstractMoneyOpsSpec("MoneyMetrics")(new MetricsForMoneyOps(new SimpleMoneyOps[Int]))


class MoneyOpsMetricsSpec extends FlatSpec with Matchers{
behavior of "MoneyMetrics"
  import MoneyLanguage._
  it should "count the number of times add and split are called " in {
    implicit val money = new MetricsForMoneyOps(new SimpleMoneyOps[Int])

    1 add 2 add 3 add 4 split 2

    money.copyOfMetrics shouldBe Map("add" -> 3, "split" -> 1)
  }
}
