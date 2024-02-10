package object monad {

  /**
   * Реализуйте методы map / flatMap / withFilter чтобы работал код и законы монад соблюдались
   * HINT: для проверки на пустой элемент можно использовать eq
   */

  sealed trait Wrap[+A] {

    def get: A

    def flatMap[R](f: A => Wrap[R]): Wrap[R] = this match {
      case NonEmptyWrap(result) => f(result)
      case EmptyWrap => EmptyWrap
    }

    // HINT: map можно реализовать через pure и flatMap
    def map[R](f: A => R): Wrap[R] = flatMap(t => Wrap.pure(f(t)))

    def withFilter(f: A => Boolean): Wrap[A] = this match {
      case NonEmptyWrap(result) => if (f(result)) this else EmptyWrap
      case EmptyWrap => EmptyWrap
    }

  }

  object Wrap {
    def empty[R]: Wrap[R] = EmptyWrap

    def pure[R](x: R): Wrap[R] =
      if (x == null) EmptyWrap
      else NonEmptyWrap(x)
  }

  case class NonEmptyWrap[A](result: A) extends Wrap[A] {
    override def get: A = result
  } // pure

  case object EmptyWrap extends Wrap[Nothing] {
    override def get: Nothing = throw new NoSuchElementException("Wrap.get")
  } // bottom, null element

}