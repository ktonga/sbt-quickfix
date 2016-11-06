package com.dscleaver.sbt.quickfix

import sbt._

object VimInteraction {

  lazy val vimServerName: Option[String] = sys.props.get("sbtquickfix.vim.servername")

  def call(vimExec: String, command: String): Int = {
    val server: List[String] =
      if(vimExec == "nvr") vimServerName.fold(List[String]()) { sn => List("--servername", sn) }
      else List("--servername", vimServerName.fold("GVIM")(identity))
    Process(List(vimExec) ++ server ++ List("--remote-send", s"<c-\\><c-n>:$command<cr>")).!
  }

}
