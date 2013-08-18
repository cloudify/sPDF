package io.github.cloudify.scala.spdf

import java.io.File
import scala.sys.process._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec

class PdfSpec extends WordSpec with ShouldMatchers {

  "A Pdf" should {

    "require the executionPath config" in {
      val file = new File("notexecutable")
      val filePath = file.getAbsolutePath

      evaluating {
        new Pdf(filePath, PdfConfig.default)
      } should produce[NoExecutableException]

      evaluating {
        Pdf(filePath, PdfConfig.default)
      } should produce[NoExecutableException]

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
