package org.money

import org.scalatest.{FlatSpec, Matchers}
import java.io.PrintStream
import java.io.ByteArrayOutputStream

import org.money.Logger.PrintlnLogger

class LoggerSpec extends FlatSpec with Matchers {

  behavior of "PrintlnLogger"

  it should "Log to the output" ignore {
    val outContent = new ByteArrayOutputStream
    val oldOut = System.out
    try {
      System.out.flush()
      System.setOut(new PrintStream(outContent))
      val logger = PrintlnLogger
      logger.log("one$two$three")
      System.out.flush()
    } finally {
      System.setOut(oldOut)
    }
    outContent.toString.trim should include ("one$two$three")
  }

}
