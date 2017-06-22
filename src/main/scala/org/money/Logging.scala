package org.money

trait Logger {
  def log(s: => String)

  def logResult[R](msgFn: R => String)(block: => R) = {
    val result = block
    log(msgFn(result))
    result
  }
}

object Logger {

  implicit object DefaultLogger extends Logger {
    override def log(s: => String): Unit = println(s)
  }

}
