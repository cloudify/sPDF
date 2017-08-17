package io.github.cloudify.scala.spdf

import java.io.File
import scala.sys.process._
import org.scalatest.Matchers
import org.scalatest.WordSpec

class ImageSpec extends WordSpec with Matchers {

  "An Image" should {

    "require the executionPath config" in {
      val file = new File("notexecutable")
      val filePath = file.getAbsolutePath

      assertThrows[NoExecutableException] {
        new Image(filePath, ImageConfig.default)
      }

      assertThrows[NoExecutableException] {
        Image(filePath, ImageConfig.default)
      }

    }

    PdfConfig.findExecutable match {
      case Some(_) =>
        "generate an image file from an HTML string" in {

          val page =
            """
              |<html><body><h1>Hello</h1></body></html>
            """.stripMargin

          val file = File.createTempFile("scala.spdf", "pdf")

          val image = Image(ImageConfig.default)

          image.run(page, file)

          Seq("file", file.getAbsolutePath).!! should include("PDF document")
        }

      case None =>
        "Skipping test, missing wkhtmltopdf binary" in { true should equal(true) }
    }


  }

}
