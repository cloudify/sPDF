package io.flow.spdf

import ParamShow.{BooleanParamShow, IterableParamShow, StringParamShow}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ParamShowSpec extends AnyWordSpec with Matchers {

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
