package org.money

import org.scalatest.{FlatSpec, Matchers}
import utils.Monoid

import scala.reflect.ClassTag


abstract class AbstractMonoidSpec[M: ClassTag](zero: M, one: M, two: M, three: M)(implicit monoid: Monoid[M]) extends FlatSpec with Matchers {

  behavior of s"Monoid[${implicitly[ClassTag[M]].runtimeClass.getClass.getSimpleName}]}"

  it should "have a zero" in {
    monoid.zero shouldBe zero
  }

  it should "obey identity with zero" in {
    monoid.add(zero, zero) shouldBe zero
    monoid.add(zero, one) shouldBe one
    monoid.add(zero, two) shouldBe two
    monoid.add(zero, three) shouldBe three
    monoid.add(one, zero) shouldBe one
    monoid.add(two, zero) shouldBe two
    monoid.add(three, zero) shouldBe three
  }

  it should " allow simple arithmetic" in {
    monoid.add(one, one) shouldBe two
    monoid.add(one, two) shouldBe three
    monoid.add(two, one) shouldBe three
  }

}


class MonoidForIntSpec extends AbstractMonoidSpec[Int](0, 1, 2, 3)

class MonoidForBigIntSpec extends AbstractMonoidSpec[BigInt](0, 1, 2, 3)