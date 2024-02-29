package module1.futures

import module1.futures.HomeworksUtils.TaskSyntax

import scala.concurrent.impl.Promise
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object task_futures_sequence {

  /**
   * В данном задании Вам предлагается реализовать функцию fullSequence,
   * похожую на Future.sequence, но в отличии от нее,
   * возвращающую все успешные и не успешные результаты.
   * Возвращаемое тип функции - кортеж из двух списков,
   * в левом хранятся результаты успешных выполнений,
   * в правово результаты неуспешных выполнений.
   * Не допускается использование методов объекта Await и мутабельных переменных var
   */
  /**
   * @param futures список асинхронных задач
   * @return асинхронную задачу с кортежом из двух списков
   */
  def fullSequence[A](futures: List[Future[A]])
                     (implicit ex: ExecutionContext): Future[(List[A], List[Throwable])] = {

    val initialFuture: Future[(List[A], List[Throwable])] = Future(List(), List())

    val preFuture: Future[(List[A], List[Throwable])] = futures.foldLeft(initialFuture){(accFuture, nextFuture) =>
      accFuture.flatMap{ case (successList, failureList) =>
        nextFuture.map(x => Success(x)).recover(x => Failure(x)).map {
          case Failure(exception) => (successList, exception :: failureList)
          case Success(value) => (value :: successList, failureList)
        }
      }
    }

    preFuture.map{ case (l1, l2) => (l1.reverse, l2.reverse)}
  }
}
