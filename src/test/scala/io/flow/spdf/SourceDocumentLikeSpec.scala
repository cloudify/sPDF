package io.flow.spdf

import java.io.{ByteArrayInputStream, File}
import java.net.URL

import SourceDocumentLike._
import org.mockito.MockitoSugar
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.sys.process.Process

class SourceDocumentLikeSpec extends AnyWordSpec with Matchers with MockitoSugar {

  val catProcess = Process("cat")

  trait inputStreamInput {
    val input = new ByteArrayInputStream("Hello world".getBytes("UTF-8"))
  }

  trait stringInput {
    val input = "Hello world"
  }

  trait xmlInput {
    val input = <body>Hello world</body>
  }

  trait fileInput {
    val input = new File("test")
  }

  trait urlInput {
    val input = new URL("http://www.google.com")
  }

  trait httpsUrlInput {
    val input = new URL("https://www.google.com")
  }

  trait unsupportedUrlInput {
    val input = new URL("ftp://ftp.google.com")
  }

  "SourceDocumentLike" should {

    "set commandParameter to -" in {
      new SourceDocumentLike[Unit] {}.commandParameter(()) should equal("-")
    }

    "leave process untouched" in {
      new SourceDocumentLike[Unit] {}.sourceFrom(())(catProcess) should equal(catProcess)
    }

  }

  "InputStreamSourceDocument" should {

    "have commandParameter to -" in new inputStreamInput {
      InputStreamSourceDocument.commandParameter(input) should equal("-")
    }

    "pipe stream into process STDIN" in new inputStreamInput {
      // need to fix https://github.com/cloudify/sPDF/issues/36
      pending
      val processWithSource = InputStreamSourceDocument.sourceFrom(input)(catProcess)
      processWithSource.!! should equal("Hello world\n")
    }

  }

  "FileSourceDocument" should {

    "have commandParameter to the absolute path of the file" in new fileInput {
      FileSourceDocument.commandParameter(input) should equal(input.getAbsolutePath)
    }

    "leave process untouched" in new fileInput {
      FileSourceDocument.sourceFrom(input)(catProcess) should equal(catProcess)
    }

  }

  "StringSourceDocument" should {

    "have commandParameter to -" in new stringInput {
      StringSourceDocument.commandParameter(input) should equal("-")
    }

    "pipe stream into process STDIN" in new stringInput {
      // need to fix https://github.com/cloudify/sPDF/issues/36
      pending
      val processWithSource = StringSourceDocument.sourceFrom(input)(catProcess)
      processWithSource.!! should equal("Hello world\n")
    }

  }

  "XmlSourceDocument" should {

    "have commandParameter to -" in new xmlInput {
      XmlSourceDocument.commandParameter(input) should equal("-")
    }

    "pipe stream into process STDIN" in new xmlInput {
      // need to fix https://github.com/cloudify/sPDF/issues/36
      pending
      val processWithSource = XmlSourceDocument.sourceFrom(input)(catProcess)
      processWithSource.!! should equal("<body>Hello world</body>\n")
    }

  }

  "URLSourceDocument" should {

    "have commandParameter set to URL" in new urlInput {
      URLSourceDocument.commandParameter(input) should equal("http://www.google.com")
    }

    "leave process untouched" in new urlInput {
      URLSourceDocument.sourceFrom(input)(catProcess) should equal(catProcess)
    }

    "throw an UnsupportedProtocolException if protocol is not supported" in new unsupportedUrlInput {
      assertThrows[UnsupportedProtocolException] {
        URLSourceDocument.commandParameter(input)
      }
    }
  }

  "httpsURLSourceDocument" should {

    "have commandParameter set to URL" in new httpsUrlInput {
      URLSourceDocument.commandParameter(input) should equal("https://www.google.com")
    }

    "leave process untouched" in new httpsUrlInput {
      URLSourceDocument.sourceFrom(input)(catProcess) should equal(catProcess)
    }

    "throw an UnsupportedProtocolException if protocol is not supported" in new unsupportedUrlInput {
      assertThrows[UnsupportedProtocolException] {
        URLSourceDocument.commandParameter(input)
      }
    }
  }

}
