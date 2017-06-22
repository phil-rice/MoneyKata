package utils

trait Monoid[M] {
  def zero: M

  def add(one: M, two: M): M
}

object Monoid{
  implicit object MonoidForInt extends Monoid[Int]{
    override def zero: Int = 0

    override def add(one: Int, two: Int): Int = one + two
  }
  implicit object MonoidForBigInt extends Monoid[BigInt]{
    override def zero: BigInt = 0

    override def add(one: BigInt, two: BigInt): BigInt = one + two
  }
}
