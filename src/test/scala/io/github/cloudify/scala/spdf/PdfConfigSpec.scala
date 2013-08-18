package io.github.cloudify.scala.spdf

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec

class PdfConfigSpec extends WordSpec with ShouldMatchers {

  def someConfig = new PdfConfig {
    convertForms := true
    marginBottom := "1in"
    minimumFontSize := 3
    orientation := Landscape
    zoom := 1.23f
  }

  "PdfConfig" should {

    "have a default config" in {
      PdfConfig.toParameters(PdfConfig.default) should equal(Seq("--encoding", "UTF-8"))
    }

    "generate parameters from config" in {
      PdfConfig.toParameters(someConfig) should equal(Seq("--forms", "--encoding", "UTF-8", "--margin-bottom", "1in", "--minimum-font-size", "3", "--orientation", "Landscape", "--zoom", "1.23"))
    }

  }

}
