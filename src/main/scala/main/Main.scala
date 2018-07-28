package main

// Standard. More specific imports only if needed
import cats._
import cats.implicits._

// for Show Instances
//import cats.Show
//import cats.instances.string._
//import cats.instances.int._

// for |+|
//import cats.syntax.semigroup._

// for 123.show
//import cats.syntax.show._

object Main extends App {
  println("Hello " |+| "Cats!")

  val showInt: Show[Int] = Show.apply[Int]
  val showString: Show[String] = Show.apply[String]
  val intAsString: String = showInt.show(123)
  println(intAsString)

  val shownInt = 123.show
  val shownString = "abc".show
  println(shownInt)


}
