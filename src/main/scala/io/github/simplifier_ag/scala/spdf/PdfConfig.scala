package io.github.simplifier_ag.scala.spdf

import ParamShow._

import scala.sys.process._

/**
 * Holds the configuration parameters of Pdf Kit
 */
trait PdfConfig {

  /**
   * Options for `wkhtmltopdf` command
   * See `wkhtmltopdf --extended-help` for a description of each option
   */

  val allow: Parameter[Iterable[String]] = Parameter[Iterable[String]]("allow")

  val defaultHeader: Parameter[Boolean] = Parameter[Boolean]("default-header")

  val disableExternalLinks: Parameter[Boolean] = Parameter[Boolean]("disable-external-links")

  val disableInternalLinks: Parameter[Boolean] = Parameter[Boolean]("disable-internal-links")

  val disableJavascript: Parameter[Boolean] = Parameter[Boolean]("disable-javascript")

  @deprecated("Use noPdfCompression instead", "1.3.1")
  val disablePdfCompression: Parameter[Boolean] = Parameter[Boolean]("disable-pdf-compression")

  val noPdfCompression: Parameter[Boolean] = Parameter[Boolean]("no-pdf-compression")

  val disableSmartShrinking: Parameter[Boolean] = Parameter[Boolean]("disable-smart-shrinking")

  val javascriptDelay: Parameter[Int] = Parameter[Int]("javascript-delay")

  val enableForms: Parameter[Boolean] = Parameter[Boolean]("enable-forms")

  val encoding: Parameter[String] = Parameter[String]("encoding", "UTF-8")

  val grayScale: Parameter[Boolean] = Parameter[Boolean]("grayscale")

  val lowQuality: Parameter[Boolean] = Parameter[Boolean]("lowquality")

  val marginBottom: Parameter[String] = Parameter[String]("margin-bottom")

  val marginLeft: Parameter[String] = Parameter[String]("margin-left")

  val marginRight: Parameter[String] = Parameter[String]("margin-right")

  val marginTop: Parameter[String] = Parameter[String]("margin-top")

  val minimumFontSize: Parameter[Int] = Parameter[Int]("minimum-font-size")

  val background: Parameter[Option[Boolean]] = Parameter[Option[Boolean]]("background")

  val orientation: Parameter[PageOrientation] = Parameter[PageOrientation]("orientation")

  val pageHeight: Parameter[String] = Parameter[String]("page-height")

  val pageOffset: Parameter[String] = Parameter[String]("page-offset")

  val pageSize: Parameter[String] = Parameter[String]("page-size")

  val pageWidth: Parameter[String] = Parameter[String]("page-width")

  val title: Parameter[String] = Parameter[String]("title")

  val tableOfContent: ObjectParameter[Boolean] = ObjectParameter[Boolean]("toc")

  val zoom: Parameter[Float] = Parameter[Float]("zoom")

  val footerCenter: Parameter[String] = Parameter[String]("footer-center")

  val footerFontName: Parameter[String] = Parameter[String]("footer-font-name")

  val footerFontSize: Parameter[String] = Parameter[String]("footer-font-size")

  val footerHtml: Parameter[String] = Parameter[String]("footer-html")

  val footerLeft: Parameter[String] = Parameter[String]("footer-left")

  val footerLine: Parameter[Boolean] = Parameter[Boolean]("footer-line")

  val footerRight: Parameter[String] = Parameter[String]("footer-right")

  val footerSpacing: Parameter[Float] = Parameter[Float]("footer-spacing")

  val headerCenter: Parameter[String] = Parameter[String]("header-center")

  val headerFontName: Parameter[String] = Parameter[String]("header-font-name")

  val headerFontSize: Parameter[String] = Parameter[String]("header-font-size")

  val headerHtml: Parameter[String] = Parameter[String]("header-html")

  val headerLeft: Parameter[String] = Parameter[String]("header-left")

  val headerLine: Parameter[Option[Boolean]] = Parameter[Option[Boolean]]("header-line")

  val headerRight: Parameter[String] = Parameter[String]("header-right")

  val headerSpacing: Parameter[Float] = Parameter[Float]("header-spacing")

  val tableOfContentDepth: Parameter[Int] = Parameter[Int]("toc-depth")

  val tableOfContentDisableBackLinks: Parameter[Boolean] = Parameter[Boolean]("toc-disable-back-links")

  val tableOfContentDisableLinks: Parameter[Boolean] = Parameter[Boolean]("toc-disable-links")

  val tableOfContentFontName: Parameter[String] = Parameter[String]("toc-font-name")

  val tableOfContentHeaderFontName: Parameter[String] = Parameter[String]("toc-header-font-name")

  val tableOfContentHeaderFontSize: Parameter[Int] = Parameter[Int]("toc-header-font-size")

  val tableOfContentHeaderText: Parameter[String] = Parameter[String]("toc-header-text")

  val tableOfContentLevel1FontSize: Parameter[Int] = Parameter[Int]("toc-l1-font-size")

  val tableOfContentLevel1Indentation: Parameter[Int] = Parameter[Int]("toc-l1-indentation")

  val tableOfContentLevel2FontSize: Parameter[Int] = Parameter[Int]("toc-l2-font-size")

  val tableOfContentLevel2Indentation: Parameter[Int] = Parameter[Int]("toc-l2-indentation")

  val tableOfContentLevel3FontSize: Parameter[Int] = Parameter[Int]("toc-l3-font-size")

  val tableOfContentLevel3Indentation: Parameter[Int] = Parameter[Int]("toc-l3-indentation")

  val tableOfContentLevel4FontSize: Parameter[Int] = Parameter[Int]("toc-l4-font-size")

  val tableOfContentLevel4Indentation: Parameter[Int] = Parameter[Int]("toc-l4-indentation")

  val tableOfContentLevel5FontSize: Parameter[Int] = Parameter[Int]("toc-l5-font-size")

  val tableOfContentLevel5Indentation: Parameter[Int] = Parameter[Int]("toc-l5-indentation")

  val tableOfContentLevel6FontSize: Parameter[Int] = Parameter[Int]("toc-l6-font-size")

  val tableOfContentLevel6Indentation: Parameter[Int] = Parameter[Int]("toc-l6-indentation")

  val tableOfContentLevel7FontSize: Parameter[Int] = Parameter[Int]("toc-l7-font-size")

  val tableOfContentLevel7Indentation: Parameter[Int] = Parameter[Int]("toc-l7-indentation")

  val tableOfContentNoDots: Parameter[Boolean] = Parameter[Boolean]("toc-no-dots")

  val outline: Parameter[Option[Boolean]] = Parameter[Option[Boolean]]("outline")

  val outlineDepth: Parameter[Int] = Parameter[Int]("outline-depth")

  val printMediaType: Parameter[Option[Boolean]] = Parameter[Option[Boolean]]("print-media-type")

  val userStyleSheet: Parameter[String] = Parameter[String]("user-style-sheet")

  val username: Parameter[String] = Parameter[String]("username")

  val password: Parameter[String] = Parameter[String]("password")

  val viewportSize: Parameter[String] = Parameter[String]("viewport-size")

  val useXServer: Parameter[Boolean] = Parameter[Boolean]("use-xserver")
}

object PdfConfig {

  /**
   * An instance of the default configuration
   */
  object default extends PdfConfig

  /**
   * Generates a sequence of command line parameters from a `PdfKitConfig`
   */
  def toParameters(config: PdfConfig): Seq[String] = {
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
      zoom.toParameter,
      tableOfContent.toParameter(ObjectParamShow.BooleanObjectParamShow)
    ).flatten
  }

  /**
   * Attempts to find the `wkhtmltopdf` executable in the system path.
   * @return
   */
  def findExecutable: Option[String] = try {
    val os = System.getProperty("os.name").toLowerCase
    val cmd = if(os.contains("windows")) "where wkhtmltopdf" else "which wkhtmltopdf"

    Option(cmd.!!.trim).filter(_.nonEmpty)
  } catch {
    case _: RuntimeException => None
  }

}
