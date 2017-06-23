package utils

case class Percentage(points: Int) {
  override def toString: String = s"$points%"
}

trait PercentageOps[T] {
  def times(t: T, p: Percentage): T
}

object PercentageOps {

  implicit object PercentageOpsForInt extends PercentageOps[Int] {
    override def times(t: Int, p: Percentage): Int = t *  p.points / 100
  }

  implicit object PercentageOpsForBigInt extends PercentageOps[BigInt] {
    override def times(t: BigInt, p: Percentage): BigInt = t * p.points / 100
  }

}