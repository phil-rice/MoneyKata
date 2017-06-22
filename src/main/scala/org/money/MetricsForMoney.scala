package org.money

import java.util.concurrent.atomic.AtomicInteger

import utils.AmountAndRemainder

import scala.collection.concurrent.TrieMap

class MetricsForMoney[M](delegate: Money[M]) extends Money[M] {
  private val metrics = TrieMap[String, AtomicInteger]()

  private def addMetric(s: String) = metrics.getOrElseUpdate(s, new AtomicInteger()).incrementAndGet()

  def copyOfMetrics = metrics.mapValues(_.get).toMap

  override def add(one: M, two: M): M = {
    addMetric("add")
    delegate.add(one, two)
  }

  override def split(m: M, ways: Int): AmountAndRemainder[M] = {
    addMetric("split")
    delegate.split(m, ways)
  }
}
