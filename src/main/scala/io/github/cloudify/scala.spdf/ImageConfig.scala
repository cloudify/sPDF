package io.github.cloudify.scala.spdf

import scala.sys.process._

/**
  * Holds the configuration parameters of Pdf Kit
  */
trait ImageConfig {

  /**
    * Options for `wkhtmltoimage` command
    * See `wkhtmltoimage --extended-help` for a description of each option
    */

  val allow = Parameter[Iterable[String]]("allow")

  val bypassProxy = Parameter[Iterable[String]]("bypass-proxy-for")

  val cacheDir = Parameter[String]("cache-dir")

  val checkboxCheckedSvg = Parameter[String]("checkbox-checked-svg")

  val checkboxSvg = Parameter[String]("checkbox-svg")

  val cookie = Parameter[Iterable[String]]("cookie")

  val cookieJar = Parameter[String]("cookieJar")

  val cropH = Parameter[Int]("crop-h")

  val cropW = Parameter[Int]("crop-w")

  val cropX = Parameter[Int]("crop-x")

  val cropY = Parameter[Int]("crop-y")

  val customHeader = Parameter[Iterable[String]]("custom-header")

  val customHeaderPropagation = Parameter[Boolean]("custom-header-propagation")

  val noCustomHeaderPropagation = Parameter[Boolean]("no-custom-header-propagation")

  val debugJavascript = Parameter[Boolean]("debug-javascript")

  val noDebugJavascript = Parameter[Boolean]("no-debug-javascript")

  val encoding = Parameter[String]("encoding")

  val format = Parameter[String]("format")

  val height = Parameter[Int]("height")

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

  val password = Parameter[String]("password")

  val disablePlugins = Parameter[Boolean]("disable-plugins")

  val enablePlugins = Parameter[Boolean]("enable-plugins")

  val post = Parameter[Iterable[String]]("post")

  val postFile = Parameter[String]("post-file")

  val proxy = Parameter[String]("proxy")

  val quality = Parameter[Int]("quality")

  val quiet = Parameter[Boolean]("quiet")

  val radioButtonCheckedSvg = Parameter[String]("radiobuttion-checked-svg")

  val radioButtonSvg = Parameter[String]("radiobuttion-svg")

  val runScript = Parameter[String]("run-script")

  val stopSlowScripts = Parameter[Boolean]("stop-slow-scripts")

  val noStopSlowScripts = Parameter[Boolean]("no-stop-slow-scripts")

  val userStyleSheet = Parameter[String]("user-style-sheet")

  val username = Parameter[String]("username")

  val width = Parameter[Int]("width")

  val windowStatus = Parameter[String]("window-status")

  val zoom = Parameter[Float]("zoom")

}

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
      bypassProxy.toParameter,
      cacheDir.toParameter,
      checkboxCheckedSvg.toParameter,
      checkboxSvg.toParameter,
      cookie.toParameter,
      cookieJar.toParameter,
      cropH.toParameter,
      cropW.toParameter,
      cropX.toParameter,
      cropY.toParameter,
      customHeader.toParameter,
      customHeaderPropagation.toParameter,
      noCustomHeaderPropagation.toParameter,
      debugJavascript.toParameter,
      noDebugJavascript.toParameter,
      encoding.toParameter,
      format.toParameter,
      height.toParameter,
      images.toParameter,
      noImages.toParameter,
      disableJavascript.toParameter,
      enableJavascript.toParameter,
      javascriptDelay.toParameter,
      loadErrorHandling.toParameter,
      loadMediaErrorHandling.toParameter,
      disableLocalFileAccess.toParameter,
      enableLocalFileAccess.toParameter,
      minimumFontSize.toParameter,
      password.toParameter,
      disablePlugins.toParameter,
      enablePlugins.toParameter,
      post.toParameter,
      postFile.toParameter,
      proxy.toParameter,
      quality.toParameter,
      quiet.toParameter,
      radioButtonCheckedSvg.toParameter,
      radioButtonSvg.toParameter,
      runScript.toParameter,
      stopSlowScripts.toParameter,
      noStopSlowScripts.toParameter,
      userStyleSheet.toParameter,
      username.toParameter,
      width.toParameter,
      windowStatus.toParameter,
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
