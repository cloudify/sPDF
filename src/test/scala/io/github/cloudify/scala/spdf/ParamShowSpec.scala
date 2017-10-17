package io.github.cloudify.scala.spdf

import io.github.cloudify.scala.spdf.ParamShow.{BooleanParamShow, FloatParamShow, IterableParamShow, StringParamShow}
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

  "FloatParamShow" should {

    "represent a properly formatted float parameter" in {
      FloatParamShow.show("zoom", 0.2f) should equal(Seq("--zoom", "0.20"))
    }

  }

}
