package org.money

import java.util.concurrent.atomic.AtomicReference
import java.util.function.UnaryOperator

import utils.{AmountAndRemainder, Monoid}

class MoneyBragger[M](delegate: Money[M])(implicit monoid: Monoid[M]) extends Money[M] {
  private val totalAdded = new AtomicReference[M](monoid.zero)
  private val totalSplit = new AtomicReference[M](monoid.zero)

  def recordAndReturn(ref: AtomicReference[M], value: M): M = ref.updateAndGet(new UnaryOperator[M] {
    override def apply(t: M): M = monoid.add(t, value)
  })

  override def add(one: M, two: M): M = recordAndReturn(totalAdded, delegate.add(one, two))

  override def split(s: M, n: Int): AmountAndRemainder[M] = {
    recordAndReturn(totalSplit, s)
    delegate.split(s, n)
  }

  override def toString: String = s"Bragging Rights: Added ${totalAdded.get()} Split ${totalSplit}"
}
