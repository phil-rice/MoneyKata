package org.money

import org.scalatest.{FlatSpec, Matchers}
import utils.{AmountAndRemainder, Splitable}

import scala.reflect.ClassTag


abstract class SplitableSpec[M: ClassTag](zero: M, one: M, two: M, three: M)(implicit splitable: Splitable[M]) extends FlatSpec with Matchers {
  behavior of s"Splitable[${implicitly[ClassTag[M]].runtimeClass.getSimpleName}]"

  it should "split numbers" in {
    splitable.split(three, 1) shouldBe AmountAndRemainder(three, zero)
    splitable.split(three, 2) shouldBe AmountAndRemainder(one, one)
    splitable.split(two, 2) shouldBe AmountAndRemainder(one, zero)
  }
}

class SplitableIntSpec extends SplitableSpec[Int](0, 1, 2, 3)

class SplitableBigIntSpec extends SplitableSpec[BigInt](0, 1, 2, 3)
