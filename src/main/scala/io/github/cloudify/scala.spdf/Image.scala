package io.github.cloudify.scala.spdf

import scala.sys.process._
import java.io.File

class Image(executablePath: String, config: ImageConfig) {
  validateExecutable_!(executablePath)

  /**
    * Runs the conversion tool to convert sourceDocument HTML into
    * destinationDocument image.
    */
  def run[A, B](sourceDocument: A, destinationDocument: B)(implicit sourceDocumentLike: SourceDocumentLike[A], destinationDocumentLike: DestinationDocumentLike[B]): Int = {
    val commandLine = toCommandLine(sourceDocument, destinationDocument)
    val process = Process(commandLine)
    def source = sourceDocumentLike.sourceFrom(sourceDocument) _
    def sink = destinationDocumentLike.sinkTo(destinationDocument) _

    (sink compose source)(process).!
  }

  /**
    * Generates the command line needed to execute `wkhtmltoimage`
    */
  private def toCommandLine[A: SourceDocumentLike, B: DestinationDocumentLike](source: A, destination: B): Seq[String] =
    Seq(executablePath) ++
      ImageConfig.toParameters(config) ++
      Seq(
        implicitly[SourceDocumentLike[A]].commandParameter(source),
        implicitly[DestinationDocumentLike[B]].commandParameter(destination)
      )

  /**
    * Check whether the executable is actually executable, if it isn't
    * a NoExecutableException is thrown.
    */
  private def validateExecutable_!(executablePath: String): Unit = {
    val executableFile = new File(executablePath)
    if(!executableFile.canExecute) throw new NoExecutableException(executableFile.getAbsolutePath)
  }

}

object Image {

  /**
    * Creates a new instance of Image with default configuration
    * @return
    */
  def apply(config: ImageConfig): Image = {
    val executablePath: String = ImageConfig.findExecutable.getOrElse {
      throw new NoExecutableException(System.getenv("PATH"))
    }

    apply(executablePath, config)
  }

  /**
    * Creates a new instance of Image with the passed configuration
    */
  def apply(executablePath: String, config: ImageConfig): Image =
    new Image(executablePath, config)

}
