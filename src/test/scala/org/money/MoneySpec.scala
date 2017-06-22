package org.money

import org.scalatest.{FlatSpec, Matchers}
import utils.AmountAndRemainder

abstract class AbstractMoneySpec(name: String)(implicit money: Money[Int]) extends FlatSpec with Matchers {

  import MoneyLanguage._

  behavior of "Money aspects of $name"

  it should "add" in {
    3 add 4 shouldBe 7
  }

  it should "split one ways" in {
    4 split 1 shouldBe AmountAndRemainder(4, 0)
  }
  it should "split two ways" in {
    2 split 2 shouldBe AmountAndRemainder(1, 0)
    3 split 2 shouldBe AmountAndRemainder(1, 1)
  }
  it should "split three ways" in {
    9 split 3 shouldBe AmountAndRemainder(3, 0)
    10 split 3 shouldBe AmountAndRemainder(3, 1)
    11 split 3 shouldBe AmountAndRemainder(3, 2)
  }
}


class SimpleMoneySpec extends AbstractMoneySpec("SimpleMoney")(new SimpleMoney[Int])
