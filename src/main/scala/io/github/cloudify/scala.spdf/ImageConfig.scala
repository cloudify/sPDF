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
    Seq(
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
