package chapter2.superadder


case class Order(totalCost: Double, quantity: Double)

object SuperAdder {
  //  Exercise B.3 Adding All The Things Part 1
  //  def add(items: List[Int]): Int = items.foldLeft(0)(_ + _)

  //  using monoids; Not useful yet
  import cats.Monoid
  import cats.instances.int._ // for Monoid
  import cats.syntax.semigroup._ // for |+|
  def add(items: List[Int]): Int =
    items.foldLeft(Monoid[Int].empty)(_ |+| _)

  //  Exercise B.4 Adding All The Things Part 2
  import cats.instances.option._ // for Monoid
  //  def __add[A](items: List[A])(implicit monoid: Monoid[A]): A =
  //    items.foldLeft(monoid.empty)(_ |+| _)
  def add[A: Monoid](items: List[A]): A =
    items.foldLeft(Monoid[A].empty)(_ |+| _)


  //  Exercise B.5: Adding All The Things Part 3
  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    def combine(x: Order, y: Order): Order =
      Order(
        x.totalCost + y.totalCost,
        x.quantity + y.quantity
      )

    def empty: Order = Order(0.0, 0.0)

  }

  def main(args: Array[String]): Unit = {
    println(add(List(1, 2, 3)))
    println(add(List(Some(1), None, Some(2))))

    val order1 = Order(1.0, 2.0)
    val order2 = Order(2.0, 1.0)
    println(add(List(order1, order2)))

  }

}
