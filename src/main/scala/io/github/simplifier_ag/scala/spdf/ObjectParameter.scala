package io.github.simplifier_ag.scala.spdf

trait ObjectParameter[T] extends Parameter[T] {
  def toParameter(implicit shower: ObjectParamShow[T]): Iterable[String] = value match {
    case Some(v) => shower.show(name, v)
    case _ => Iterable.empty
  }
}

object ObjectParameter {
  def apply[T : ObjectParamShow](commandName: String): ObjectParameter[T] =
    new ObjectParameter[T] {
      /**
       * The commandline name for this parameter
       */
      override val name: String = commandName
    }

}
