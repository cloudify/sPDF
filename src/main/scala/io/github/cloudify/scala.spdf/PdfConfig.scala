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

  val collate = Parameter[Boolean]("collate")

  val noCollate = Parameter[Boolean]("no-collate")

  val cookieJar = Parameter[String]("cookie-jar")

  val copies = Parameter[Int]("copies")

  val dpi = Parameter[Int]("dpi")

  val grayScale = Parameter[Boolean]("grayscale")

  val lowQuality = Parameter[Boolean]("lowquality")

  val marginBottom = Parameter[String]("margin-bottom")

  val marginLeft = Parameter[String]("margin-left")

  val marginRight = Parameter[String]("margin-right")

  val marginTop = Parameter[String]("margin-top")

  val orientation = Parameter[PageOrientation]("orientation")

  val pageHeight = Parameter[String]("page-height")

  val pageWidth = Parameter[String]("page-width")

  val title = Parameter[String]("title")

  val allow = Parameter[Iterable[String]]("allow")

  val background = Parameter[Boolean]("background")

  val noBackground = Parameter[Boolean]("no-background")

  val bypassProxyFor = Parameter[Iterable[String]]("bypass-proxy-for")

  val cacheDir = Parameter[String]("cache-dir")

  val checkboxCheckedSvg = Parameter[String]("checkbox-checked-svg")

  val checkboxSvg = Parameter[String]("checkbox-svg")

  val cookie = Parameter[Iterable[String]]("cookie")

  val customHeader = Parameter[Iterable[String]]("custom-header")

  val customHeaderPropagation = Parameter[Boolean]("custom-header-propagation")

  val noCustomHeaderPropagation = Parameter[Boolean]("no-custom-header-propagation")

  val debugJavascript = Parameter[Boolean]("debug-javascript")

  val noDebugJavascript = Parameter[Boolean]("no-debug-javascript")

  val encoding = Parameter[String]("encoding")

  val images = Parameter[Boolean]("images")

  val noImages = Parameter[Boolean]("no-images")

  val disableJavascript = Parameter[Boolean]("disable-javascript")

  val enableJavascript = Parameter[Boolean]("enable-javascript")

  val javascriptDelay = Parameter[Int]("javascript-delay")

  val loadErrorHandling = Parameter[String]("load-error-handling")

  val loadMediaErrorHandling = Parameter[String]("load-media-error-handling")

  val disableLocalFileAccess = Parameter[Boolean]("disable-local-file-access")

  val enableLocalFileAccess = Parameter[Boolean]("enable-local-file-access")

  val minimumFontSize = Parameter[Int]("minimum-font-size")

  val pageOffset = Parameter[Int]("page-offset")

  val password = Parameter[String]("password")

  val disablePlugins = Parameter[Boolean]("disable-plugins")

  val enablePlugins = Parameter[Boolean]("enable-plugins")

  val post = Parameter[Iterable[String]]("post")

  val postFile = Parameter[String]("post-file")

  val proxy = Parameter[String]("proxy")

  val radioButtonCheckedSvg = Parameter[String]("radiobuttion-checked-svg")

  val radioButtonSvg = Parameter[String]("radiobuttion-svg")

  val runScript = Parameter[String]("run-script")

  val stopSlowScripts = Parameter[Boolean]("stop-slow-scripts")

  val noStopSlowScripts = Parameter[Boolean]("no-stop-slow-scripts")

  val userStyleSheet = Parameter[String]("user-style-sheet")

  val username = Parameter[String]("username")

  val windowStatus = Parameter[String]("window-status")

  val zoom = Parameter[Float]("zoom")

  // Only deprecated options from here on
  @deprecated("No longer in use", "1.4.1")
  val defaultHeader = Parameter[Boolean]("default-header")

  @deprecated("No longer in use", "1.4.1")
  val disableExternalLinks = Parameter[Boolean]("disable-external-links")

  @deprecated("No longer in use", "1.4.1")
  val disableInternalLinks = Parameter[Boolean]("disable-internal-links")

  @deprecated("Use noPdfCompression instead", "1.3.1")
  val disablePdfCompression = Parameter[Boolean]("disable-pdf-compression")

  @deprecated("No longer in use", "1.4.1")
  val noPdfCompression = Parameter[Boolean]("no-pdf-compression")

  @deprecated("No longer in use", "1.4.1")
  val disableSmartShrinking = Parameter[Boolean]("disable-smart-shrinking")

  @deprecated("No longer in use", "1.4.1")
  val enableForms = Parameter[Boolean]("enable-forms")

  @deprecated("No longer in use", "1.4.1")
  val pageSize = Parameter[String]("page-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContent = Parameter[Boolean]("toc")

  @deprecated("No longer in use", "1.4.1")
  val footerCenter = Parameter[String]("footer-center")

  @deprecated("No longer in use", "1.4.1")
  val footerFontName = Parameter[String]("footer-font-name")

  @deprecated("No longer in use", "1.4.1")
  val footerFontSize = Parameter[String]("footer-font-size")

  @deprecated("No longer in use", "1.4.1")
  val footerHtml = Parameter[String]("footer-html")

  @deprecated("No longer in use", "1.4.1")
  val footerLeft = Parameter[String]("footer-left")

  @deprecated("No longer in use", "1.4.1")
  val footerLine = Parameter[Boolean]("footer-line")

  @deprecated("No longer in use", "1.4.1")
  val footerRight = Parameter[String]("footer-right")

  @deprecated("No longer in use", "1.4.1")
  val footerSpacing = Parameter[Float]("footer-spacing")

  @deprecated("No longer in use", "1.4.1")
  val headerCenter = Parameter[String]("header-center")

  @deprecated("No longer in use", "1.4.1")
  val headerFontName = Parameter[String]("header-font-name")

  @deprecated("No longer in use", "1.4.1")
  val headerFontSize = Parameter[String]("header-font-size")

  @deprecated("No longer in use", "1.4.1")
  val headerHtml = Parameter[String]("header-html")

  @deprecated("No longer in use", "1.4.1")
  val headerLeft = Parameter[String]("header-left")

  @deprecated("No longer in use", "1.4.1")
  val headerLine = Parameter[Option[Boolean]]("header-line")

  @deprecated("No longer in use", "1.4.1")
  val headerRight = Parameter[String]("header-right")

  @deprecated("No longer in use", "1.4.1")
  val headerSpacing = Parameter[Float]("header-spacing")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentDepth = Parameter[Int]("toc-depth")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentDisableBackLinks = Parameter[Boolean]("toc-disable-back-links")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentDisableLinks = Parameter[Boolean]("toc-disable-links")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentFontName = Parameter[String]("toc-font-name")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentHeaderFontName = Parameter[String]("toc-header-font-name")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentHeaderFontSize = Parameter[Int]("toc-header-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentHeaderText = Parameter[String]("toc-header-text")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel1FontSize = Parameter[Int]("toc-l1-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel1Indentation = Parameter[Int]("toc-l1-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel2FontSize = Parameter[Int]("toc-l2-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel2Indentation = Parameter[Int]("toc-l2-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel3FontSize = Parameter[Int]("toc-l3-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel3Indentation = Parameter[Int]("toc-l3-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel4FontSize = Parameter[Int]("toc-l4-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel4Indentation = Parameter[Int]("toc-l4-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel5FontSize = Parameter[Int]("toc-l5-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel5Indentation = Parameter[Int]("toc-l5-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel6FontSize = Parameter[Int]("toc-l6-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel6Indentation = Parameter[Int]("toc-l6-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel7FontSize = Parameter[Int]("toc-l7-font-size")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentLevel7Indentation = Parameter[Int]("toc-l7-indentation")

  @deprecated("No longer in use", "1.4.1")
  val tableOfContentNoDots = Parameter[Boolean]("toc-no-dots")

  @deprecated("No longer in use", "1.4.1")
  val outline = Parameter[Option[Boolean]]("outline")

  @deprecated("No longer in use", "1.4.1")
  val outlineDepth = Parameter[Int]("outline-depth")

  @deprecated("No longer in use", "1.4.1")
  val printMediaType = Parameter[Option[Boolean]]("print-media-type")

  @deprecated("No longer in use", "1.4.1")
  val viewportSize = Parameter[String]("viewport-size")

  @deprecated("No longer in use", "1.4.1")
  val useXServer = Parameter[Boolean]("use-xserver")
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
      bypassProxyFor.toParameter,
      cacheDir.toParameter,
      checkboxCheckedSvg.toParameter,
      checkboxSvg.toParameter,
      collate.toParameter,
      cookie.toParameter,
      cookieJar.toParameter,
      copies.toParameter,
      customHeader.toParameter,
      customHeaderPropagation.toParameter,
      debugJavascript.toParameter,
      disableJavascript.toParameter,
      disableLocalFileAccess.toParameter,
      disablePlugins.toParameter,
      dpi.toParameter,
      enableJavascript.toParameter,
      enableLocalFileAccess.toParameter,
      enablePlugins.toParameter,
      encoding.toParameter,
      grayScale.toParameter,
      images.toParameter,
      javascriptDelay.toParameter,
      loadErrorHandling.toParameter,
      loadMediaErrorHandling.toParameter,
      lowQuality.toParameter,
      marginBottom.toParameter,
      marginLeft.toParameter,
      marginRight.toParameter,
      marginTop.toParameter,
      minimumFontSize.toParameter,
      noBackground.toParameter,
      noCollate.toParameter,
      noCustomHeaderPropagation.toParameter,
      noDebugJavascript.toParameter,
      noImages.toParameter,
      noStopSlowScripts.toParameter,
      orientation.toParameter,
      pageHeight.toParameter,
      pageOffset.toParameter,
      pageWidth.toParameter,
      password.toParameter,
      post.toParameter,
      postFile.toParameter,
      proxy.toParameter,
      radioButtonCheckedSvg.toParameter,
      radioButtonSvg.toParameter,
      runScript.toParameter,
      stopSlowScripts.toParameter,
      title.toParameter,
      userStyleSheet.toParameter,
      username.toParameter,
      windowStatus.toParameter,
      zoom.toParameter
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
