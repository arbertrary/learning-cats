package chapter2.monoids

trait Semigroup[A] {
  def combine(x: A, y: A): A
}

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid {
  def apply[A](implicit monoid: Monoid[A]): Monoid[A] =
    monoid
}

object MonoidInstances {
  //  Exercise B.1 Monoid Instances for Boolean
  implicit val booleanOrMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty: Boolean = false

    def combine(x: Boolean, y: Boolean): Boolean = x || y
  }

  implicit val booleanAndMonoid: Monoid[Boolean] = new Monoid[Boolean] {
    def empty: Boolean = true

    def combine(x: Boolean, y: Boolean): Boolean = x && y
  }

  implicit val booleanEitherMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(a: Boolean, b: Boolean): Boolean =
        (a && !b) || (!a && b)

      def empty = false
    }
  implicit val booleanXnorMonoid: Monoid[Boolean] =
    new Monoid[Boolean] {
      def combine(a: Boolean, b: Boolean): Boolean =
        (!a || b) && (a || !b)

      def empty = true
    }

  //  Exercise B.2 Monoid Instances for sets (union)
  implicit def setUnionMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]) = a union b

      def empty = Set.empty[A]
    }

  implicit def symDiffMonoid[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] =
        (a diff b) union (b diff a)

      def empty: Set[A] = Set.empty
    }

  //  Semigroup Instance for sets (intersection)
  implicit def setIntersectionSemigroup[A]: Semigroup[Set[A]] =
    new Semigroup[Set[A]] {
      def combine(a: Set[A], b: Set[A]): Set[A] =
        a intersect b
    }
}

