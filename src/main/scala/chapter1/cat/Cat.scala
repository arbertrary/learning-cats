package chapter1.cat

import chapter1.printable._
import chapter1.printable.PrintableInstances._
import chapter1.printable.PrintableSyntax._

import cats._
import cats.implicits._

final case class Cat(name: String, age: Int, color: String)

object Cat {
  implicit val catPrintable: Printable[Cat] = new Printable[Cat] {
    def format(cat: Cat): String = {
      val name = Printable.format(cat.name)
      val age = Printable.format(cat.age)
      val color = Printable.format(cat.color)
      s"$name is a $age year-old $color chapter1.cat."
    }
  }

  implicit val catShow: Show[Cat] = {
    cat =>
      val name = cat.name.show
      val age = cat.age.show
      val color = cat.color.show
      s"$name is a $age year-old $color chapter1.cat."

  }

  def main(args: Array[String]): Unit = {
    //    Without PrintableSyntax:
    val cat = Cat("Luna", 2, "black")
    Printable.print(cat)

    //    Using PrintableSyntax
    Cat("Luna", 2, "black").print

    // using Show[Cat] resp. catShow as defined above
    println(cat.show)
  }
}