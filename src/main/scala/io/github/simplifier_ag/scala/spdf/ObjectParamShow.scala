package io.github.simplifier_ag.scala.spdf

trait ObjectParamShow[T] extends ParamShow[T]

object ObjectParamShow {
  implicit object BooleanObjectParamShow extends ObjectParamShow[Boolean] {
    override def show(name: String, value: Boolean): Iterable[String] = {
      if(value)
        formatObjectParam(name)
      else
        Iterable.empty
    }
  }

  private def formatObjectParam(name: String): Iterable[String] = Seq(name)
}
