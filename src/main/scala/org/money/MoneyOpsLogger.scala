package org.money

import utils.{AmountAndRemainder, Percentage}

class MoneyOpsLogger[M](delegate: MoneyOps[M])(implicit logger: Logger) extends MoneyOps[M] {
  override def add(one: M, two: M): M =
    logger.logResult[M](result => s"$one + $two => $result")(delegate.add(one, two))

  override def split(s: M, n: Int): AmountAndRemainder[M] =
    logger.logResult[AmountAndRemainder[M]](result => s"$s split $n ways => $result")(delegate.split(s, n))

}
