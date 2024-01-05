package module1

object functions extends App {


  /**
   * Функции
   */



  /**
   * Реализовать метод  sum, которая будет суммировать 2 целых числа и выдавать результат
   */

  def sum(x: Int, y: Int): Int = x + y

  sum(2, 3) // 5

  val sum2: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2

  sum2(2, 3) // 5

  val sum3: (Int, Int) => Int = sum2

  sum3(2, 3) // 5

  val sum4: (Int, Int) => Int = sum


  // Partial function

  val divide: PartialFunction[(Int, Int), Int] = new PartialFunction[(Int, Int), Int]{
    def isDefinedAt(x: (Int, Int)): Boolean = x._2 != 0

    def apply(v1: (Int, Int)): Int = v1._1 / v1._2
  }

  val divide2: PartialFunction[(Int, Int), Int] = {
    case x if x._2 != 0 => x._1 / x._2
//    case x if x._2 != 1 => x._1 / x._2
  }

  val l = List((4, 2), (5, 0), (6, 3)).collect(divide2)



  // SAM Single Abstract Method



  /**
   *  Задание 1. Написать ф-цию метод isEven, которая будет вычислять является ли число четным
   */
  def isEven(x: Int): Boolean = {
    x % 2 == 0
  }

  /**
   * Задание 2. Написать ф-цию метод isOdd, которая будет вычислять является ли число нечетным
   */
  def isOdd(x: Int): Boolean = {
    x % 2 != 0
  }

  /**
   * Задание 3. Написать ф-цию метод filterEven, которая получает на вход массив чисел и возвращает массив тех из них,
   * которые являются четными
   */
  def filterEven(numbers: Array[Int]): Array[Int] = {
    numbers.filter(x => isEven(x))
  }


  /**
   * Задание 4. Написать ф-цию метод filterOdd, которая получает на вход массив чисел и возвращает массив тех из них,
   * которые являются нечетными
   */
  def filterOdd(numbers: Array[Int]): Array[Int] = {
    numbers.filter(isOdd)
  }

  /**
   * return statement
   *
   */
  val myRange: Range = 0 to 3

  // isEven
  myRange.foreach(x => println(s"isEven($x): " + isEven(x)))

  // isOdd
  myRange.foreach(x => println(s"isOdd($x): " + isOdd(x)))

  val myArray = myRange.toArray
  // filterEven
  println{
    s"filterEven(${myArray.mkString(", ")}) => " +
      filterEven(myArray).mkString(", ")
  }

  // filterOdd
  println{
    s"filterOdd(${myArray.mkString(", ")}) => " +
      filterOdd(myArray).mkString(", ")
  }

}