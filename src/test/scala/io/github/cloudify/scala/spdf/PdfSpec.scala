package io.github.cloudify.scala.spdf

import java.io.File
import java.net.URL

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

        "generate a PDF file from multiple HTML URLs" in {

          val file = File.createTempFile("scala.spdf", "pdf")
          val url1 = new URL("https://www.google.com")
          val url2 = new URL("https://www.wikipedia.org")
          val pdf = Pdf(PdfConfig.default)

          pdf.run(Seq(url1, url2), file)

          Seq("file", file.getAbsolutePath).!! should include("PDF document")
        }

        "generate a PDF file from multiple HTML strings" in {

          val page1 =
            """
              |<html><body><h1>Hello</h1></body></html>
            """.stripMargin

          val page2 =
            """
              |<html><body><h1>Hello2</h1></body></html>
            """.stripMargin

          val file = File.createTempFile("scala.spdf", ".pdf")
          val xml = <html><body><h1>Hello3</h1></body></html>
          val pdf = Pdf(PdfConfig.default)

          pdf.run(Seq(page1, page2), file)

          Seq("file", file.getAbsolutePath).!! should include("PDF document")
        }

        "generate a PDF file from multiple HTML xml elements" in {

          val file = File.createTempFile("scala.spdf", ".pdf")
          val xml1 = <html><body><h1>Nice!</h1></body></html>
          val xml2 = <html><body><h1>This is awesome</h1></body></html>
          val pdf = Pdf(PdfConfig.default)

          pdf.run(Seq(xml1, xml2), file)

          Seq("file", file.getAbsolutePath).!! should include("PDF document")
        }


      case None =>
        "Skipping test, missing wkhtmltopdf binary" in { true should equal(true) }
    }
  }

}
