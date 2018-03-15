package io.github.cloudify.scala.spdf

import scala.sys.process._
import java.io.{File, FileOutputStream}

import scala.io.Source

class Pdf(executablePath: String, config: PdfConfig) {
  validateExecutable_!(executablePath)

  /**
    * Runs the conversion tool to convert sourceDocument HTML into
    * destinationDocument PDF.
    */
  def run[A, B](sourceDocument: A, destinationDocument: B)(implicit sourceDocumentLike: SourceDocumentLike[A], destinationDocumentLike: DestinationDocumentLike[B]): Int = {
    val commandLine = toCommandLine(sourceDocument, destinationDocument)
    val process = Process(commandLine)
    def source = sourceDocumentLike.sourceFrom(sourceDocument) _
    def sink = destinationDocumentLike.sinkTo(destinationDocument) _

    (sink compose source)(process).!
  }
  private def inputToFile(is: java.io.InputStream, f: java.io.File) {
    val in = scala.io.Source.fromInputStream(is)
    val out = new java.io.PrintWriter(f)
    try { in.getLines().foreach(out.println) }
    finally { out.close() }
  }
  def run[A, B](sourceDocuments: Seq[A], destinationDocument: B)(implicit sourceDocumentLike: SourceDocumentLike[A], destinationDocumentLike: DestinationDocumentLike[B]): Int = {
    if(sourceDocumentLike.isUsingInputStream) {
      val documents: Seq[File] = sourceDocuments.zipWithIndex.map { kv =>
        val (src, index) = kv
        val is = sourceDocumentLike.asInputStream(src)
        val f = File.createTempFile(s"html$index", ".html")
        inputToFile(is, f)
        f
      }
      run(documents, destinationDocument)
    } else {
      val commandLine = toCommandLine(sourceDocuments, destinationDocument)
      val process = Process(commandLine)
      val sources = sourceDocuments.map {src => sourceDocumentLike.sourceFrom(src) _}
      val source = sources.foldLeft(sources.head)(_ andThen _)
      def sink = destinationDocumentLike.sinkTo(destinationDocument) _

      (sink compose source)(process).!
    }
  }

  /**
    * Generates the command line needed to execute `wkhtmltopdf`
    */
  def toCommandLine[A: SourceDocumentLike, B: DestinationDocumentLike](source: A, destination: B): Seq[String] =
    Seq(executablePath) ++
      PdfConfig.toParameters(config) ++
      Seq(
        "--quiet",
        implicitly[SourceDocumentLike[A]].commandParameter(source),
        implicitly[DestinationDocumentLike[B]].commandParameter(destination)
      )

  def toCommandLine[A: SourceDocumentLike, B: DestinationDocumentLike](sources: Seq[A], destination: B): Seq[String] = {
    Seq(executablePath) ++
      PdfConfig.toParametersGlobal(config) ++
      Seq ("--quiet") ++
      sources.map {src => implicitly[SourceDocumentLike[A]].commandParameter(src)} ++
      Seq (implicitly[DestinationDocumentLike[B]].commandParameter(destination))
  }
  /**
    * Check whether the executable is actually executable, if it isn't
    * a NoExecutableException is thrown.
    */
  private def validateExecutable_!(executablePath: String): Unit = {
    val executableFile = new File(executablePath)
    if(!executableFile.canExecute) throw new NoExecutableException(executableFile.getAbsolutePath)
  }

}

object Pdf {

  /**
    * Creates a new instance of Pdf with default configuration
    * @return
    */
  def apply(config: PdfConfig): Pdf = {
    val executablePath: String = PdfConfig.findExecutable.getOrElse {
      throw new NoExecutableException(System.getenv("PATH"))
    }

    apply(executablePath, config)
  }

  /**
    * Creates a new instance of Pdf with the passed configuration
    */
  def apply(executablePath: String, config: PdfConfig): Pdf =
    new Pdf(executablePath, config)

}

