package io.github.cloudify.scala.spdf

import java.io.File
import scala.sys.process._
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.WordSpec

class WrappedPdfSpec extends WordSpec with ShouldMatchers {

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
