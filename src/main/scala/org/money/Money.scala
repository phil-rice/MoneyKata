package org.money

import utils._

object MoneyLanguage {
  implicit class MoneyPimper[M](m: M)(implicit money: Money[M]) {
    def add (two: M) = money.add(m, two)
    def split ( ways: Int) = money.split(m, ways)
  }
}

trait Money[M] {
  def add(one: M, two: M): M

  def split(s: M, n: Int): AmountAndRemainder[M]
}

class SimpleMoney[M](implicit monoid: Monoid[M], splitable: Splitable[M]) extends Money[M] {
    override def add(one: M, two: M): M = monoid.add(one, two)

    override def split(s: M, n: Int): AmountAndRemainder[M] = splitable.split(s, n)
}
