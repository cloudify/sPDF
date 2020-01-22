package io.flow.spdf

import java.io.File

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.sys.process._

class WrappedPdfSpec extends AnyWordSpec with Matchers {

  "A Pdf" should {

    PdfConfig.findExecutable match {
      case Some(_) =>
        "generate a PDF file from an HTML string" in {

          val page =
            """
              |<html><body><h1>Hello</h1></body></html>
            """.stripMargin

          val file = File.createTempFile("scala.spdf", "pdf")

          val pdf = WrappedPdf(Seq("wkhtmltopdf"), PdfConfig.default)

          pdf.run(page, file)

          Seq("file", file.getAbsolutePath).!! should include("PDF document")
        }

      case None =>
        "Skipping test, missing wkhtmltopdf binary" in { true should equal(true) }
    }


  }

}
