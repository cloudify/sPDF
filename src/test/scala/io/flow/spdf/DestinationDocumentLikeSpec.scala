package io.flow.spdf

import java.io.{ByteArrayOutputStream, File, OutputStream}

import DestinationDocumentLike.{FileDestinationDocument, OutputStreamDestinationDocument}
import org.mockito.MockitoSugar
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.sys.process._

class DestinationDocumentLikeSpec extends AnyWordSpec with Matchers with MockitoSugar {

  trait catProcess {
    val process = Process(Seq("cat", "-"))
  }

  "DestinationDocumentLike" should {
    "set commandParameter to -" in {
      new DestinationDocumentLike[Unit] {}.commandParameter(()) should equal("-")
    }

    "leave process untouched" in new catProcess {
      new DestinationDocumentLike[Unit] {}.sinkTo(())(process) should equal(process)
    }
  }

  "FileDestinationDocument" should {
    "set commandParameter to the absolute file path" in {
      val file = new File("test")
      FileDestinationDocument.commandParameter(file) should equal(file.getAbsolutePath)
    }

    "leave process untouched" in new catProcess {
      FileDestinationDocument.sinkTo(mock[File])(process) should equal(process)
    }
  }

  "OutputStreamDestinationDocument" should {
    "set commandParameter to -" in {
      OutputStreamDestinationDocument.commandParameter(mock[OutputStream]) should equal("-")
    }

    "pipe process STDOUT into destination stream" in new catProcess {
      // need to fix https://github.com/cloudify/sPDF/issues/36
      pending
      val destinationDocument = new ByteArrayOutputStream()
      // val processWithDestination =  OutputStreamDestinationDocument.sinkTo(destinationDocument)(process)

      // val exitValue = (Seq("echo", "-n", "Hello world") #> processWithDestination).!
      // exitValue should equal(0)

      destinationDocument.toString should equal("Hello world")
    }
  }
}
