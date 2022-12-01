package io.github.simplifier_ag.scala.spdf

sealed trait PageOrientation {
  val value: String
}

object Landscape extends PageOrientation {
  override val value = "Landscape"
}

object Portrait extends PageOrientation {
  override val value = "Portrait"
}
