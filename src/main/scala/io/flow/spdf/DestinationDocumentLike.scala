package io.flow.spdf

import java.io.{File, OutputStream}

import scala.annotation.{implicitNotFound, nowarn}
import scala.sys.process._

/** Type class that describes the kind of destination type we can write the resulting PDF documents to.
  */
@implicitNotFound(msg = "Cannot find DestinationDocumentLike type class for ${A}")
trait DestinationDocumentLike[-A] {

  /** The destination argument to supply to `wkhtmltopdf`
    */
  def commandParameter(destinationDocument: A): String = "-"

  /** Sink the process output into this document
    */
  def sinkTo(@nowarn destinationDocument: A)(process: ProcessBuilder): ProcessBuilder =
    process

}

object DestinationDocumentLike {

  implicit object FileDestinationDocument extends DestinationDocumentLike[File] {

    override def commandParameter(destinationDocument: File): String =
      destinationDocument.getAbsolutePath

  }

  implicit object OutputStreamDestinationDocument extends DestinationDocumentLike[OutputStream] {

    override def sinkTo(destinationDocument: OutputStream)(process: ProcessBuilder) =
      process #> destinationDocument

  }

}
