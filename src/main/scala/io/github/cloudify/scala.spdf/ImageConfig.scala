package io.github.cloudify.scala.spdf

import scala.sys.process._
import ParamShow._

/**
  * Holds the configuration parameters of Pdf Kit
  */
trait ImageConfig extends PdfConfig

object ImageConfig {

  /**
    * An instance of the default configuration
    */
  object default extends ImageConfig

  /**
    * Generates a sequence of command line parameters from a `PdfKitConfig`
    */
  def toParameters(config: ImageConfig): Seq[String] = {
    import config._
    Seq(
      allow.toParameter,
      background.toParameter,
      defaultHeader.toParameter,
      disableExternalLinks.toParameter,
      disableInternalLinks.toParameter,
      disableJavascript.toParameter,
      noPdfCompression.toParameter,
      disableSmartShrinking.toParameter,
      javascriptDelay.toParameter,
      enableForms.toParameter,
      encoding.toParameter,
      footerCenter.toParameter,
      footerFontName.toParameter,
      footerFontSize.toParameter,
      footerHtml.toParameter,
      footerLeft.toParameter,
      footerLine.toParameter,
      footerRight.toParameter,
      footerSpacing.toParameter,
      grayScale.toParameter,
      headerCenter.toParameter,
      headerFontName.toParameter,
      headerFontSize.toParameter,
      headerHtml.toParameter,
      headerLeft.toParameter,
      headerLine.toParameter,
      headerRight.toParameter,
      headerSpacing.toParameter,
      lowQuality.toParameter,
      marginBottom.toParameter,
      marginLeft.toParameter,
      marginRight.toParameter,
      marginTop.toParameter,
      minimumFontSize.toParameter,
      orientation.toParameter,
      outline.toParameter,
      outlineDepth.toParameter,
      pageHeight.toParameter,
      pageOffset.toParameter,
      pageSize.toParameter,
      pageWidth.toParameter,
      password.toParameter,
      printMediaType.toParameter,
      tableOfContent.toParameter,
      tableOfContentDepth.toParameter,
      tableOfContentDisableBackLinks.toParameter,
      tableOfContentDisableLinks.toParameter,
      tableOfContentFontName.toParameter,
      tableOfContentHeaderFontName.toParameter,
      tableOfContentHeaderFontSize.toParameter,
      tableOfContentHeaderText.toParameter,
      tableOfContentLevel1FontSize.toParameter,
      tableOfContentLevel1Indentation.toParameter,
      tableOfContentLevel2FontSize.toParameter,
      tableOfContentLevel2Indentation.toParameter,
      tableOfContentLevel3FontSize.toParameter,
      tableOfContentLevel3Indentation.toParameter,
      tableOfContentLevel4FontSize.toParameter,
      tableOfContentLevel4Indentation.toParameter,
      tableOfContentLevel5FontSize.toParameter,
      tableOfContentLevel5Indentation.toParameter,
      tableOfContentLevel6FontSize.toParameter,
      tableOfContentLevel6Indentation.toParameter,
      tableOfContentLevel7FontSize.toParameter,
      tableOfContentLevel7Indentation.toParameter,
      tableOfContentNoDots.toParameter,
      title.toParameter,
      userStyleSheet.toParameter,
      username.toParameter,
      useXServer.toParameter,
      viewportSize.toParameter,
      zoom.toParameter
    ).flatten
  }

  /**
    * Attempts to find the `wkhtmltoimage` executable in the system path.
    * @return
    */
  def findExecutable: Option[String] = try {
    val os = System.getProperty("os.name").toLowerCase
    val cmd = if(os.contains("windows")) "where wkhtmltoimage" else "which wkhtmltoimage"

    Option(cmd.!!.trim).filter(_.nonEmpty)
  } catch {
    case _: RuntimeException => None
  }

}
