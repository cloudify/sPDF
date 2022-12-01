package io.github.simplifier_ag.scala.spdf

import java.io.File
import scala.sys.process._
import org.scalatest.Matchers
import org.scalatest.WordSpec

class PdfSpec extends WordSpec with Matchers {

  "A Pdf" should {

    "require the executionPath config" in {
      val file = new File("notexecutable")
      val filePath = file.getAbsolutePath

      assertThrows[NoExecutableException] {
        new Pdf(filePath, PdfConfig.default)
      }

      assertThrows[NoExecutableException] {
        Pdf(filePath, PdfConfig.default)
      }

    }

    PdfConfig.findExecutable match {
      case Some(_) =>
        "generate a PDF file from an HTML string" in {

          val page =
            """
              |<html><body><h1>Hello</h1></body></html>
            """.stripMargin

          val file = File.createTempFile("scala.spdf", "pdf")

          val pdf = Pdf(PdfConfig.default)

          pdf.run(page, file)

          Seq("file", file.getAbsolutePath).!! should include("PDF document")
        }

      case None =>
        "Skipping test, missing wkhtmltopdf binary" in { true should equal(true) }
    }


  }

}
