package io.github.cloudify.scala.spdf

import scala.sys.process._
import ParamShow._

/**
 * Holds the configuration parameters of Pdf Kit
 */
trait PdfConfig {

  /**
   * Options for `wkhtmltopdf` command
   * See `wkhtmltopdf --extended-help` for a description of each option
   */

  val allow = Parameter[Iterable[String]]("allow")

  val defaultHeader = Parameter[Boolean]("default-header")

  val disableExternalLinks = Parameter[Boolean]("disable-external-links")

  val disableInternalLinks = Parameter[Boolean]("disable-internal-links")

  val disableJavascript = Parameter[Boolean]("disable-javascript")

  @deprecated("Use noPdfCompression instead", "1.3.1")
  val disablePdfCompression = Parameter[Boolean]("disable-pdf-compression")

  val noPdfCompression = Parameter[Boolean]("no-pdf-compression")

  val disableSmartShrinking = Parameter[Boolean]("disable-smart-shrinking")

  val convertForms = Parameter[Boolean]("forms")

  val encoding = Parameter[String]("encoding", "UTF-8")

  val grayScale = Parameter[Boolean]("grayscale")

  val lowQuality = Parameter[Boolean]("lowquality")

  val marginBottom = Parameter[String]("margin-bottom")

  val marginLeft = Parameter[String]("margin-left")

  val marginRight = Parameter[String]("margin-right")

  val marginTop = Parameter[String]("margin-top")

  val minimumFontSize = Parameter[Int]("minimum-font-size")

  val background = Parameter[Option[Boolean]]("background")

  val orientation = Parameter[PageOrientation]("orientation")

  val pageHeight = Parameter[String]("page-height")

  val pageOffset = Parameter[String]("page-offset")

  val pageSize = Parameter[String]("page-size")

  val pageWidth = Parameter[String]("page-width")

  val title = Parameter[String]("title")

  val tableOfContent = Parameter[Boolean]("toc")

  val zoom = Parameter[Float]("zoom")

  val footerCenter = Parameter[String]("footer-center")

  val footerFontName = Parameter[String]("footer-font-name")

  val footerFontSize = Parameter[String]("footer-font-size")

  val footerHtml = Parameter[String]("footer-html")

  val footerLeft = Parameter[String]("footer-left")

  val footerLine = Parameter[Boolean]("footer-line")

  val footerRight = Parameter[String]("footer-right")

  val footerSpacing = Parameter[Float]("footer-spacing")

  val headerCenter = Parameter[String]("header-center")

  val headerFontName = Parameter[String]("header-font-name")

  val headerFontSize = Parameter[String]("header-font-size")

  val headerHtml = Parameter[String]("header-html")

  val headerLeft = Parameter[String]("header-left")

  val headerLine = Parameter[Option[Boolean]]("header-line")

  val headerRight = Parameter[String]("header-right")

  val headerSpacing = Parameter[Float]("header-spacing")

  val tableOfContentDepth = Parameter[Int]("toc-depth")

  val tableOfContentDisableBackLinks = Parameter[Boolean]("toc-disable-back-links")

  val tableOfContentDisableLinks = Parameter[Boolean]("toc-disable-links")

  val tableOfContentFontName = Parameter[String]("toc-font-name")

  val tableOfContentHeaderFontName = Parameter[String]("toc-header-font-name")

  val tableOfContentHeaderFontSize = Parameter[Int]("toc-header-font-size")

  val tableOfContentHeaderText = Parameter[String]("toc-header-text")

  val tableOfContentLevel1FontSize = Parameter[Int]("toc-l1-font-size")

  val tableOfContentLevel1Indentation = Parameter[Int]("toc-l1-indentation")

  val tableOfContentLevel2FontSize = Parameter[Int]("toc-l2-font-size")

  val tableOfContentLevel2Indentation = Parameter[Int]("toc-l2-indentation")

  val tableOfContentLevel3FontSize = Parameter[Int]("toc-l3-font-size")

  val tableOfContentLevel3Indentation = Parameter[Int]("toc-l3-indentation")

  val tableOfContentLevel4FontSize = Parameter[Int]("toc-l4-font-size")

  val tableOfContentLevel4Indentation = Parameter[Int]("toc-l4-indentation")

  val tableOfContentLevel5FontSize = Parameter[Int]("toc-l5-font-size")

  val tableOfContentLevel5Indentation = Parameter[Int]("toc-l5-indentation")

  val tableOfContentLevel6FontSize = Parameter[Int]("toc-l6-font-size")

  val tableOfContentLevel6Indentation = Parameter[Int]("toc-l6-indentation")

  val tableOfContentLevel7FontSize = Parameter[Int]("toc-l7-font-size")

  val tableOfContentLevel7Indentation = Parameter[Int]("toc-l7-indentation")

  val tableOfContentNoDots = Parameter[Boolean]("toc-no-dots")

  val outline = Parameter[Option[Boolean]]("outline")

  val outlineDepth = Parameter[Int]("outline-depth")

  val printMediaType = Parameter[Option[Boolean]]("print-media-type")
  
  val username = Parameter[String]("username")
  
  val password = Parameter[String]("password")

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
      convertForms.toParameter,
      defaultHeader.toParameter,
      disableExternalLinks.toParameter,
      disableInternalLinks.toParameter,
      disableJavascript.toParameter,
      noPdfCompression.toParameter,
      disableSmartShrinking.toParameter,
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
      background.toParameter,
      orientation.toParameter,
      outline.toParameter,
      outlineDepth.toParameter,
      pageHeight.toParameter,
      pageOffset.toParameter,
      pageSize.toParameter,
      pageWidth.toParameter,
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
      zoom.toParameter,
      printMediaType.toParameter,
      username.toParameter,
      password.toParameter
    ).flatten
  }

  /**
   * Attempts to find the `wkhtmltopdf` executable in the system path.
   * @return
   */
  def findExecutable: Option[String] = try {
    Option("which wkhtmltopdf".!!.trim).filter(_.nonEmpty)
  } catch {
    case _: RuntimeException => None
  }

}
