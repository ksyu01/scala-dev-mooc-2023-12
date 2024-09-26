package module1.homework


import scala.util.Random

class BallsExperiment {
  val random = new Random()
  val boxBalls:List[Int] = List(0, 0, 0, 1, 1, 1)
  val boxBallsPutoutOrder: List[Int] = random.shuffle(boxBalls)

  def isFirstBlackSecondWhite(): Boolean = {
    if (boxBallsPutoutOrder(0) == 0 && boxBallsPutoutOrder(1) == 1) true else false
  }
}

object BallsTest {
  def main(args: Array[String]): Unit = {
    val count = 10000
    val listOfExperiments: List[BallsExperiment] = List.fill(count)(new BallsExperiment)
    val countOfExperiments: List[Boolean] = listOfExperiments.map(_.isFirstBlackSecondWhite())
    val countOfPositiveExperiments: Float = countOfExperiments.count(_ == true)
    println(countOfPositiveExperiments / count)
  }
}