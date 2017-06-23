package org.money

import utils._


trait MoneyOps[M] {
  def add(one: M, two: M): M

  def split(s: M, n: Int): AmountAndRemainder[M]

}

class SimpleMoneyOps[M](implicit monoid: Monoid[M], splitable: Splitable[M], percentageOps: PercentageOps[M]) extends MoneyOps[M] {
  override def add(one: M, two: M): M = monoid.add(one, two)

  override def split(s: M, n: Int): AmountAndRemainder[M] = splitable.split(s, n)
}

trait MoneyLanguage {

  implicit class MoneyPimper[M](m: M)(implicit money: MoneyOps[M]) {
    def add(two: M) = money.add(m, two)

    def split(ways: Int) = money.split(m, ways)

  }

}

object MoneyLanguage extends  MoneyLanguage
