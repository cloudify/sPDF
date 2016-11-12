package io.github.cloudify.scala.spdf

import java.io.{OutputStream, ByteArrayOutputStream, File}
import io.github.cloudify.scala.spdf.DestinationDocumentLike.{OutputStreamDestinationDocument, FileDestinationDocument}
import scala.sys.process._
import org.scalatest.WordSpec
import org.scalatest.mockito.MockitoSugar
import org.scalatest.Matchers

class DestinationDocumentLikeSpec extends WordSpec with Matchers with MockitoSugar {

  trait catProcess {
    val process = Process(Seq("cat", "-"))
  }

  "DestinationDocumentLike" should {
    "set commandParameter to -" in {
      new DestinationDocumentLike[Unit] {}.commandParameter(Unit) should equal("-")
    }

    "leave process untouched" in new catProcess {
      new DestinationDocumentLike[Unit] {}.sinkTo(Unit)(process) should equal(process)
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
      val destinationDocument = new ByteArrayOutputStream()
      val processWithDestination =  OutputStreamDestinationDocument.sinkTo(destinationDocument)(process)

      val exitValue = (Seq("echo", "-n", "Hello world") #> processWithDestination).!
      // exitValue should equal(0)

      destinationDocument.toString should equal("Hello world")
    }
  }
}
