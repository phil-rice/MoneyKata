package org.money

import org.scalatest.{FlatSpec, Matchers}


class MoneyBraggerAsMoneyOpsSpec extends AbstractMoneyOpsSpec("MoneyBragger")(new MoneyOpsBragger(new SimpleMoneyOps[Int]))

class MoneyOpsBraggerSpec extends FlatSpec with Matchers {

  import MoneyLanguage._

  implicit val money: MoneyOps[Int] = new MoneyOpsBragger(new SimpleMoneyOps[Int])

  behavior of "MoneyBragger"

  it should "add up all the adds and splits" in {
    1 add 2 split 4
    money.toString shouldBe "Bragging Rights: Added 3 Split 3"

  }
}
