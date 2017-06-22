package utils


case class AmountAndRemainder[M](amount: M, remainder: M)

trait Splitable[S] {
  def split(m: S, n: Int): AmountAndRemainder[S]
}

object Splitable {

  implicit object SplitableForInt extends Splitable[Int] {
    override def split(m: Int, n: Int): AmountAndRemainder[Int] = AmountAndRemainder(m / n, m % n)
  }

  implicit object SplitableForBigInt extends Splitable[BigInt] {
    override def split(m: BigInt, n: Int): AmountAndRemainder[BigInt] =
      AmountAndRemainder(m / n, m % n)
  }

}
