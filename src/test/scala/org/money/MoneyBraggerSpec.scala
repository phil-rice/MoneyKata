package org.money

import org.scalatest.{FlatSpec, Matchers}


class MoneyBraggerAsMoneySpec extends AbstractMoneySpec("MoneyBragger")(new MoneyBragger(new SimpleMoney[Int]))

class MoneyBraggerSpec extends FlatSpec with Matchers {

  import MoneyLanguage._

  implicit val money: Money[Int] = new MoneyBragger(new SimpleMoney[Int])


  behavior of "MoneyBragger"

  it should "add up all the adds and splits" in {
    1 add 2 split 4
    money.toString shouldBe "Bragging Rights: Added 3 Split 3"

  }
}
