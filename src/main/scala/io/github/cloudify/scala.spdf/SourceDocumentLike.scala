package io.github.cloudify.scala.spdf

import java.io.{ByteArrayInputStream, File, FileInputStream, InputStream}

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

  val isUsingInputStream = false

  def asInputStream(sourceDocument: A): InputStream = ???

}

object SourceDocumentLike {

  /**
   * Pipes the InputStream into the process STDIN
   */
  implicit object InputStreamSourceDocument extends SourceDocumentLike[InputStream] {
    override val isUsingInputStream: Boolean = true
    override def sourceFrom(sourceDocument: InputStream)(process: ProcessBuilder): ProcessBuilder =
      process #< sourceDocument

    override def asInputStream(sourceDocument: InputStream): InputStream = sourceDocument
  }

  /**
   * Sets the file absolute path as the input parameter
   */
  implicit object FileSourceDocument extends SourceDocumentLike[File] {

    override def commandParameter(sourceDocument: File): String =
      sourceDocument.getAbsolutePath

    override def asInputStream(sourceDocument: File): InputStream = {
      new FileInputStream(sourceDocument)
    }
  }

  /**
   * Pipes a UTF-8 string into the process STDIN
   */
  implicit object StringSourceDocument extends SourceDocumentLike[String] {
    override val isUsingInputStream: Boolean = true

    override def sourceFrom(sourceDocument: String)(process: ProcessBuilder) =
      process #< toInputStream(sourceDocument)

    private def toInputStream(sourceDocument: String): ByteArrayInputStream =
      new ByteArrayInputStream(sourceDocument.getBytes("UTF-8"))

    override def asInputStream(sourceDocument: String): InputStream = toInputStream(sourceDocument)
  }

  /**
   * Sets the URL as the input parameter
   */
  implicit object URLSourceDocument extends SourceDocumentLike[URL] {

    override def commandParameter(sourceDocument: URL) = sourceDocument.getProtocol match {
      case "https" | "http" | "file" => sourceDocument.toString
      case _ => throw new UnsupportedProtocolException(sourceDocument)
    }

    override def asInputStream(sourceDocument: URL): InputStream = {
      sourceDocument.openStream()
    }
  }

  /**
   * Sets the XML node as the input parameter
   */
  implicit object XmlSourceDocument extends SourceDocumentLike[Elem] {
    override val isUsingInputStream: Boolean = true

    override def sourceFrom(sourceDocument: Elem)(process: ProcessBuilder) =
      process #< toInputStream(sourceDocument)

    private def toInputStream(sourceDocument: Elem): ByteArrayInputStream =
      new ByteArrayInputStream(sourceDocument.toString().getBytes("UTF-8"))

    override def asInputStream(sourceDocument: Elem): InputStream = {
      toInputStream(sourceDocument)
    }
  }

}
