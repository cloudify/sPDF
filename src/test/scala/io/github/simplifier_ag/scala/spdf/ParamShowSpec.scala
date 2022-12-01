package io.github.simplifier_ag.scala.spdf

import ParamShow.{BooleanParamShow, StringParamShow, IterableParamShow}
import org.scalatest.WordSpec
import org.scalatest.Matchers

class ParamShowSpec extends WordSpec with Matchers {

  "StringParamShow" should {

    "represent a parameter" in {
      StringParamShow.show("param", "value") should equal(Seq("--param", "value"))
    }

  }

  "BooleanParamShow" should {

    "represent a parameter when true" in {
      BooleanParamShow.show("param", value = true) should equal(Seq("--param"))
    }

    "return empty parameter when false" in {
      BooleanParamShow.show("param", value = false) should equal(Iterable.empty)
    }

  }

  "IterableParamShow" should {

    "represent a repeatable parameter" in {
      IterableParamShow.show("param", List("a", "b")) should equal(Seq("--param", "a", "--param", "b"))
    }

  }

}
