package io.github.cloudify.scala.spdf

import org.scalatest.WordSpec
import org.scalatest.matchers.ShouldMatchers

class CommandOptionSpec extends WordSpec with ShouldMatchers {

  def instantiatedWith = afterWord("instantiated with")

  trait commandOptionWithDefault {
    lazy val commandOption = Parameter("param", "default")
  }

  trait commandOptionWithoutDefault {
    lazy val commandOption = Parameter[String]("param")
  }

  "A CommandOption" when instantiatedWith {

    "a default value" when {

      "not updated" should {
        "generate a parameter with the default value" in new commandOptionWithDefault {
          commandOption.toParameter should equal(Seq("--param", "default"))
        }
      }

      "updated with a new value" should {
        "generate a parameter with the new value" in new commandOptionWithDefault {
          commandOption := "newvalue"
          commandOption.toParameter should equal(Seq("--param", "newvalue"))
        }
      }

    }

    "no default value" when {
      "not updated" should {
        "do not generate a parameter" in new commandOptionWithoutDefault {
          commandOption.toParameter should equal(Iterable.empty)
        }
      }

      "updated with a new value" should {
        "generate a parameter with the new value" in new commandOptionWithoutDefault {
          commandOption := "newvalue"
          commandOption.toParameter should equal(Seq("--param", "newvalue"))
        }
      }
    }

  }

}
