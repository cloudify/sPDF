package io.github.simplifier_ag.scala.spdf

import java.io.{ByteArrayInputStream, InputStream, File}
import scala.sys.process._
import java.net.URL
import scala.annotation.implicitNotFound
import scala.xml.Elem

/**
 * Type class that describes the kind of source documents we can read the
 * input HTML from.
 */
@implicitNotFound(msg = "Cannot find SourceDocumentLike type class for ${A}")
trait SourceDocumentLike[-A] {

  /**
   * The source parameter to provide to `wkhtmltopdf`
   * Defaults to read from STDIN.
   */
  def commandParameter(sourceDocument: A): String = "-"

  /**
   * Source the input of the process from this sourceDocument
   * Defaults to passthrough.
   */
  def sourceFrom(sourceDocument: A)(process: ProcessBuilder): ProcessBuilder =
    process

}

object SourceDocumentLike {

  /**
   * Pipes the InputStream into the process STDIN
   */
  implicit object InputStreamSourceDocument extends SourceDocumentLike[InputStream] {

    override def sourceFrom(sourceDocument: InputStream)(process: ProcessBuilder): ProcessBuilder =
      process #< sourceDocument

  }

  /**
   * Sets the file absolute path as the input parameter
   */
  implicit object FileSourceDocument extends SourceDocumentLike[File] {

    override def commandParameter(sourceDocument: File): String =
      sourceDocument.getAbsolutePath

  }

  /**
   * Pipes a UTF-8 string into the process STDIN
   */
  implicit object StringSourceDocument extends SourceDocumentLike[String] {

    override def sourceFrom(sourceDocument: String)(process: ProcessBuilder) =
      process #< toInputStream(sourceDocument)

    private def toInputStream(sourceDocument: String): ByteArrayInputStream =
      new ByteArrayInputStream(sourceDocument.getBytes("UTF-8"))

  }

  /**
   * Sets the URL as the input parameter
   */
  implicit object URLSourceDocument extends SourceDocumentLike[URL] {

    override def commandParameter(sourceDocument: URL) = sourceDocument.getProtocol match {
      case "https" | "http" | "file" => sourceDocument.toString
      case _ => throw UnsupportedProtocolException(sourceDocument)
    }

  }

  /**
   * Sets the XML node as the input parameter
   */
  implicit object XmlSourceDocument extends SourceDocumentLike[Elem] {

    override def sourceFrom(sourceDocument: Elem)(process: ProcessBuilder) =
      process #< toInputStream(sourceDocument)

    private def toInputStream(sourceDocument: Elem): ByteArrayInputStream =
      new ByteArrayInputStream(sourceDocument.toString().getBytes("UTF-8"))

  }

}
