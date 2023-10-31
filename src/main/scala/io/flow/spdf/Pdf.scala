package io.flow.spdf

import scala.sys.process._
import java.io.File

class Pdf(executablePath: String, config: PdfConfig) {
  validateExecutable_!(executablePath)

  /** Runs the conversion tool to convert sourceDocument HTML into destinationDocument PDF.
    */
  def run[A, B](sourceDocument: A, destinationDocument: B)(implicit
    sourceDocumentLike: SourceDocumentLike[A],
    destinationDocumentLike: DestinationDocumentLike[B]
  ): Int = {
    val commandLine = toCommandLine(sourceDocument, destinationDocument)
    val process = Process(commandLine)
    def source = sourceDocumentLike.sourceFrom(sourceDocument) _
    def sink = destinationDocumentLike.sinkTo(destinationDocument) _

    (sink compose source)(process).!
  }

  /** Generates the command line needed to execute `wkhtmltopdf`
    */
  private def toCommandLine[A: SourceDocumentLike, B: DestinationDocumentLike](source: A, destination: B): Seq[String] =
    Seq(executablePath) ++
      PdfConfig.toParameters(config) ++
      Seq(
        "--quiet",
        implicitly[SourceDocumentLike[A]].commandParameter(source),
        implicitly[DestinationDocumentLike[B]].commandParameter(destination)
      )

  /** Check whether the executable is actually executable, if it isn't a NoExecutableException is thrown.
    */
  private def validateExecutable_!(executablePath: String): Unit = {
    val executableFile = new File(executablePath)
    if (!executableFile.canExecute) throw new NoExecutableException(executableFile.getAbsolutePath)
  }

}

object Pdf {

  /** Creates a new instance of Pdf with default configuration
    * @return
    */
  def apply(config: PdfConfig): Pdf = {
    val executablePath: String = PdfConfig.findExecutable.getOrElse {
      throw new NoExecutableException(System.getenv("PATH"))
    }

    apply(executablePath, config)
  }

  /** Creates a new instance of Pdf with the passed configuration
    */
  def apply(executablePath: String, config: PdfConfig): Pdf =
    new Pdf(executablePath, config)

}
