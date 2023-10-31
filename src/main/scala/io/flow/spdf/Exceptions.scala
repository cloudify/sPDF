package io.flow.spdf

import java.net.URL

/** Thrown in case the path to wkhtmltopdf is invalid
  */
case class NoExecutableException(path: String) extends Exception("No wkhtmltopdf executable found at %s".format(path))

/** Thrown in case a URL with unsupported protocol is used as source
  * @param url
  */
case class UnsupportedProtocolException(url: URL)
  extends Exception("The protocol is not supported by wkhtmltopdf".format(url.getProtocol))
