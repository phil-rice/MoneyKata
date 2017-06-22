package org.money

import utils.AmountAndRemainder


class MoneyExceptionHandler[M](delegate: Money[M])(implicit logger: Logger) extends Money[M] {
  def tryAndRecordException[X](msg: => String)(block: => X): X = try {
    block
  } catch {
    case e: Exception =>
      logger.log(s"$msg caused an Exception $e")
      throw e
  }

  override def add(one: M, two: M): M = tryAndRecordException(s"$one and $two")(delegate.add(one, two))

  override def split(s: M, n: Int): AmountAndRemainder[M] = tryAndRecordException(s"$s split $n")(delegate.split(s, n))
}
