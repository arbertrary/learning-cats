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

  // Exercise 4
  implicit val catShow: Show[Cat] = {
    cat =>
      val name = cat.name.show
      val age = cat.age.show
      val color = cat.color.show
      s"$name is a $age year-old $color chapter1.cat."
  }

  //  Exercise 5
  implicit val catEq: Eq[Cat] = Eq.instance({
    (cat1, cat2) =>
      cat1.name === cat2.name &&
        cat1.age === cat2.age &&
        cat1.color === cat2.color
  })


  def main(args: Array[String]): Unit = {
    //    Without PrintableSyntax:
    val cat = Cat("Luna", 2, "black")
    Printable.print(cat)

    //    Using PrintableSyntax
    Cat("Luna", 2, "black").print

    // using Show[Cat] resp. catShow as defined above
    println(cat.show)


    val cat1 = Cat("Garfield", 38, "orange and black")
    val cat2 = Cat("Heathcliff", 33, "orange and black")
    println(cat1 === cat2)
    println(cat1 =!= cat2)


    val optionCat1 = Option(cat1)
    val optionCat2 = Option.empty[Cat]
    println(optionCat1 === optionCat2)
    println(optionCat1 =!= optionCat2)

  }
}