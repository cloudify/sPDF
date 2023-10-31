package io.flow.spdf

import scala.sys.process.Process

class WrappedPdf(executable: Seq[String], config: PdfConfig) {

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

  def toCommandLine[A: SourceDocumentLike, B: DestinationDocumentLike](source: A, destination: B): Seq[String] =
    executable ++
      PdfConfig.toParameters(config) ++
      Seq(
        "--quiet",
        implicitly[SourceDocumentLike[A]].commandParameter(source),
        implicitly[DestinationDocumentLike[B]].commandParameter(destination)
      )
}

object WrappedPdf {
  def apply(executable: Seq[String], config: PdfConfig): WrappedPdf = new WrappedPdf(executable, config)
}
